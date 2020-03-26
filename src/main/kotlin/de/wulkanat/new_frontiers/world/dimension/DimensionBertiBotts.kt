package de.wulkanat.new_frontiers.world.dimension

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.world.biome.procedural.BiomeProceduralPlains
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.Identifier
import net.minecraft.util.math.*
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import net.minecraft.world.biome.source.BiomeSourceType
import net.minecraft.world.biome.source.FixedBiomeSourceConfig
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.chunk.*
import kotlin.random.Random

class DimensionBertiBotts(world: World, type: DimensionType, val seed: Long) : DynamicDimension(world, type, 0.5F) {
    private val dayLength = 50
    private val fogColor = Vec3d(0.54, 0.44, 0.16)

    @Environment(EnvType.CLIENT)
    override fun isFogThick(x: Int, z: Int): Boolean {
        return false
    }

    override fun getSpawningBlockInChunk(chunkPos: ChunkPos?, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun getSkyAngle(timeOfDay: Long, tickDelta: Float): Float {
        val daysPassed = (timeOfDay.toDouble() + tickDelta) / dayLength
        return MathHelper.fractionalPart(daysPassed - 0.25).toFloat()
    }

    override fun createChunkGenerator(): ChunkGenerator<*> {
        val generatorConfig = OverworldChunkGeneratorConfig()
        val biome = Registry.register(Registry.BIOME, Identifier(MOD_ID, "plains__${this.dimType.suffix}"),
            BiomeProceduralPlains(Random(seed)))

        val biomeConfig: FixedBiomeSourceConfig =
            BiomeSourceType.FIXED.getConfig(world.levelProperties).setBiome(biome)
        return ChunkGeneratorType.SURFACE.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), generatorConfig)
    }

    override fun getTopSpawningBlockPosition(x: Int, z: Int, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun hasVisibleSky(): Boolean {
        return true
    }

    override fun canPlayersSleep(): Boolean {
        return false
    }

    override fun getFogColor(skyAngle: Float, tickDelta: Float): Vec3d {
        return fogColor
    }
}