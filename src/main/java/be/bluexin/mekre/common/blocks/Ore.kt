package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.blocks.states.BSOre
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

    override val variants = OreType.values().size

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : OreType> get(variant: E, amount: Int) = defaultState.withProperty(BSOre.typeProperty, variant)!!

    override fun iterator() = OreType.values().iterator()
}

enum class OreType(val harvestLevel: Int): IStringSerializable {
    OSMIUM(2),
    COPPER(1),
    TIN(1);

    override fun getName() = this.toString()

    override fun toString() = name.toLowerCase()
}

