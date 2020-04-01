package de.wulkanat.new_frontiers.world.dimension.model

import de.wulkanat.new_frontiers.animation.Interpolator
import de.wulkanat.new_frontiers.extensions.kotlin.random.runRandomly
import de.wulkanat.new_frontiers.world.dimension.model.noise.RadialGradient
import kotlin.math.roundToInt
import kotlin.random.Random

class Galaxy(seed: Int) {
    private val gradient = RadialGradient(50_000.0, Interpolator.linear())
    // val simplex = SimplexNoiseSampler(Random(seed.toLong()))

    fun getNearSystems(position: GalacticPosition, distance: Double): List<System> {
        val out: ArrayList<System> = ArrayList()

        for (pos in cubeFrom(position, distance)) {
            val rand = Random(pos.hashCode())
            val pos2 = GalacticPosition(pos.x_ly + rand.nextDouble(), pos.y_ly + rand.nextDouble(), pos.z_ly + rand.nextDouble())

            rand.runRandomly(densityAt(pos)) {
                out.add(System(pos2))
            }
        }

        return out
    }

    fun densityAt(position: GalacticPosition): Double {
        return 0.1 * gradient.sample(position.x_ly, position.y_ly, position.z_ly)
    }

    private fun cubeFrom(position: GalacticPosition, size: Double): List<GalacticPosition> {
        return List((size*size*size).roundToInt()) {
            GalacticPosition(
                it / (size * size)  + position.x_ly - size / 2,
                (it / size) % size  + position.y_ly - size / 2,
                it % size         + position.z_ly -  size / 2
            )
        }
    }
}