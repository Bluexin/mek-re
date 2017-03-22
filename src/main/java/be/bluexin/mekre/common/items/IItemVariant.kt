package be.bluexin.mekre.common.items

import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
interface IItemVariant<T : Enum<*>> {

    operator fun <E : T> get(variant: E, amount: Int = 1): ItemStack

    val variants: Array<T>

    val maxMeta: Int
        get() = variantsAll.size

    val variantsAll: Array<T>
        get() = variants
}
