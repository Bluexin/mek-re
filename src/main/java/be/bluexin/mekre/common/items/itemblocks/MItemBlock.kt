package be.bluexin.mekre.common.items.itemblocks

import be.bluexin.mekre.common.blocks.MBlock
import be.bluexin.mekre.common.items.IItemVariant
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class MItemBlock(block: MBlock) : ItemBlock(block) {

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
            this as IItemVariant<Enum<*>> // yeah, this is to let it smart cast (can't specify `Any` with `is` because java u_u )
            subItems.addAll(this.variants.map { this[it] })
        }
    }

    override fun getUnlocalizedName(stack: ItemStack): String {
        if (this is IItemVariant<*>) {
            val i = stack.metadata
            val s = super.getUnlocalizedName(stack)
            return if (i < 0 || i >= this.maxMeta) s
            else "${s}_${this.variantsAll[i]}"
        } else return super.getUnlocalizedName(stack)
    }

    open fun applyToState(stack: ItemStack, state: IBlockState) = state // A shame this can't be handled with java generics

    override fun placeBlockAt(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, newState: IBlockState)
            = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, this.applyToState(stack, newState))
}
