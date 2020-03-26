package de.wulkanat.new_frontiers.world.dimension

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.math.*
import net.minecraft.util.math.noise.PerlinNoiseSampler
import net.minecraft.world.World
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSourceType
import net.minecraft.world.biome.source.FixedBiomeSourceConfig
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorType
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig
import java.util.*
import kotlin.math.PI
import kotlin.math.sin

class DimensionBertiBotts(world: World, type: DimensionType) : DynamicDimension(world, type, 0.5F) {
    private val dayLength = 50
    private val fogColor = Vec3d(0.54, 0.44, 0.16)
    private val perlin = PerlinNoiseSampler(Random(1030495))

    class Cloud(
        private val heightRage: IntRange,
        private val cloudTickSpeed: Float,
        initialHeight: Int = heightRage.first
    ) {
        private var cloudOperationReverse = false
        var height = initialHeight.toFloat()
            private set

        fun tick() {
            if (height > heightRage.last) cloudOperationReverse = true
            else if (height < heightRage.first) cloudOperationReverse = false

            if (cloudOperationReverse)
                height += cloudTickSpeed
            else
                height -= cloudTickSpeed
        }
    }

    private val clouds = Cloud(50..100, 5.0F)


    @Environment(EnvType.CLIENT)
    override fun isFogThick(x: Int, z: Int): Boolean {
        return perlin.sample(x.toDouble(), z.toDouble(), 1.0, 1.0, 1.0) > 0.5
    }

    override fun getSpawningBlockInChunk(chunkPos: ChunkPos?, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun getSkyAngle(timeOfDay: Long, tickDelta: Float): Float {
        val daysPassed = sin((timeOfDay.toDouble() + tickDelta) / dayLength / PI / 2)
        return MathHelper.fractionalPart(daysPassed - 0.25).toFloat()
    }

    override fun createChunkGenerator(): ChunkGenerator<*> {
        val generatorConfig: FlatChunkGeneratorConfig = FlatChunkGeneratorConfig.getDefaultConfig()
        val biomeConfig: FixedBiomeSourceConfig =
            BiomeSourceType.FIXED.getConfig(world.levelProperties).setBiome(Biomes.JUNGLE)
        return ChunkGeneratorType.FLAT.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), generatorConfig)
    }

    override fun getTopSpawningBlockPosition(x: Int, z: Int, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun update() {
        super.update()
        clouds.tick()
    }

    override fun getCloudHeight(): Float {
        return clouds.height
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