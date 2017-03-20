package be.bluexin.mekre.common.items

import be.bluexin.mekre.Refs
import be.bluexin.mekre.common.items.crafting.Alloy
import be.bluexin.mekre.common.items.crafting.AlloyVariants
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

    val alloy = Alloy()


    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        GameRegistry.register(alloy)
    }

    @SideOnly(Side.CLIENT)
    fun clientInit() {
        var m: ModelResourceLocation

        AlloyVariants.values().forEachIndexed { i, it ->
            m = ModelResourceLocation(Refs.getResourceLocation(alloy.registryName.resourcePath + '_' + it), "inventory")
            ModelLoader.setCustomModelResourceLocation(alloy, i, m)
         }
    }
}
