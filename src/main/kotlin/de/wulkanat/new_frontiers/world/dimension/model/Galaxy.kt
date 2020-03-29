package de.wulkanat.new_frontiers.world.dimension.model

import de.wulkanat.new_frontiers.animation.Interpolator
import de.wulkanat.new_frontiers.extensions.kotlin.random.runRandomly
import de.wulkanat.new_frontiers.world.dimension.model.noise.RadialGradient
import kotlin.random.Random

class Galaxy(seed: Int) {
    private val gradient = RadialGradient(Int.MAX_VALUE, Interpolator.sinusial())
    // val simplex = SimplexNoiseSampler(Random(seed.toLong()))

    fun getNearSystems(position: GalacticPosition): List<System> {
        val out: ArrayList<System> = ArrayList()

        for (pos in cubeFrom(position)) {
            val systemSeed = pos.hashCode()

            Random(pos.hashCode()).runRandomly(0.1 * gradient.sample(pos.x_ly, pos.y_ly, pos.z_ly)) {
                out.add(System(pos))
            }
        }

        return out
    }

    private fun cubeFrom(position: GalacticPosition): List<GalacticPosition> {
        return List(16*16*16) {
            GalacticPosition(
                it / (16 * 16)  + position.x_ly - 8,
                (it / 16) % 16  + position.y_ly - 8,
                it % 16         + position.z_ly - 8
            )
        }
    }
}