package be.bluexin.mekre.proxy

import be.bluexin.mekre.blocks.MBlocks
import be.bluexin.mekre.items.MItems

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
@Suppress("unused")
internal class ClientProxy : CommonProxy() {
    override fun preInit() {
        super.preInit()

        MItems.clientInit()
        MBlocks.clientInit()
    }
}
