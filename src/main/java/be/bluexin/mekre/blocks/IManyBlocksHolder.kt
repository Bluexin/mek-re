package be.bluexin.mekre.blocks

import net.minecraft.util.IStringSerializable

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
interface IManyBlocksHolder<T, E> where T : MVariantBlock<E>, E : Enum<E>, E : IStringSerializable {

    val blocks: Array<T>

    operator fun get(variant: E) = blocks[variant.ordinal / 16][variant]

    operator fun get(idx: Int) = blocks[idx]
}
