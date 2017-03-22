package be.bluexin.mekre.common.crafting

import be.bluexin.mekre.common.items.crafting.Ingot
import be.bluexin.mekre.common.items.crafting.MetalType
import be.bluexin.mekre.common.items.itemblocks.MetalBlockItem
import net.minecraftforge.oredict.OreDictionary

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
object MOreDict {

    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        MetalType.values().forEach {
            OreDictionary.registerOre("ingot${it.name.capitalize()}", Ingot[it])
            if (it.hasBlockForm) OreDictionary.registerOre("block${it.name.capitalize()}", MetalBlockItem[it])
        }
    }

}
