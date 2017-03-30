package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.blocks.states.BSMetalBlock
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MetalBlock : MVariantBlock<MetalType>("metalBlock", Material.IRON, hardness = 5F, resistance = 20F) {

    override fun createBlockState() = BSMetalBlock(this)

    override val variants: Array<MetalType>
        get() = MetalType.values().filter { it.hasBlockForm }.toTypedArray()

    override val typeProperty: PropertyEnum<MetalType>
        get() = BSMetalBlock.typeProperty

    override val variantsAll: Array<MetalType>
        get() = MetalType.values()
}
