package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.items.IItemVariant
import be.bluexin.mekre.common.items.MItem
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

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

enum class MetalType(val hasBlockForm: Boolean) : IStringSerializable {
    OSMIUM(true),
    COPPER(true),
    TIN(true),
    BRONZE(true),
    OBSIDIAN(false),
    GLOWSTONE(false),
    STEEL(true);

    override fun toString() = name.toLowerCase()

    override fun getName() = this.toString()
}
