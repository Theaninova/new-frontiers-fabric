package de.wulkanat.new_frontiers.abstraction.block

import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.layer.BiomeLayers
import net.minecraft.world.biome.source.BiomeLayerSampler
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig

class NFBiomeSource(
    val name: String,
    biomes: List<Biome>,
    config: VanillaLayeredBiomeSourceConfig // TODO: custom source config and sampler
) : BiomeSource(biomes.toSet()) {
    private val biomeSampler: BiomeLayerSampler = BiomeLayers.build(config.seed, config.generatorType, config.generatorSettings)

    override fun getBiomeForNoiseGen(biomeX: Int, biomeY: Int, biomeZ: Int): Biome {
        return this.biomeSampler.sample(biomeX, biomeZ);
    }
}