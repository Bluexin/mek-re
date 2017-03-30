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

enum class MetalType(val hasBlockForm: Boolean = true) : IStringSerializable {
    OSMIUM(),
    COPPER(),
    TIN(),
    BRONZE(),
    OBSIDIAN(false),
    GLOWSTONE(false),
    STEEL();

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

enum class MachineType(val factorisable: Boolean = false) : IStringSerializable {
    INVALID(),
    ENRICHMENT_CHAMBER(true),
    OSMIUM_COMPRESSOR(),
    COMBINER(),
    CRUSHER(true),
    METALLURGIC_INFUSER(true),
    ENERGIZED_SMELTER(true),
    PRECISION_SAWMILL(true),
    PURIFICATION_CHAMBER(),
    ROTARY_CONDENSENTRATOR(),
    CHEMICAL_OXIDIZER(),
    CHEMICAL_INFUSER(),
    CHEMICAL_INJ_CHAMBER(),
    ELECTROLYTIC_SEPARATOR(),
    CHEMICAL_DISSOLUTION_CHAMBER(),
    CHEMICAL_WASHER(),
    CHEMICAL_CRYSTALLIZER(),
    SEISMIC_VIBRATOR(),
    PRESSURIZED_REACTION_CHAMBER(),
    FLUIDIC_PLENISHER(),
    OREDICTIONIFICATOR(),
    FORMULAIC_ASSEMBLICATOR();

    override fun toString() = name.toLowerCase()

    override fun getName() = this.toString()
}
