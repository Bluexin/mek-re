package be.bluexin.mekre.common.blocks

import net.minecraft.block.state.IBlockState

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
interface IBlockVariant<T : Enum<*>> {

    operator fun <E : T> get(variant: E, amount: Int = 0): IBlockState

    val variants: Array<T>
}
