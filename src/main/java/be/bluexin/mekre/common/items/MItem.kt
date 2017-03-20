package be.bluexin.mekre.common.items

import be.bluexin.mekre.MekRe
import be.bluexin.mekre.Refs
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@Suppress("UNCHECKED_CAST")
abstract class MItem(name: String) : Item() {

    init {
        this.registryName = ResourceLocation(MekRe.MODID, name)
        this.unlocalizedName = this.registryName.toString().replace(':', '.')
        this.creativeTab = Refs.CTAB_MEKRE
        this.maxDamage = 0
    }

    override fun getMetadata(damage: Int) = damage

    @SideOnly(Side.CLIENT) // advanced = debug-ish
    override fun addInformation(stack: ItemStack, playerIn: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
        super.addInformation(stack, playerIn, tooltip, advanced)

        if (I18n.hasKey("${this.getUnlocalizedName(stack)}.desc"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc"))
        if (advanced && I18n.hasKey("${this.getUnlocalizedName(stack)}.desc.adv"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc.adv"))
    }
}
