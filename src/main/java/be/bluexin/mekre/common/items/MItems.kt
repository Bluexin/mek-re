package be.bluexin.mekre.common.items

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.items.crafting.Alloy
import be.bluexin.mekre.common.items.crafting.AlloyVariants
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
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

    val alloy = Alloy()


    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        GameRegistry.register(alloy)

        val iss: ItemStack = alloy[AlloyVariants.REINFORCED]
        val iss2: ItemStack = alloy[AlloyVariants.ATOMIC, 2]
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {
        registerModel(alloy)
    }

    @SideOnly(Side.CLIENT)
    private fun registerModel(item: MItem) = if (item is IItemVariant<*>) (item).forEachIndexed { i, it ->
        ModelLoader.setCustomModelResourceLocation(item, i,
                ModelResourceLocation(Refs.getResourceLocation("${item.registryName.resourcePath}_$it"), "inventory"))
    }
    else ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(Refs.getResourceLocation(item.registryName.resourcePath), "inventory"))
}
