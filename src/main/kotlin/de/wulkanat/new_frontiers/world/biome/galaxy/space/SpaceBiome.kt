package de.wulkanat.new_frontiers.world.biome.galaxy.space

import de.wulkanat.new_frontiers.abstraction.block.NFBiome
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import kotlin.random.Random

class SpaceBiome : NFBiome(
    name = "space",

    surfaceConfig = SurfaceBuilder.AIR_CONFIG,
    surfaceBuilder = SurfaceBuilder.NOPE,

    downfall = Precipitation.NONE,
    category = Category.NONE,

    depth = 0F,
    scale = 0F,
    temperature = 0F,

    waterColor = 4159204,
    waterFogColor = 329011,

    random = Random.Default,
    randomFeatures = mapOf()
)