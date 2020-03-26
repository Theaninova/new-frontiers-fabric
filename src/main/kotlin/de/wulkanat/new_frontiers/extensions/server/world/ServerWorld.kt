package de.wulkanat.new_frontiers.extensions.server.world

import net.minecraft.server.world.ServerWorld
import net.minecraft.world.WorldSaveHandler

fun ServerWorld.getWorldSaveHandler(): WorldSaveHandler {
    return ServerWorld::class.java.getDeclaredField("worldSaveHandler").let {
        it.isAccessible = true
        val value = it.get(this)
        @Suppress("UNCHECKED_CAST")
        return@let value as WorldSaveHandler
    }
}