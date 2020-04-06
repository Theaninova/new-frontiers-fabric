package de.wulkanat.new_frontiers.world.biome.galaxy.atmospheric.organic

import de.wulkanat.new_frontiers.abstraction.block.NFBiome
import de.wulkanat.new_frontiers.extensions.kotlin.random.nextBoolean
import de.wulkanat.new_frontiers.extensions.kotlin.random.normalDistributedFloat
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import kotlin.random.Random
import kotlin.random.nextInt

class BiomeProceduralHills(random: Random) : NFBiome(
    name = "hills",

    surfaceConfig = SurfaceBuilder.STONE_CONFIG,

    downfall = if (random.nextBoolean(0.9)) Precipitation.RAIN else Precipitation.SNOW,
    downfallFrequency = random.normalDistributedFloat(expectation = 0.3),

    category = Category.EXTREME_HILLS,

    depth = random.normalDistributedFloat(expectation = 0.9),
    scale = random.normalDistributedFloat(expectation = 0.9),
    temperature = random.normalDistributedFloat(),

    waterColor = random.nextInt(0..9999999),
    waterFogColor = random.nextInt(0..9999999),

    random = random,
    randomFeatures = mapOf(
        Pair(0.9, DefaultBiomeFeatures::addLandCarvers),
        Pair(0.8, DefaultBiomeFeatures::addDefaultLakes)
    )
)