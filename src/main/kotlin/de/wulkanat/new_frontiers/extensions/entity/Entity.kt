package de.wulkanat.new_frontiers.extensions.entity

import net.minecraft.entity.Entity
import net.minecraft.world.dimension.DimensionType

fun Entity.changePositionDirect(dimensionType: DimensionType) {
    val pos = this.pos
    this.changeDimension(dimensionType)
    this.teleport(pos.x, pos.y, pos.z)
}