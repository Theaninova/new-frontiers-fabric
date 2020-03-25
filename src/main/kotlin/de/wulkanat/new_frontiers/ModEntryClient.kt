package de.wulkanat.new_frontiers

import de.wulkanat.new_frontiers.command.CommandRecursiveCreate
import de.wulkanat.new_frontiers.command.CommandTPDim
import de.wulkanat.new_frontiers.world.dimension.DimensionBertiBotts
import net.fabricmc.fabric.api.registry.CommandRegistry

const val MOD_ID = "new_frontiers"

@Suppress("unused")
fun init() {
    CommandRegistry.INSTANCE.register(false) {
        CommandTPDim.register(it)
    }
    CommandRegistry.INSTANCE.register(false) {
        CommandRecursiveCreate.register(it)
    }
    DimensionBertiBotts.register()
}
