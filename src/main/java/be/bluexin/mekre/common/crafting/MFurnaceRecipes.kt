package be.bluexin.mekre.common.crafting

import be.bluexin.mekre.common.blocks.MBlocks
import be.bluexin.mekre.common.items.MItems
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MFurnaceRecipes {

    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        MBlocks.ore.variants.forEach { GameRegistry.addSmelting(MItems.oreItem[it], MItems.ingot[it.ingot], it.harvestLevel / 2.0F) }
    }
}
