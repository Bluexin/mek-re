package be.bluexin.mekre

import be.bluexin.mekre.common.items.MItems
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object Refs {

    val CTAB_MEKRE = object : CreativeTabs("tabMekRe") {
        @SideOnly(Side.CLIENT)
        override fun getTabIconItem() = MItems.alloy // TODO: custom item
    }

    fun getResourceLocation(target: String) = ResourceLocation(MekRe.MODID, target)
}
