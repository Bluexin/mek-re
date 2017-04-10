package be.bluexin.mekre.items.crafting

import be.bluexin.mekre.AlloyTypes
import be.bluexin.mekre.items.IItemVariant
import be.bluexin.mekre.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Alloy : MItem("alloy"), IItemVariant<AlloyTypes> {

    override fun get(variant: AlloyTypes, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<AlloyTypes>
        get() = AlloyTypes.values()
}
