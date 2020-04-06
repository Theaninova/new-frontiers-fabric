package de.wulkanat.new_frontiers.abstraction.block

import de.wulkanat.new_frontiers.extensions.kotlin.random.runRandomly
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import kotlin.random.Random

abstract class NFBiome(
    val name: String,
    surfaceBuilder: SurfaceBuilder<TernarySurfaceConfig> = SurfaceBuilder.DEFAULT,
    surfaceConfig: TernarySurfaceConfig = SurfaceBuilder.GRASS_CONFIG,
    downfall: Biome.Precipitation = Precipitation.NONE,
    downfallFrequency: Float = 0.3F,
    category: Biome.Category = Category.PLAINS,
    depth: Float = 0.5F,
    scale: Float = 0.5F,
    temperature: Float = 0.5F,
    waterColor: Int,
    waterFogColor: Int,
    parent: String? = null,

    random: Random,
    randomFeatures: Map<Double, (Biome) -> Unit>
) : Biome(
    Settings().configureSurfaceBuilder(surfaceBuilder, surfaceConfig)
        // TODO: none
        .precipitation(downfall)
        .category(category)
        .depth(depth)
        .scale(scale)
        .temperature(temperature)
        .downfall(downfallFrequency)
        .waterColor(waterColor)
        .waterFogColor(waterFogColor)
        .parent(parent)
) {
    init {
        for ((probability, executor) in randomFeatures) {
            random.runRandomly(probability) { executor(this) }
        }
    }
}