package de.wulkanat.new_frontiers.world.biome.procedural

import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import kotlin.random.Random

class BiomeProceduralPlains(random: Random) : Biome(
    Settings().configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
        // TODO: none
        .precipitation(if (random.nextBoolean()) Precipitation.RAIN else Precipitation.SNOW)
        .category(Category.PLAINS)
        .depth(random.nextFloat())
        .scale(random.nextFloat())
        .temperature(random.nextFloat())
        .downfall(random.nextFloat())
        .waterColor(random.nextInt(0, 9999999))
        .waterFogColor(random.nextInt(0, 9999999))
        .parent(null)
) {
    init {
        random.nextBoolean().runIf { DefaultBiomeFeatures.addLandCarvers(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addDefaultLakes(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addDungeons(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addDefaultFlowers(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addMineables(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addDefaultOres(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addDefaultVegetation(this) }
        random.nextBoolean().runIf { DefaultBiomeFeatures.addSprings(this) }
    }

    private inline fun Boolean.runIf(run: () -> Unit) {
        if (this) run()
    }
}