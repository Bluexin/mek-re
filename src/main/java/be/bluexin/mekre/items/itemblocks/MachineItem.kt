package be.bluexin.mekre.items.itemblocks

import be.bluexin.mekre.MachineType
import be.bluexin.mekre.blocks.MachineBlocksHolder
import be.bluexin.mekre.items.IItemVariant
import be.bluexin.mekre.items.IManyItemsHolder
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class MachineItem(val subID: Int) : MItemBlock(MachineBlocksHolder[subID]), IItemVariant<MachineType> {

    override fun get(variant: MachineType, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<MachineType> = MachineType.values().filter { it.ordinal >= subID * 16 && it.ordinal < (subID + 1) * 16 }.toTypedArray()

    override val variantsAll: Array<MachineType>
        get() = MachineType.values()

    override fun applyToState(stack: ItemStack, state: IBlockState) = MachineBlocksHolder[variantsAll[stack.itemDamage]]
}

object MachineItemsHolder : IManyItemsHolder<MachineItem, MachineType> {
    override val items: Array<MachineItem> = (0..MachineType.values().size / 16).map(::MachineItem).toTypedArray()
}
