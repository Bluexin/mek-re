package be.bluexin.mekre.common.items.crafting

import be.bluexin.mekre.common.items.MItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class Alloy : MItem("alloy") {

    init {
        this.hasSubtypes = true
    }

    override fun getUnlocalizedName(stack: ItemStack): String {
        val s = super.getUnlocalizedName(stack)
        val i = stack.metadata
        return if (i < 0 || i >= AlloyVariants.values().size) s
        else s + AlloyVariants.values()[i]
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: MutableList<ItemStack>) {
        subItems.addAll((0..AlloyVariants.values().size - 1).map { ItemStack(this, 1, it) })
    }

}

enum class AlloyVariants {
    ENRICHED,
    REINFORCED,
    ATOMIC;

    override fun toString() = name.toLowerCase()
}
