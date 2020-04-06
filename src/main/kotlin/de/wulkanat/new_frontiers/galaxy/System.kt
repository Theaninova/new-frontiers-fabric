package de.wulkanat.new_frontiers.galaxy

import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedInt
import de.wulkanat.new_frontiers.galaxy.GalacticPosition
import de.wulkanat.new_frontiers.galaxy.Sun
import kotlin.random.Random

class System(val position: GalacticPosition) {
    private val random: Random = Random(position.hashCode())
    val suns: List<Sun>

    init {
        val sunCount = random.normalDistributedInt(1..4, expectation = 1.8, variance = 0.5)
        suns = List(sunCount) {
            Sun(random.nextInt(), this)
        }
    }
}