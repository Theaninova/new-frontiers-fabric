package de.wulkanat.new_frontiers.world.dimension.model.noise

import de.wulkanat.new_frontiers.extensions.kotlin.math.tempNormalized
import kotlin.math.pow
import kotlin.math.sqrt

class RadialGradient(val radius: Int, private val interpolation: (Double) -> Double) {
    fun sample(x: Double, y: Double, z: Double): Double {
        return sqrt(x.pow(2) + y.pow(2) + z.pow(2)).tempNormalized(0.0..1.0, interpolation)
    }
}