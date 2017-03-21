package be.bluexin.mekre.proxy

import be.bluexin.mekre.common.blocks.MBlocks
import be.bluexin.mekre.common.crafting.MFurnaceRecipes
import be.bluexin.mekre.common.items.MItems
import be.bluexin.mekre.common.world.GenerationHandler
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal open class CommonProxy {

    open fun preInit() {
        MItems.init()
        MBlocks.init()
    }

    open fun init() {
        GameRegistry.registerWorldGenerator(GenerationHandler, 1)
        MFurnaceRecipes.init()
    }
}
