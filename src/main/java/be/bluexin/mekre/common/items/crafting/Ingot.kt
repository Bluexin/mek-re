package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Ingot : MItem("ingot"), IItemVariant<MetalType> {

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : MetalType> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<MetalType>
        get() = MetalType.values()
}
