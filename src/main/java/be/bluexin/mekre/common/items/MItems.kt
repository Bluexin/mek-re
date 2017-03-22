package be.bluexin.mekre.common.items

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.items.crafting.Alloy
import be.bluexin.mekre.common.items.crafting.Ingot
import be.bluexin.mekre.common.items.itemblocks.MetalBlockItem
import be.bluexin.mekre.common.items.itemblocks.OreItem
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MItems {
    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        GameRegistry.register(Alloy)
        GameRegistry.register(Ingot)
        GameRegistry.register(OreItem)
        GameRegistry.register(MetalBlockItem)
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {
        registerModel(Alloy)
        registerModel(Ingot)
    }

    @SideOnly(Side.CLIENT)
    private fun registerModel(item: MItem) = if (item is IItemVariant<*>) item.variants.forEachIndexed { i, it ->
        ModelLoader.setCustomModelResourceLocation(item, i,
                ModelResourceLocation(Refs.getResourceLocation("${item.registryName.resourcePath}_$it"), "inventory"))
    }
    else ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(Refs.getResourceLocation(item.registryName.resourcePath), "inventory"))
}
