package de.wulkanat.new_frontiers.world.dimension

import de.wulkanat.new_frontiers.MOD_ID
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType
import net.minecraft.block.pattern.BlockPattern.TeleportTarget
import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.*
import net.minecraft.util.math.noise.PerlinNoiseSampler
import net.minecraft.world.Heightmap
import net.minecraft.world.World
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSourceType
import net.minecraft.world.biome.source.FixedBiomeSourceConfig
import net.minecraft.world.dimension.Dimension
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorType
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig
import java.util.*
import kotlin.math.PI
import kotlin.math.sin

class DimensionBertiBotts(world: World, type: DimensionType) : Dimension(world, type, 0.5F) {
    private val dayLength = 50
    private val fogColor = Vec3d(0.54, 0.44, 0.16)
    private val perlin = PerlinNoiseSampler(Random(1030495))
    lateinit var dimType: DimensionType

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

    override fun getType(): DimensionType {
        return dimType
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

    companion object {
        fun register(name: String = "berti_botts"): FabricDimensionType {
            lateinit var type2: FabricDimensionType
            type2 = FabricDimensionType.builder()
                .defaultPlacer { oldEntity: Entity, destinationWorld: ServerWorld, _: Direction?, _: Double, _: Double ->
                    TeleportTarget(
                        Vec3d(
                            destinationWorld.getTopPosition(
                                Heightmap.Type.WORLD_SURFACE,
                                BlockPos.ORIGIN
                            )
                        ),
                        oldEntity.velocity,
                        oldEntity.yaw.toInt()
                    )
                }
                .factory { world, type ->
                    val dim = DimensionBertiBotts(world, type)
                    dim.dimType = type2
                    dim
                }
                .skyLight(false)
                .buildAndRegister(Identifier(MOD_ID, name))

            return type2
        }
    }
}