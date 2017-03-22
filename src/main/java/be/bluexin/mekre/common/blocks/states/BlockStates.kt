package be.bluexin.mekre.common.blocks.states

import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.OreType
import be.bluexin.mekre.common.blocks.MetalBlock
import be.bluexin.mekre.common.blocks.Ore
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class BSOre(ore: Ore) : BlockStateContainer(ore, typeProperty) {

    companion object {
        val typeProperty = PropertyEnum.create<OreType>("type", OreType::class.java)!!
    }
}

class BSMetalBlock(metalBlock: MetalBlock) : BlockStateContainer(metalBlock, typeProperty) {
    companion object {
        val typeProperty = PropertyEnum.create<MetalType>("type", MetalType::class.java, { it!!.hasBlockForm })!!
    }
}
