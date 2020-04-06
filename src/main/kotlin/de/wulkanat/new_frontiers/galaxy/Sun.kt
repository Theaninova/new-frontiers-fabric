package de.wulkanat.new_frontiers.galaxy

import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedInt
import kotlin.random.Random

class Sun(seed: Int, val system: System) {
    private val random: Random = Random(seed)

    val planets: List<Planet>

    init {
        val planetCount = random.normalDistributedInt(0..20, expectation = 5.7, variance = 4.6)
        planets = List(planetCount) {
            Planet(random.nextInt(), this)
        }
    }
}