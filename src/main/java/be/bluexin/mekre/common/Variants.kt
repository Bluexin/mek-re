package be.bluexin.mekre.common

import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
enum class AlloyVariants {
    ENRICHED,
    REINFORCED,
    ATOMIC;

    override fun toString() = name.toLowerCase()
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

enum class OreType(val harvestLevel: Int, val metal: MetalType) : IStringSerializable {
    OSMIUM(2, MetalType.OSMIUM),
    COPPER(1, MetalType.COPPER),
    TIN(1, MetalType.TIN);

    override fun getName() = this.toString()

    override fun toString() = name.toLowerCase()
}
