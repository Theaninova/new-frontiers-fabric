package de.wulkanat.new_frontiers.extensions.server

import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.dimension.DimensionType

fun MinecraftServer.getWorldsRaw(): java.util.Map<DimensionType, ServerWorld> {
    return MinecraftServer::class.java.getDeclaredField("worlds").let {
        it.isAccessible = true
        val value = it.get(this)
        @Suppress("UNCHECKED_CAST")
        return@let value as java.util.Map<DimensionType, ServerWorld>
    }
}