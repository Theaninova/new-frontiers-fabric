package de.wulkanat.new_frontiers.world.biome

import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class SpaceBiome() : Biome(
    Settings().configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
        .precipitation(Precipitation.RAIN)
        .category(Biome.Category.NONE)
        .depth(0.24F)
        .scale(0.2F)
        .temperature(0.0F)
        .downfall(0.0F)
        .waterColor(4159204)
        .waterFogColor(329011)
        .parent(null)
) {
}