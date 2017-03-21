package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.blocks.Ore
import be.bluexin.mekre.common.blocks.OreType
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class OreItem(ore: Ore) : MItemBlock(ore) {

    init {
        hasSubtypes = true
    }

    override fun getUnlocalizedName(stack: ItemStack): String {
        val s = super.getUnlocalizedName(stack)
        val i = stack.metadata
        return if (i < 0 || i >= OreType.values().size) s
        else "${s}_${OreType.values()[i]}"
    }
}
