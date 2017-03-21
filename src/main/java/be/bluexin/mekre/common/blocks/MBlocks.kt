package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.items.itemblocks.OreItem
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MBlocks {

    private var initialized = false

    val ore = Ore()

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        GameRegistry.register(ore)
        GameRegistry.register(OreItem(ore))
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {
        var m: ModelResourceLocation

        OreType.values().forEachIndexed { i, it ->
            m = ModelResourceLocation(Refs.getResourceLocation(ore.registryName.resourcePath), "type=$it")
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ore), i, m)
        }
    }
}
