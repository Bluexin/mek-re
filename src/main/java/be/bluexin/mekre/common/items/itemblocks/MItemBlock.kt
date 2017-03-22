package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.items.IItemVariant
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
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
        this.maxDamage = 0
        @Suppress("LeakingThis")
        this.hasSubtypes = this is IItemVariant<*>
    }

    override fun getMetadata(damage: Int) = damage

    @SideOnly(Side.CLIENT) // advanced = debug-ish
    override fun addInformation(stack: ItemStack, playerIn: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
        if (I18n.hasKey("${this.getUnlocalizedName(stack)}.desc"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc"))
        if (advanced && I18n.hasKey("${this.getUnlocalizedName(stack)}.desc.adv"))
            tooltip.add(I18n.format("${this.getUnlocalizedName(stack)}.desc.adv"))
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: MutableList<ItemStack>) {
        if (this is IItemVariant<*>) {
            @Suppress("UNCHECKED_CAST")
            this as IItemVariant<Any> // yeah, this is to let it smart cast (can't specify `Any` with `is` because java u_u )
            subItems.addAll(this.variants.map { this[it] })
        }
    }

    override fun getUnlocalizedName(stack: ItemStack): String {
        if (this is IItemVariant<*>) {
            val i = stack.metadata
            val s = super.getUnlocalizedName(stack)
            return if (i < 0 || i >= this.variantsCount) s
            else "${s}_${this.variants[i]}"
        } else return super.getUnlocalizedName(stack)
    }
}
