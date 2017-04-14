package be.bluexin.mekre.tiles

import be.bluexin.mekre.crafting.MachineRecipe
import be.bluexin.mekre.crafting.smelterRecipes
import com.teamwizardry.librarianlib.common.util.autoregister.TileRegister
import com.teamwizardry.librarianlib.common.util.saving.Savable
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@TileRegister("mekre:smelterte")
@Savable
class SmelterTE : OperatingTE(9001) {

    override val recipes: List<MachineRecipe>
        get() = smelterRecipes

    override fun hasIngredients(recipe: MachineRecipe) = recipe.input.all { s ->
        inputRange.any {
            val r = extractItem(it, s.stackSize, true)
            r?.isItemEqual(s) ?: false && r!!.stackSize == s.stackSize
        }
    }

    override fun consumeIngredients(recipe: MachineRecipe) = recipe.input.forEach { s ->
        inputRange.any {
            val r = extractItem(it, s.stackSize, false)
            r?.isItemEqual(s) ?: false && r!!.stackSize == s.stackSize
        }
    }

    override fun grantResults(recipe: MachineRecipe) {
        recipe.output.forEach {
            var s: ItemStack? = it.copy()

            outputRange.any {
                s = insertOutput(it, s, false)
                s == null
            }
            if (s != null) world.spawnEntity(EntityItem(world, pos.x + 0.5, pos.y + 1.5, pos.z + 0.5, s))
        }
    }
}
