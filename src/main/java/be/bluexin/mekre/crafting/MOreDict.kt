package be.bluexin.mekre.crafting

import be.bluexin.mekre.AlloyTypes
import be.bluexin.mekre.MetalType
import be.bluexin.mekre.items.crafting.Alloy
import be.bluexin.mekre.items.crafting.Ingot
import be.bluexin.mekre.items.itemblocks.MetalBlockItem
import net.minecraftforge.oredict.OreDictionary

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal object MOreDict {

    private var initialized = false

    fun init() {
        if (initialized) throw IllegalStateException("Items already initialized.")
        initialized = true

        MetalType.values().forEach {
            OreDictionary.registerOre("ingot${it.name.capitalize()}", Ingot[it])
            if (it.hasBlockForm) OreDictionary.registerOre("block${it.name.capitalize()}", MetalBlockItem[it])
        }
        AlloyTypes.values().forEach {
            OreDictionary.registerOre("alloy${it.name.capitalize()}", Alloy[it])
        }
    }

}
