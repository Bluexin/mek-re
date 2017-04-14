package be.bluexin.mekre.blocks

import be.bluexin.mekre.MachineType
import be.bluexin.mekre.blocks.states.BSMachine
import be.bluexin.mekre.containers.OperatingTEContainer
import be.bluexin.mekre.tiles.EnergeticTE
import be.bluexin.mekre.tiles.OperatingTE
import com.teamwizardry.librarianlib.common.container.GuiHandler
import com.teamwizardry.librarianlib.common.util.getTileEntitySafely
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.ReflectionHelper

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class MachineBlock(val subID: Int) : MVariantBlockContainer<MachineType>("machineBlock$subID", Material.IRON, hardness = 3.5F, resistance = 16F) {
// TODO: when implementing the TEs, the facing will have to be propagated
// About TE sync: https://gist.github.com/williewillus/7945c4959b1142ece9828706b527c5a4
// https://github.com/Growlith1223/ArsMagica2/commit/cc3e11e14983abfe00889892ce26cbf442d940e8
// https://github.com/TehNut/LendingLibrary/blob/1.11/src/main/java/tehnut/lib/mc/tile/TileBase.java

    init {
        ReflectionHelper.setPrivateValue(Block::class.java, this, this.createBlockState(), "blockState", "field_176227_L")
        this.defaultState = blockState.baseState
    }

    override val variants: Array<MachineType> = MachineType.values().filter { it.ordinal >= subID * 16 && it.ordinal < (subID + 1) * 16 }.toTypedArray()

    override val variantsAll: Array<MachineType>
        get() = MachineType.values()

    override fun createBlockState() = BSMachine(this)

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        (worldIn.getTileEntity(pos) as EnergeticTE).facing = placer.horizontalFacing.opposite
    }

    override fun getStateFromMeta(meta: Int) = MachineBlocksHolder[subID][variants[meta % 16]]

    override lateinit var typeProperty: PropertyEnum<MachineType>

    override fun isOpaqueCube(state: IBlockState) = state.getValue(this.typeProperty).opaque

    override fun createTileEntity(world: World, state: IBlockState) = state.getValue(this.typeProperty).te!!.invoke()

    override fun hasTileEntity(state: IBlockState) = state.getValue(this.typeProperty).te != null

    override fun getActualState(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): IBlockState? {
        val te = worldIn.getTileEntitySafely(pos) as? OperatingTE
        return super.getActualState(state, worldIn, pos)
                .withProperty(BSMachine.activeProperty, te?.operates ?: false)
                .withProperty(BSMachine.facingProperty, te?.facing ?: EnumFacing.NORTH)
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        return if (hasTileEntity(state)) {
            GuiHandler.open(OperatingTEContainer.NAME, playerIn, pos)
            true
        } else false
    }
}

object MachineBlocksHolder : IManyBlocksHolder<MachineBlock, MachineType> {
    override val blocks: Array<MachineBlock> = (0..MachineType.values().size / 16).map(::MachineBlock).toTypedArray()
}
