package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.OreType
import be.bluexin.mekre.common.blocks.Ore
import be.bluexin.mekre.common.items.IItemVariant
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object OreItem : MItemBlock(Ore), IItemVariant<OreType> {

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : OreType> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<OreType>
        get() = OreType.values()
}
