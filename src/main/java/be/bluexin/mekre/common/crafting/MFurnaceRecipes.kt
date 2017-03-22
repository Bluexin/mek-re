package be.bluexin.mekre.common.crafting

import be.bluexin.mekre.common.blocks.Ore
import be.bluexin.mekre.common.items.crafting.Ingot
import be.bluexin.mekre.common.items.itemblocks.OreItem
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

        Ore.variants.forEach { GameRegistry.addSmelting(OreItem[it], Ingot[it.metal], it.harvestLevel / 2.0F) }
    }
}
