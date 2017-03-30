package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.common.OreType
import be.bluexin.mekre.common.blocks.states.BSOre
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Ore : MVariantBlock<OreType>("oreBlock", Material.ROCK, hardness = 3.0F, resistance = 5.0F) {

    override fun createBlockState() = BSOre(this)

    override fun getHarvestLevel(state: IBlockState) = state.getValue(BSOre.typeProperty).harvestLevel

    override fun getHarvestTool(state: IBlockState?) = "pickaxe"

    override val variants: Array<OreType>
        get() = OreType.values()

    override val typeProperty: PropertyEnum<OreType>
        get() = BSOre.typeProperty
}
