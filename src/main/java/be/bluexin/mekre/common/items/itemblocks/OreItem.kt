package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.OreType
import be.bluexin.mekre.common.blocks.Ore
import be.bluexin.mekre.common.items.IItemVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object OreItem : MItemBlock(Ore), IItemVariant<OreType> {

    override fun get(variant: OreType, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<OreType>
        get() = OreType.values()

    override fun applyToState(stack: ItemStack, state: IBlockState) = state.withProperty(Ore.typeProperty, this.variants[stack.itemDamage])!!
}
