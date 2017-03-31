package be.bluexin.mekre.common.blocks

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.blocks.states.IPropertyWrapper
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

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        GameRegistry.register(Ore)
        GameRegistry.register(MetalBlock)
        MachineBlocksHolder.blocks.forEach { GameRegistry.register(it) }
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {
        registerModel(Ore)
        registerModel(MetalBlock)
        MachineBlocksHolder.blocks.forEach { registerModel(it) }
    }

    @SideOnly(Side.CLIENT)
    fun registerModel(block: MBlock) = if (block is MVariantBlock<*>) {
        block.blockState.validStates.forEach {
            var idx: Int = 0
            val s = it.properties.filter {
                val prop = it.key
                if (prop is IPropertyWrapper) {
                    if (prop.isItemIdx) idx = (it.value as Enum<*>).ordinal
                    prop.renderDepends
                } else false
            }.map {
                "${it.key.getName()}=${it.value}"
            }.joinToString(",")

//            println("Registering model for ${block.registryName.resourcePath} with variant [$s].")

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), idx,
                    ModelResourceLocation(Refs.getResourceLocation(block.registryName.resourcePath), s))
        }
    } else TODO()
}
