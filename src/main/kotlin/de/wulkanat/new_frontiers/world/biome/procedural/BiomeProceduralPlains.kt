package de.wulkanat.new_frontiers.world.biome.procedural

import de.wulkanat.new_frontiers.procedural.nextBoolean
import de.wulkanat.new_frontiers.procedural.normalDistributedFloat
import de.wulkanat.new_frontiers.procedural.runRandomly
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import kotlin.random.Random
import kotlin.random.nextInt

class BiomeProceduralPlains(random: Random) : Biome(
    Settings().configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
        // TODO: none
        .precipitation(if (random.nextBoolean(0.9)) Precipitation.RAIN else Precipitation.SNOW)
        .category(Category.PLAINS)
        .depth(random.normalDistributedFloat())
        .scale(random.normalDistributedFloat())
        .temperature(random.normalDistributedFloat())
        .downfall(random.normalDistributedFloat(expectation = 0.3))
        .waterColor(random.nextInt(0..9999999))
        .waterFogColor(random.nextInt(0..9999999))
        .parent(null)
) {
    init {
        random.runRandomly(0.9) { DefaultBiomeFeatures.addLandCarvers(this) }
        random.runRandomly(0.8) { DefaultBiomeFeatures.addDefaultLakes(this) }
        random.runRandomly(0.9) { DefaultBiomeFeatures.addDungeons(this) }
        random.runRandomly(0.9) { DefaultBiomeFeatures.addDefaultFlowers(this) }
        random.runRandomly(0.95) { DefaultBiomeFeatures.addMineables(this) }
        random.runRandomly(0.95) { DefaultBiomeFeatures.addDefaultOres(this) }
        random.runRandomly(0.4) { DefaultBiomeFeatures.addDefaultVegetation(this) }
        random.runRandomly(0.4) { DefaultBiomeFeatures.addSprings(this) }
    }
}