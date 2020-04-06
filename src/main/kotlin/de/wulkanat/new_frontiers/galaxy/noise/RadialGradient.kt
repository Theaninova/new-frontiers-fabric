package de.wulkanat.new_frontiers.galaxy.noise

import de.wulkanat.new_frontiers.extensions.kotlin.math.normalize
import kotlin.math.pow
import kotlin.math.sqrt

class RadialGradient(val radius: Double, private val interpolation: (Double) -> Double) {
    fun sample(x: Double, y: Double, z: Double): Double {
        return (radius - sqrt(x.pow(2) + y.pow(2) + z.pow(2))).normalize(0.0..radius)
    }
}