package be.bluexin.mekre

import be.bluexin.mekre.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@Mod(modid = MekRe.MODID, name = MekRe.MODNAME, version = MekRe.VERSION, dependencies = MekRe.DEPS)
object MekRe {
    const val MODID = "mekre"
    const val MODNAME = "Mek:Re"
    const val VERSION = "0.1"
    const val DEPS = "required-after:saomclib@[1.0.5b,);required-after:tesla@[1.2.1.50,)"

    @SidedProxy(clientSide = "be.bluexin.mekre.proxy.ClientProxy", serverSide = "be.bluexin.mekre.proxy.CommonProxy")
    internal lateinit var proxy: CommonProxy

    @Mod.EventHandler
    fun preInit(e: FMLPreInitializationEvent) {
        proxy.preInit()
    }

    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        proxy.init()
    }

    @Mod.EventHandler
    fun serverStart(e: FMLServerStartingEvent) {

    }

    @JvmStatic
    @Mod.InstanceFactory
    fun shenanigan() = this
}
