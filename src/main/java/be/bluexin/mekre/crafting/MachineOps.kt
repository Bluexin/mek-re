package be.bluexin.mekre.crafting

import com.teamwizardry.librarianlib.common.util.saving.Savable
import com.teamwizardry.librarianlib.common.util.saving.SavableConstructorOrder
import com.teamwizardry.librarianlib.common.util.saving.Save
import net.minecraft.item.ItemStack
import java.util.*

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@Savable
data class MachineRecipe
@SavableConstructorOrder("input", "output", "energyNet", "time") constructor(

        @Save
        val input: ArrayList<ItemStack>,

        @Save
        val output: ArrayList<ItemStack>,

        /**
         * Energy result from this operation.
         * Can be positive (energy gain, aka generation) or negative (energy loss, aka consumption).
         */
        @Save
        val energyNet: Int,

        /**
         * Amount of ticks required for this operation.
         */
        @Save
        val time: Int
) // TODO: remove either energyNet or time? Kinda redundant. Instead, use machine-based consumption per tick.

@Savable
class Operation(@Save val recipe: MachineRecipe) {

    @SavableConstructorOrder("currentProgress", "recipe")
    @Deprecated(message = "Never to be called manually. Needed for auto save.")
    constructor(currentProgress: Float, recipe: MachineRecipe) : this(recipe) {
        this.currentProgress = currentProgress
    }

    @Save
    private var currentProgress = 0.0f
    private val energyPerDelta by lazy { recipe.energyNet / recipe.time.toFloat() }

    fun tick(delta: Float): Int {
        currentProgress += delta
        return (energyPerDelta * delta - Math.max(0.0f, currentProgress - recipe.time)).toInt()
    }

    val done: Boolean
        get() = currentProgress >= recipe.time

    fun canProgress(energy: Int) = energy >= energyPerDelta

    val progress: Float
        get() = currentProgress / recipe.time
}
