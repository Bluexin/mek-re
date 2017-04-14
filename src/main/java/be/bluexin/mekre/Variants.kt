package be.bluexin.mekre

import be.bluexin.mekre.tiles.SmelterTE
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
enum class AlloyTypes {
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

enum class MachineType(val te: (() -> TileEntity)? = null, val factorisable: Boolean = false, val opaque: Boolean = true) : IStringSerializable {
    INVALID(),
    ENRICHMENT_CHAMBER(factorisable = true),
    OSMIUM_COMPRESSOR(),
    COMBINER(),
    CRUSHER(factorisable = true),
    METALLURGIC_INFUSER(factorisable = true, opaque = false),
    ENERGIZED_SMELTER(::SmelterTE, factorisable = true),
    PRECISION_SAWMILL(factorisable = true),
    PURIFICATION_CHAMBER(),
    ROTARY_CONDENSENTRATOR(opaque = false),
    CHEMICAL_OXIDIZER(opaque = false),
    CHEMICAL_INFUSER(opaque = false),
    CHEMICAL_INJ_CHAMBER(),
    ELECTROLYTIC_SEPARATOR(opaque = false),
    CHEMICAL_DISSOLUTION_CHAMBER(),
    CHEMICAL_WASHER(opaque = false),
    CHEMICAL_CRYSTALLIZER(),
    SEISMIC_VIBRATOR(),
    PRESSURIZED_REACTION_CHAMBER(opaque = false),
    FLUIDIC_PLENISHER(opaque = false),
    OREDICTIONIFICATOR(),
    FORMULAIC_ASSEMBLICATOR();

    override fun toString() = name.toLowerCase()

    override fun getName() = this.toString()
}
