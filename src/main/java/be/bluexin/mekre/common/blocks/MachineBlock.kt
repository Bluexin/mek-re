package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.common.MachineType
import be.bluexin.mekre.common.blocks.states.BSMachine
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.ReflectionHelper

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class MachineBlock(val subID: Int) : MVariantBlock<MachineType>("machineBlock$subID", Material.IRON, hardness = 3.5F, resistance = 16F) {
// TODO: when implementing the TEs, the facing will have to be propagated

    init {
        ReflectionHelper.setPrivateValue(Block::class.java, this, this.createBlockState(), "blockState", "field_176227_L")
        this.defaultState = blockState.baseState
    }

    override val variants: Array<MachineType> = MachineType.values().filter { it.ordinal >= subID * 16 && it.ordinal < (subID + 1) * 16 }.toTypedArray()

    override val variantsAll: Array<MachineType>
        get() = MachineType.values()

    override fun createBlockState() = BSMachine(this)

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        worldIn.setBlockState(pos, state.withProperty(BSMachine.facingProperty, placer.horizontalFacing.opposite))
    }

    override fun getStateFromMeta(meta: Int) = MachineBlocksHolder[subID][variants[meta % 16]]

    override lateinit var typeProperty: PropertyEnum<MachineType>
}

object MachineBlocksHolder : IManyBlocksHolder<MachineBlock, MachineType> {
    override val blocks: Array<MachineBlock> = (0..MachineType.values().size / 16).map(::MachineBlock).toTypedArray()
}
