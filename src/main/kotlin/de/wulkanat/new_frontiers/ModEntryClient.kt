package de.wulkanat.new_frontiers

import de.wulkanat.new_frontiers.command.CommandCreateDimension
import de.wulkanat.new_frontiers.command.CommandPrintDim
import de.wulkanat.new_frontiers.command.CommandTPDim
import de.wulkanat.new_frontiers.command.CommandTPRaw
import net.fabricmc.fabric.api.registry.CommandRegistry

@Suppress("unused")
fun init() {
    CommandRegistry.INSTANCE.register(false) {
        CommandTPDim.register(it)
    }
    CommandRegistry.INSTANCE.register(false) {
        CommandCreateDimension.register(it)
    }
    CommandRegistry.INSTANCE.register(false) {
        CommandPrintDim.register(it)
    }
    CommandRegistry.INSTANCE.register(false) {
        CommandTPRaw.register(it)
    }
}
