package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.blocks.states.BSOre
import be.bluexin.mekre.common.items.crafting.IngotType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class Ore : MBlock("oreBlock", Material.ROCK, hardness = 3.0F, resistance = 5.0F), IBlockVariant<OreType> {

    init {
        this.setHardness(3.0F)
        this.setResistance(5.0F)
        this.setCreativeTab(Refs.CTAB_MEKRE)
    }

    override fun createBlockState() = BSOre(this)

    override fun getStateFromMeta(meta: Int) = this.defaultState.withProperty(BSOre.typeProperty, OreType.values()[meta])

    override fun getMetaFromState(state: IBlockState) = state.getValue(BSOre.typeProperty).ordinal

    override fun getHarvestLevel(state: IBlockState) = state.getValue(BSOre.typeProperty).harvestLevel

    override fun getHarvestTool(state: IBlockState?) = "pickaxe"

    override val variantCount = OreType.values().size

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : OreType> get(variant: E, amount: Int) = defaultState.withProperty(BSOre.typeProperty, variant)!!

    override val variants: Array<OreType>
        get() = OreType.values()
}

enum class OreType(val harvestLevel: Int, val ingot: IngotType) : IStringSerializable {
    OSMIUM(2, IngotType.OSMIUM),
    COPPER(1, IngotType.COPPER),
    TIN(1, IngotType.TIN);

    override fun getName() = this.toString()

    override fun toString() = name.toLowerCase()
}

