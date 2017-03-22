package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.common.MetalType
import be.bluexin.mekre.common.blocks.states.BSMetalBlock
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MetalBlock : MBlock("metalBlock", Material.IRON, hardness = 5F, resistance = 20F), IBlockVariant<MetalType> {

    override fun createBlockState() = BSMetalBlock(this)

    override fun getStateFromMeta(meta: Int) =
            if (MetalType.values()[meta].hasBlockForm) this.defaultState.withProperty(BSMetalBlock.typeProperty, MetalType.values()[meta])
            else this.defaultState

    override fun getMetaFromState(state: IBlockState) = state.getValue(BSMetalBlock.typeProperty).ordinal

    @Suppress("FINAL_UPPER_BOUND")
    override fun <E : MetalType> get(variant: E, amount: Int) = defaultState.withProperty(BSMetalBlock.typeProperty, variant)!!

    override val variants: Array<MetalType>
        get() = MetalType.values().filter { it.hasBlockForm }.toTypedArray()
}
