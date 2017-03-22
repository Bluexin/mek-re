package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
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
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {

        registerModel(ore)
    }

    @SideOnly(Side.CLIENT)
    fun registerModel(block: MBlock) = if (block is IBlockVariant<*>) block.variants.forEachIndexed { i, it ->
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
                ModelResourceLocation(Refs.getResourceLocation(block.registryName.resourcePath), "type=$it"))
    } else TODO()
}
