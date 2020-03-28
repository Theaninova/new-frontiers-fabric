package de.wulkanat.new_frontiers.world.dimension.model

import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedInt
import kotlin.random.Random

class Planet(seed: Int, val sun: Sun) {
    private val random: Random = Random(seed)

    val moons: List<Moon>

    init {
        val moonCount = random.normalDistributedInt(0..15, expectation = 6.5, variance = 4.6)
        moons = List(moonCount) {
            Moon(random.nextInt(), this)
        }
    }
}