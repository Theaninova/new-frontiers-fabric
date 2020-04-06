package de.wulkanat.new_frontiers.world.dimension.galaxy

import de.wulkanat.new_frontiers.abstraction.block.NFDynamicDimension
import de.wulkanat.new_frontiers.world.biome.galaxy.atmospheric.organic.BiomeProceduralHills
import de.wulkanat.new_frontiers.world.biome.galaxy.atmospheric.organic.BiomeProceduralPlains
import net.minecraft.world.World
import net.minecraft.world.dimension.DimensionType
import kotlin.random.Random

class DimensionPlanetOrganic(random: Random, type: DimensionType, world: World) : NFDynamicDimension(
    random = random,
    type = type,
    world = world,

    biomes = listOf(
        BiomeProceduralPlains(random),
        BiomeProceduralHills(random)
    )
)