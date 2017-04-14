package be.bluexin.mekre.crafting

import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
val smelterRecipes = mutableListOf(// TODO: move to recipe registry
        MachineRecipe(arrayListOf(ItemStack(Blocks.GOLD_ORE)), arrayListOf(ItemStack(Items.GOLD_INGOT, 2)), 60, 20),
        MachineRecipe(arrayListOf(ItemStack(Blocks.IRON_ORE)), arrayListOf(ItemStack(Items.IRON_INGOT, 2)), 60, 20)
)
