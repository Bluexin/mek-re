package be.bluexin.mekre.items.crafting

import be.bluexin.mekre.MetalType
import be.bluexin.mekre.items.IItemVariant
import be.bluexin.mekre.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Ingot : MItem("ingot"), IItemVariant<MetalType> {

    override fun get(variant: MetalType, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<MetalType>
        get() = MetalType.values()
}
