package de.wulkanat.new_frontiers

import de.wulkanat.new_frontiers.world.dimension.model.GalacticPosition
import de.wulkanat.new_frontiers.world.dimension.model.Galaxy
import kotlin.system.measureTimeMillis

// for testing purposes

fun main() {
    val galaxy = Galaxy(6209206)

    println(measureTimeMillis {
        galaxy.getNearSystems(GalacticPosition(0.0, 0.0, 0.0), 20.0)
    })
}