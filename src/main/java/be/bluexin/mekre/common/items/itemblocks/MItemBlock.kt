package be.bluexin.mekre.common.items.itemblocks

import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class MItemBlock(block: Block) : ItemBlock(block) {

    init {
        this.registryName = block.registryName
        this.unlocalizedName = block.unlocalizedName
    }

    override fun getMetadata(damage: Int) = damage

    @SideOnly(Side.CLIENT) // advanced = debug-ish
    override fun addInformation(stack: ItemStack, playerIn: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
        if (I18n.hasKey("${this.getUnlocalizedName(stack)}.desc"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc"))
        if (advanced && I18n.hasKey("${this.getUnlocalizedName(stack)}.desc.adv"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc.adv"))
    }
}
