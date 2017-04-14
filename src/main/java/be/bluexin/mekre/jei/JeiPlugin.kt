package be.bluexin.mekre.jei

import com.teamwizardry.librarianlib.client.guicontainer.GuiContainerBase
import mezz.jei.api.BlankModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.gui.IAdvancedGuiHandler
import java.awt.Rectangle

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@JEIPlugin
/*object*/ class JeiPlugin : BlankModPlugin() {

    override fun register(registry: IModRegistry) {
        super.register(registry)

        registry.addAdvancedGuiHandlers(JeiGuiHandler)
    }
}

object JeiGuiHandler : IAdvancedGuiHandler<GuiContainerBase> {

    override fun getIngredientUnderMouse(gui: GuiContainerBase, mouseX: Int, mouseY: Int) = null
    // TODO: change when using fluids

    override fun getGuiContainerClass() = GuiContainerBase::class.java

    override fun getGuiExtraAreas(gui: GuiContainerBase) =
            listOf<Rectangle>(/*Rectangle(200, 200, 600, 100)*/)
}
