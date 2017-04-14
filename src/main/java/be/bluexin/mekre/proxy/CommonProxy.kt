package be.bluexin.mekre.proxy

import be.bluexin.mekre.Refs
import be.bluexin.mekre.blocks.MBlocks
import be.bluexin.mekre.crafting.MCraftingRecipes
import be.bluexin.mekre.crafting.MFurnaceRecipes
import be.bluexin.mekre.crafting.MOreDict
import be.bluexin.mekre.items.MItems
import be.bluexin.mekre.world.GenerationHandler
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal open class CommonProxy {

    open fun preInit() {
        MBlocks.init()
        MItems.init()
        initTEs()
    }

    open fun init() {
        GameRegistry.registerWorldGenerator(GenerationHandler, 1)
        MOreDict.init()
        MFurnaceRecipes.init()
        MCraftingRecipes.init()
    }

    private fun initTEs() {
//        initTE(SmelterTE::class.java)
    }

    private fun initTE(clazz: Class<out TileEntity>) = GameRegistry.registerTileEntity(clazz, Refs.getResourceLocation(clazz.simpleName).toString())
}
