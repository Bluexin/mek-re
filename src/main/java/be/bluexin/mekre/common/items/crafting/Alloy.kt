package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class Alloy : MItem("alloy"), IItemVariant<AlloyVariants> {

    override fun getUnlocalizedName(stack: ItemStack): String {
        val s = super.getUnlocalizedName(stack)
        val i = stack.metadata
        return if (i < 0 || i >= AlloyVariants.values().size) s
        else "${s}_${AlloyVariants.values()[i]}"
    }

    override val variants = AlloyVariants.values().size

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : AlloyVariants> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override fun iterator() = AlloyVariants.values().iterator()
}

enum class AlloyVariants {
    ENRICHED,
    REINFORCED,
    ATOMIC;

    override fun toString() = name.toLowerCase()
}
