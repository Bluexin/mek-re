package be.bluexin.mekre.blocks

import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IStringSerializable
import net.minecraft.world.World

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
abstract class MVariantBlock<T>(name: String, material: Material, hardness: Float, resistance: Float) : MBlock(name, material, hardness, resistance) where T : Enum<T>, T : IStringSerializable {

    open operator fun get(variant: T) = defaultState.withProperty(typeProperty, variant)!!

    abstract val variants: Array<T>

    open val variantsAll: Array<T>
        get() = variants

    abstract val typeProperty: PropertyEnum<T>

    override fun damageDropped(state: IBlockState) = state.getValue(typeProperty).ordinal

    override fun getSubBlocks(itemIn: Item, tab: CreativeTabs, list: MutableList<ItemStack>) {
        list.addAll(this.variants.map { ItemStack(itemIn, 1, it.ordinal) })
    }

    override fun getMetaFromState(state: IBlockState) = state.getValue(typeProperty).ordinal % 16

    override fun getStateFromMeta(meta: Int) = this[variantsAll[meta]]
}

abstract class MVariantBlockContainer<T>(name: String, material: Material, hardness: Float, resistance: Float) : MVariantBlock<T>(name, material, hardness, resistance) where T : Enum<T>, T : IStringSerializable {

    abstract override fun hasTileEntity(state: IBlockState): Boolean

    abstract override fun createTileEntity(world: World, state: IBlockState): TileEntity

}
