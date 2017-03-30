package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class MBlockContainer(name: String, material: Material, hardness: Float = 0.5F, resistance: Float = 0.0F) : BlockContainer(material) {

    init {
        this.setCreativeTab(Refs.CTAB_MEKRE)
        this.registryName = Refs.getResourceLocation(name)
        this.unlocalizedName = this.registryName.toString().replace(':', '.')
        this.setHardness(hardness)
        if (resistance != 0.0F) this.setResistance(resistance)
    }

    override fun addInformation(stack: ItemStack, player: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
        if (I18n.hasKey("${this.unlocalizedName}.desc"))
            tooltip.add(I18n.format("${this.unlocalizedName}.desc"))
        if (advanced && I18n.hasKey("${this.unlocalizedName}.desc.adv"))
            tooltip.add(I18n.format("${this.unlocalizedName}.desc.adv"))
    }

    override fun getSubBlocks(itemIn: Item, tab: CreativeTabs, list: MutableList<ItemStack>) {
        if (this is MVariantBlock<*>) this.variants.map { ItemStack(itemIn, 1, it.ordinal) }
    }

    override fun damageDropped(state: IBlockState) = getMetaFromState(state)

}
