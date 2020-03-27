package de.wulkanat.new_frontiers.world.dimension.model

import com.google.gson.JsonObject

interface IProcSerializable<T> {
    fun serialize(): JsonObject
    val fromJson: (it: JsonObject) -> T
}