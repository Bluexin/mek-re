package be.bluexin.mekre.common.items

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
interface IManyItemsHolder<T, E> where T : IItemVariant<E>, E : Enum<E> {

    val items: Array<T>

    operator fun get(variant: E) = items[variant.ordinal / 16][variant]

    operator fun get(idx: Int) = items[idx]
}
