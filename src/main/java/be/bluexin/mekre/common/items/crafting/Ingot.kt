package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.MItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class Ingot : MItem("ingot"), IItemVariant<IngotType> {

    override val variantsCount = IngotType.values().size

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : IngotType> get(variant: E, amount: Int) = ItemStack(this, amount, variant.ordinal)

    override val variants: Array<IngotType>
        get() = IngotType.values()
}

enum class IngotType {
    OSMIUM,
    COPPER,
    TIN,
    BRONZE,
    OBSIDIAN,
    GLOWSTONE,
    STEEL;

    override fun toString() = name.toLowerCase()
}
