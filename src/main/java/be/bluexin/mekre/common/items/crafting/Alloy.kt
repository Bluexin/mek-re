package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Alloy : MItem("alloy"), IItemVariant<AlloyVariants> {

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : AlloyVariants> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<AlloyVariants>
        get() = AlloyVariants.values()
}

enum class AlloyVariants {
    ENRICHED,
    REINFORCED,
    ATOMIC;

    override fun toString() = name.toLowerCase()
}
