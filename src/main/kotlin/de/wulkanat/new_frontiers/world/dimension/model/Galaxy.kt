package de.wulkanat.new_frontiers.world.dimension.model

import de.wulkanat.new_frontiers.animation.Interpolator
import de.wulkanat.new_frontiers.extensions.kotlin.random.runRandomly
import de.wulkanat.new_frontiers.world.dimension.model.noise.RadialGradient
import kotlin.random.Random

class Galaxy(seed: Int) {
    private val gradient = RadialGradient(50_000.0, Interpolator.linear())
    // val simplex = SimplexNoiseSampler(Random(seed.toLong()))

    fun getNearSystems(position: GalacticPosition): List<System> {
        val out: ArrayList<System> = ArrayList()

        for (pos in cubeFrom(position)) {
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