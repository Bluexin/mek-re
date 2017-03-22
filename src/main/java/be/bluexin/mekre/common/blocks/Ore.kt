package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.common.blocks.states.BSOre
import be.bluexin.mekre.common.items.crafting.MetalType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Ore : MBlock("oreBlock", Material.ROCK, hardness = 3.0F, resistance = 5.0F), IBlockVariant<OreType> {

    override fun createBlockState() = BSOre(this)

    override fun getStateFromMeta(meta: Int) = this.defaultState.withProperty(BSOre.typeProperty, OreType.values()[meta])

    override fun getMetaFromState(state: IBlockState) = state.getValue(BSOre.typeProperty).ordinal

    override fun getHarvestLevel(state: IBlockState) = state.getValue(BSOre.typeProperty).harvestLevel

    override fun getHarvestTool(state: IBlockState?) = "pickaxe"

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : OreType> get(variant: E, amount: Int) = defaultState.withProperty(BSOre.typeProperty, variant)!!

    override val variants: Array<OreType>
        get() = OreType.values()
}

enum class OreType(val harvestLevel: Int, val metal: MetalType) : IStringSerializable {
    OSMIUM(2, MetalType.OSMIUM),
    COPPER(1, MetalType.COPPER),
    TIN(1, MetalType.TIN);

    override fun getName() = this.toString()

    override fun toString() = name.toLowerCase()
}

