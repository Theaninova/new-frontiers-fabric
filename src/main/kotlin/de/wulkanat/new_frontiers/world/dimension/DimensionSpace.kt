package de.wulkanat.new_frontiers.world.dimension

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSourceType
import net.minecraft.world.biome.source.FixedBiomeSourceConfig
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorType
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig

class DimensionSpace(world: World, type: DimensionType) : DynamicDimension(world, type, 0.5F) {
    override fun isFogThick(x: Int, z: Int): Boolean {
        return false
    }

    override fun getBackgroundColor(skyAngle: Float, tickDelta: Float): FloatArray? {
        return floatArrayOf(0.0F, 0.0F, 0.0F)
    }

    override fun hasGround(): Boolean {
        return false
    }

    override fun hasSkyLight(): Boolean {
        return true
    }

    override fun doesWaterVaporize(): Boolean {
        return true
    }

    override fun getHorizonShadingRatio(): Double {
        return 0.0
    }

    override fun getBrightness(lightLevel: Int): Float {
        return this.lightLevelToBrightness[lightLevel] * 2
    }

    override fun getSpawningBlockInChunk(chunkPos: ChunkPos?, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun getSkyAngle(timeOfDay: Long, tickDelta: Float): Float {
        return 1.0F
    }

    override fun createChunkGenerator(): ChunkGenerator<*> {
        // TODO
        val generatorConfig: FlatChunkGeneratorConfig = FlatChunkGeneratorConfig.getDefaultConfig()
        val biomeConfig: FixedBiomeSourceConfig =
            BiomeSourceType.FIXED.getConfig(world.levelProperties).setBiome(Biomes.JUNGLE)
        return ChunkGeneratorType.FLAT.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), generatorConfig)
    }

    override fun getTopSpawningBlockPosition(x: Int, z: Int, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun hasVisibleSky(): Boolean {
        return true
    }

    override fun canPlayersSleep(): Boolean {
        return true
    }

    override fun getFogColor(skyAngle: Float, tickDelta: Float): Vec3d {
        return Vec3d.ZERO
    }
}