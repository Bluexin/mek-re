package be.bluexin.mekre.blocks

import be.bluexin.mekre.Refs
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class MBlock(name: String, material: Material, hardness: Float = 0.5F, resistance: Float = 0.0F) : Block(material) {

    init {
        this.setCreativeTab(Refs.CTAB_MEKRE)
        this.registryName = Refs.getResourceLocation(name)
        this.unlocalizedName = this.registryName.toString().replace(':', '.')
        this.setHardness(hardness)
        if (resistance != 0.0F) this.setResistance(resistance)
    }

    override fun getMetaFromState(state: IBlockState) = 0
}
