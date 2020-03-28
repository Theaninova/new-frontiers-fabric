package de.wulkanat.new_frontiers.world.dimension.model

import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedInt
import kotlin.random.Random

class System(seed: Int, val position: GalacticPosition) {
    private val random: Random = Random(seed)
    val suns: List<Sun>

    init {
        val sunCount = random.normalDistributedInt(1..4, expectation = 1.8, variance = 0.5)
        suns = List(sunCount) {
            Sun(random.nextInt(), this)
        }
    }
}