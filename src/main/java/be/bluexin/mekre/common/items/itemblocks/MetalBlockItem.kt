package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.blocks.MetalBlock
import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.crafting.MetalType
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MetalBlockItem : MItemBlock(MetalBlock), IItemVariant<MetalType> {

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : MetalType> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<MetalType>
        get() = MetalType.values().filter { it.hasBlockForm }.toTypedArray()

    override val variantsAll: Array<MetalType>
        get() = MetalType.values()
}
