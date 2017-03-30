package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.blocks.MetalBlock
import be.bluexin.mekre.common.blocks.states.BSMetalBlock
import be.bluexin.mekre.common.items.IItemVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MetalBlockItem : MItemBlock(MetalBlock), IItemVariant<MetalType> {

    override fun get(variant: MetalType, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<MetalType>
        get() = MetalType.values().filter { it.hasBlockForm }.toTypedArray()

    override val variantsAll: Array<MetalType>
        get() = MetalType.values()

    override fun applyToState(stack: ItemStack, state: IBlockState) = state.withProperty(BSMetalBlock.typeProperty, this.variantsAll[stack.itemDamage])!!
}
