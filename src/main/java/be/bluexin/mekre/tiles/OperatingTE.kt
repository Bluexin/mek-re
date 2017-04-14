package be.bluexin.mekre.tiles

import be.bluexin.mekre.crafting.MachineRecipe
import be.bluexin.mekre.crafting.Operation
import be.bluexin.saomclib.onClient
import be.bluexin.saomclib.onServer
import com.teamwizardry.librarianlib.common.util.saving.Save
import com.teamwizardry.librarianlib.common.util.saving.SaveMethodGetter
import com.teamwizardry.librarianlib.common.util.saving.SaveMethodSetter
import net.minecraft.item.ItemStack
import net.minecraft.util.ITickable
import net.minecraftforge.items.IItemHandler

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class OperatingTE(capacity: Int, inputSize: Int = 1, outputSize: Int = 1, parallelOperations: Int = 1) : EnergeticTE(capacity, customStackCount = inputSize + outputSize), ITickable {
// TODO: move from ISidedInventory to ItemStackHandler capability

    abstract protected val recipes: List<MachineRecipe>

    open protected val progressSpeed = 1.0f

    val inputRange = 0 until inputSize
    val outputRange = inputSize until inputSize + outputSize

    var operates = false
        @SaveMethodGetter(saveName = "operates") get
        @SaveMethodSetter(saveName = "operates") set(value) {
            if (value != field) {
                field = value
                if (world != null) {
                    markDirty()
                    world onClient { world.markBlockRangeForRenderUpdate(pos, pos) }
                }
            }
        }

    /**
     * Check whether this machine currently has the ingredients to handle a given recipe.
     * Energy should not be accounted for.
     */
    abstract fun hasIngredients(recipe: MachineRecipe): Boolean

    /**
     * Consume the ingredients for a given recipe.
     * Energy should not be accounted for.
     */
    abstract fun consumeIngredients(recipe: MachineRecipe)

    /**
     * Grand the results of this recipe.
     * Energy should not be accounted for.
     */
    // TODO: check whether there's enough available space to accept the results
    abstract fun grantResults(recipe: MachineRecipe)

    @Save
    val currentOperations = Array<Operation?>(parallelOperations, { null })

    override fun update() {
        world onServer {
            currentOperations.indices.forEach {
                val op = currentOperations[it]
                if (op == null) {
                    this.tryAndStartOp(it)
                } else {
                    if (op.canProgress(this.energy)) this.energy -= op.tick(progressSpeed)
                    if (op.done) {
                        this.grantResults(op.recipe)
                        currentOperations[it] = null

                        if (!this.tryAndStartOp(it) && (currentOperations.all { it == null })) //operates = false
                    }
                    markDirty()
                }
            }
            this.operates = currentOperations.any { it != null && it.canProgress(this.energy) }
            clean()
        }
    }

    private val firstAvailableRecipe: MachineRecipe?
        get() = recipes.find { this.hasIngredients(it) }

    private fun tryAndStartOp(opIdx: Int): Boolean {
        val r = firstAvailableRecipe
        return if (r != null) {
            this.consumeIngredients(r)
            currentOperations[opIdx] = Operation(r)
//            this.operates = true
            markDirty()
            true
        } else false
    }

    override fun insertItem(slot: Int, stack: ItemStack?, simulate: Boolean) =
            if (inputRange.contains(slot)) super.insertItem(slot, stack, simulate) else stack

    protected fun insertOutput(slot: Int, stack: ItemStack?, simulate: Boolean) =
            if (outputRange.contains(slot)) super.insertItem(slot, stack, simulate) else stack

//    override fun extractItem(slot: Int, amount: Int, simulate: Boolean) =
//            if (outputRange.contains(slot)) super.extractItem(slot, amount, simulate) else null

//    protected fun extractInput(slot: Int, amount: Int, simulate: Boolean) =
//            super.extractItem(slot, amount, simulate)

    override val itemHandler: IItemHandler by lazy { WorldItemHandler(this) }
}

class WorldItemHandler(val te: OperatingTE) : IItemHandler {
    override fun insertItem(slot: Int, stack: ItemStack?, simulate: Boolean) =
            if (te.inputRange.contains(slot)) te.insertItem(slot, stack, simulate) else stack

    override fun getStackInSlot(slot: Int) = te.getStackInSlot(slot)

    override fun getSlots() = te.slots

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean) =
            if (te.outputRange.contains(slot)) te.extractItem(slot, amount, simulate) else null
}

