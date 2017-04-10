package be.bluexin.mekre.crafting

import be.bluexin.mekre.blocks.MetalBlock
import be.bluexin.mekre.items.crafting.Ingot
import be.bluexin.mekre.items.itemblocks.MetalBlockItem
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.ShapelessOreRecipe

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal object MCraftingRecipes {

    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        MetalBlock.variants.forEach {
            val n = it.name.capitalize()
            GameRegistry.addRecipe(ShapelessOreRecipe(MetalBlockItem[it],
                    "ingot$n", "ingot$n", "ingot$n",
                    "ingot$n", "ingot$n", "ingot$n",
                    "ingot$n", "ingot$n", "ingot$n"))
            GameRegistry.addRecipe(ShapelessOreRecipe(Ingot[it, 9], "block$n"))
        }
    }
}
