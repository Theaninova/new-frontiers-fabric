package de.wulkanat.new_frontiers.world.biome.galaxy.atmospheric.organic

import de.wulkanat.new_frontiers.abstraction.block.NFBiome
import de.wulkanat.new_frontiers.extensions.kotlin.random.nextBoolean
import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedFloat
import net.minecraft.world.biome.DefaultBiomeFeatures
import kotlin.random.Random
import kotlin.random.nextInt

class BiomeProceduralPlains(random: Random) : NFBiome(
    name = "plains",

    downfall = if (random.nextBoolean(0.9)) Precipitation.RAIN else Precipitation.SNOW,
    downfallFrequency = random.normalDistributedFloat(expectation = 0.3),

    category = Category.PLAINS,

    depth = random.normalDistributedFloat(),
    scale = random.normalDistributedFloat(),
    temperature = random.normalDistributedFloat(),

    waterColor = random.nextInt(0..9999999),
    waterFogColor = random.nextInt(0..9999999),

    random = random,
    randomFeatures = mapOf(
        Pair(0.9, DefaultBiomeFeatures::addLandCarvers),
        Pair(0.8, DefaultBiomeFeatures::addDefaultLakes),
        Pair(0.9, DefaultBiomeFeatures::addDungeons),
        Pair(0.9, DefaultBiomeFeatures::addDefaultFlowers)
    )
)