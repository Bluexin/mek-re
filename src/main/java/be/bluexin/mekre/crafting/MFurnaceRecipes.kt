package be.bluexin.mekre.crafting

import be.bluexin.mekre.blocks.Ore
import be.bluexin.mekre.items.crafting.Ingot
import be.bluexin.mekre.items.itemblocks.OreItem
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal object MFurnaceRecipes {

    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        Ore.variants.forEach { GameRegistry.addSmelting(OreItem[it], Ingot[it.metal], it.harvestLevel / 2.0F) }
    }
}
