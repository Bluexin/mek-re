package be.bluexin.mekre.common.items

import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
interface IItemVariant<T> : Iterable<T> {

    val variants: Int

    operator fun <E : T> get(variant: E, amount: Int = 1): ItemStack
}
