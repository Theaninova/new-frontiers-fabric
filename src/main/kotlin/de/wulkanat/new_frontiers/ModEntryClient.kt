package de.wulkanat.new_frontiers

import de.wulkanat.new_frontiers.command.*
import net.fabricmc.fabric.api.registry.CommandRegistry

@Suppress("unused")
fun init() {
    CommandRegistry.INSTANCE.register(false) {
        CommandTPDim.register(it)
        CommandCreateDimension.register(it)
        CommandPrintDim.register(it)
        CommandTPRaw.register(it)
        CommandTravel.register(it)
    }
}
