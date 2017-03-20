package be.bluexin.mekre.proxy

import be.bluexin.mekre.common.items.MItems

/**
 * Part of mek_re by Bluexin, released under GNU GPLv3.
 *
 * @author Bluexin
 */
internal open class CommonProxy {

    open fun preInit() {
        MItems.init()
    }
}
