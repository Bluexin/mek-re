package be.bluexin.mekre.tiles

import be.bluexin.saomclib.onClient
import com.teamwizardry.librarianlib.common.base.block.TileModInventory
import com.teamwizardry.librarianlib.common.util.saving.Save
import com.teamwizardry.librarianlib.common.util.saving.SaveMethodGetter
import com.teamwizardry.librarianlib.common.util.saving.SaveMethodSetter
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class EnergeticTE(capacity: Int, customStackCount: Int = 0) : TileModInventory(customStackCount), IEnergyStorage, ICapabilityProvider {

    /*
    EIO Tiers :
        * cables :
            1)     640RF/T
            2)   5.120RF/T
            3)  20.480RF/T
        * capacitors :
            1)   1.000RF/T
            2)   5.000RF/T
            3)  25.000RF/T
     */

    val unlocalizedName: String by lazy {
        val s = world.getBlockState(pos)
        val item = ItemStack(s.block, 1, s.block.getMetaFromState(s))
        item.displayName
    }

    var facing: EnumFacing = EnumFacing.NORTH
        @SaveMethodGetter("facing") get
        @SaveMethodSetter("facing") set(value) {
            if (value != field && value.horizontalIndex >= 0) {
                field = value
                if (world != null) {
                    markDirty()
                    world onClient { world.markBlockRangeForRenderUpdate(pos, pos) }
                }
            }
        }

    @Save
    open protected var capacity = capacity
        set(value) {
            if (value != field) {
                field = value
                markDirty()
            }
        }

    @Save
    protected var energy = 0
        set(value) {
            if (value != field) {
                field = value
                markDirty()
            }
        }

    open protected val input: Int
        get() = capacity
    open protected val output: Int
        get() = capacity

    override fun canExtract() = extractEnergy(1, true) > 0

    override fun getMaxEnergyStored() = capacity

    override fun getEnergyStored() = energy

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        val r = Math.min(Math.min(maxExtract, output), energy)
        if (!simulate) energy -= r
        return r
    }

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        val r = Math.min(Math.min(maxReceive, input), capacity - energy)
        if (!simulate) energy += r
        return r
    }

    override fun canReceive() = receiveEnergy(1, true) > 0

//    @CapabilityProvide(sides = EnumFacing.DOWN)
//    val energyStorage: IEnergyStorage = this

    override fun <T : Any> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        when {
            capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && isFaceValidForItems(facing) -> return itemHandler as T
            capability == CapabilityEnergy.ENERGY && isFaceValidForPower(facing) -> return this as T
            else -> return super.getCapability(capability, facing)
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        when {
            capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && isFaceValidForItems(facing) -> return true
            capability == CapabilityEnergy.ENERGY && isFaceValidForPower(facing) -> return true
            else -> return super.hasCapability(capability, facing)
        }
    }

    open val itemHandler: IItemHandler by lazy { this }

    private fun isFaceValidForItems(facing: EnumFacing?): Boolean {
        return true // TODO: config + different handlers depending on face config (in/out/both/nothing)
    }

    private fun isFaceValidForPower(facing: EnumFacing?): Boolean {
        return true // TODO: config + different handlers depending on face config (in/out/both/nothing)
    }


    override fun markDirty() {
        dirty = true
    }

    fun clean() {
        if (dirty) {
            super.markDirty()
            dirty = false
        }
    }

    private var dirty: Boolean = false
}
