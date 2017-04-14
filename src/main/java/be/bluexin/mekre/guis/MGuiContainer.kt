package be.bluexin.mekre.guis

import be.bluexin.mekre.Refs
import be.bluexin.mekre.containers.OperatingTEContainer
import be.bluexin.mekre.tiles.OperatingTE
import com.teamwizardry.librarianlib.client.gui.GuiComponent
import com.teamwizardry.librarianlib.client.gui.components.ComponentSprite
import com.teamwizardry.librarianlib.client.gui.components.ComponentSpriteProgressBar
import com.teamwizardry.librarianlib.client.gui.components.ComponentText
import com.teamwizardry.librarianlib.client.gui.components.ComponentVoid
import com.teamwizardry.librarianlib.client.guicontainer.GuiContainerBase
import com.teamwizardry.librarianlib.client.guicontainer.builtin.BaseLayouts
import com.teamwizardry.librarianlib.client.sprite.Texture
import com.teamwizardry.librarianlib.common.util.MethodHandleHelper
import com.teamwizardry.librarianlib.common.util.plus
import com.teamwizardry.librarianlib.common.util.vec
import net.minecraft.client.resources.I18n

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
open class MGuiContainer(inventorySlotsIn: OperatingTEContainer) : GuiContainerBase(inventorySlotsIn, 176, 166) {

    private val slots = mutableListOf<ComponentVoid>()
    private val comp = MethodHandleHelper.wrapperForGetter(GuiContainerBase::class.java, "mainScaleWrapper")(this) as ComponentVoid

    init {
        val te = inventorySlotsIn.invBlock.inventory as OperatingTE
        val bg = ComponentSprite(BG, 0, 0)
        mainComponents.add(bg)

        bg.add(ComponentSprite(SL_INPUT, 55, 16))
        bg.add(ComponentSprite(SL_POWER, 55, 52))
        bg.add(ComponentSprite(SG_POWER, 55, 52))
        bg.add(ComponentSprite(SL_OUTPUT_L, 111, 30))

        val inventory = BaseLayouts.player(inventorySlotsIn.invPlayer)
        bg.add(inventory.root)
        slots.add(inventory.root)
        inventory.main.pos = vec(8, 84)

        val input = BaseLayouts.grid(inventorySlotsIn.invBlock.input, 7)
        input.root.pos = vec(56, 17)
        slots.add(input.root)
        bg.add(input.root)

        val output = BaseLayouts.grid(inventorySlotsIn.invBlock.output, 7)
        output.root.pos = vec(116, 35)
        slots.add(output.root)
        bg.add(output.root)

        bg.add(ComponentText(88, 6, horizontal = ComponentText.TextAlignH.CENTER).`val`(I18n.format(te.unlocalizedName)))

        bg.add(ComponentSprite(PROGRESS_BG, 77, 37))
        val progressBar = ComponentSpriteProgressBar(PROGRESS_FG, 78, 38)
        progressBar.direction.setValue(ComponentSpriteProgressBar.ProgressDirection.X_POS)
        progressBar.progress.func { te.currentOperations[0]?.progress ?: 0f }
        progressBar.tooltip { I18n.format("gui.progress", ((te.currentOperations[0]?.progress ?: 0f) * 100).toInt()) }
        bg.add(progressBar)

        bg.add(ComponentSprite(POWER_BG, 164, 15))
        val powerBar = ComponentSpriteProgressBar(POWER_FG, 165, 16)
        powerBar.direction.setValue(ComponentSpriteProgressBar.ProgressDirection.Y_NEG)
        powerBar.progress.func { te.energyStored.toFloat() / te.maxEnergyStored }
        powerBar.tooltip { I18n.format("gui.energy", te.energyStored, te.maxEnergyStored) }
        bg.add(powerBar)
    }

    override fun initGui() {
        var pos = comp.pos
        slots.forEach { it.pos += pos }

        super.initGui()

        pos = comp.pos
        guiLeft = pos.xi
        guiTop = pos.yi
        slots.forEach { it.pos -= pos }
    }

    companion object {
        private val RES_MAIN = Texture(Refs.getResourceLocation("textures/guis/gui_basic_machine.png"))
        private val BG = RES_MAIN.getSprite("bg", 176, 166)

        private val RES_PROGRESS = Texture(Refs.getResourceLocation("textures/guis/parts/gui_progress.png"))
        private val PROGRESS_BG = RES_PROGRESS.getSprite("regular_bg", 25, 9)
        private val PROGRESS_FG = RES_PROGRESS.getSprite("regular_fg", 23, 7)

        private val RES_POWER = Texture(Refs.getResourceLocation("textures/guis/parts/gui_power_bar.png"))
        private val POWER_BG = RES_POWER.getSprite("regular_bg", 6, 54)
        private val POWER_FG = RES_POWER.getSprite("regular_fg", 4, 52)

        private val RES_SLOTS = Texture(Refs.getResourceLocation("textures/guis/parts/gui_slots.png"))
        private val SL_INPUT = RES_SLOTS.getSprite("input", 18, 18)
        private val SL_POWER = RES_SLOTS.getSprite("power", 18, 18)
        private val SL_OUTPUT_L = RES_SLOTS.getSprite("output_large", 26, 26)
        private val SG_POWER = RES_SLOTS.getSprite("sign_power", 18, 18)
    }
}

inline fun GuiComponent<*>.tooltip(crossinline callback: () -> String) {
    this.BUS.hook(GuiComponent.PostDrawEvent::class.java) {
        if (this.mouseOver) this.setTooltip(listOf(callback()))
    }
}

fun GuiComponent<*>.tooltip(text: String) {
    this.BUS.hook(GuiComponent.PostDrawEvent::class.java) {
        if (this.mouseOver) this.setTooltip(listOf(text))
    }
}
