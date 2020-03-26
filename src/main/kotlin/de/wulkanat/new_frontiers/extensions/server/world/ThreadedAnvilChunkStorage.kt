package de.wulkanat.new_frontiers.extensions.server.world

import net.minecraft.server.WorldGenerationProgressListener
import net.minecraft.server.world.ThreadedAnvilChunkStorage

fun ThreadedAnvilChunkStorage.getWorldGenerationProgressListener(): WorldGenerationProgressListener {
    return ThreadedAnvilChunkStorage::class.java.getDeclaredField("worldGenerationProgressListener").let {
        it.isAccessible = true
        val value = it.get(this)
        @Suppress("UNCHECKED_CAST")
        return@let value as WorldGenerationProgressListener
    }
}