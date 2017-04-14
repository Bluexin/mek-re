package be.bluexin.mekre.containers

import be.bluexin.mekre.Refs
import be.bluexin.mekre.guis.MGuiContainer
import be.bluexin.mekre.tiles.OperatingTE
import com.teamwizardry.librarianlib.common.container.ContainerBase
import com.teamwizardry.librarianlib.common.container.GuiHandler
import com.teamwizardry.librarianlib.common.container.InventoryWrapper
import com.teamwizardry.librarianlib.common.container.builtin.BaseWrappers
import net.minecraft.entity.player.EntityPlayer

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
class OperatingTEContainer(player: EntityPlayer, te: OperatingTE) : ContainerBase(player) {

    val invPlayer = BaseWrappers.player(player)
    val invBlock = OperatingTEWrapper(te)

    init {
        addSlots(invPlayer)
        addSlots(invBlock)

        transferRule().from(invPlayer.main).from(invPlayer.hotbar).deposit(invBlock.input).filter {
            true // TODO: "can be processed?"
        }

        transferRule().from(invBlock.input).from(invBlock.output).deposit(invPlayer.main).deposit(invPlayer.hotbar)
    }

    companion object {
        val NAME = Refs.getResourceLocation("operatingtecontainer")

        init {
            GuiHandler.registerBasicContainer(NAME, { player, pos, tile -> OperatingTEContainer(player, tile as OperatingTE) }, { player, container -> MGuiContainer(container) })
        }
    }
}

class OperatingTEWrapper(inventory: OperatingTE) : InventoryWrapper(inventory) {
    val input = slots[inventory.inputRange]
    val output = slots[inventory.outputRange]
}
