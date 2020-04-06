package de.wulkanat.new_frontiers.galaxy

data class GalacticPosition(val x_ly: Double, val y_ly: Double, val z_ly: Double) {
    operator fun minus(other: GalacticPosition): GalacticPosition {
        return GalacticPosition(
            x_ly - other.x_ly,
            y_ly - other.y_ly,
            z_ly - other.z_ly
        )
    }

    operator fun plus(other: GalacticPosition): GalacticPosition {
        return GalacticPosition(
            x_ly + other.x_ly,
            y_ly + other.y_ly,
            z_ly + other.z_ly
        )
    }
}