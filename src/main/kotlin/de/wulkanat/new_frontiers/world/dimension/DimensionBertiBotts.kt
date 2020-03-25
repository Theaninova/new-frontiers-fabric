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

class DimensionBertiBotts(world: World, type: DimensionType) : Dimension(world, type, 0.5F) {
    private val dayLength = 24000
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
        val generatorConfig: FlatChunkGeneratorConfig = FlatChunkGeneratorConfig.getDefaultConfig()
        val biomeConfig: FixedBiomeSourceConfig =
            BiomeSourceType.FIXED.getConfig(world.levelProperties).setBiome(Biomes.JUNGLE)
        return ChunkGeneratorType.FLAT.create(world, BiomeSourceType.FIXED.applyConfig(biomeConfig), generatorConfig)
    }

    override fun getTopSpawningBlockPosition(x: Int, z: Int, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun getType(): DimensionType {
        return BERTI_BOTTS_DIMENSION
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
        lateinit var BERTI_BOTTS_DIMENSION: FabricDimensionType

        fun register() {
            BERTI_BOTTS_DIMENSION = FabricDimensionType.builder()
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
                .factory { world, type -> DimensionBertiBotts(world, type) }
                .skyLight(false)
                .buildAndRegister(Identifier(MOD_ID, "berti_botts"))
        }
    }
}