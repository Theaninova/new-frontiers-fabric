package de.wulkanat.new_frontiers.abstraction.block

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.world.dimension.DynamicDimension
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import net.minecraft.world.biome.source.*
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorType
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig
import kotlin.random.Random
import kotlin.reflect.KClass

abstract class NFDynamicDimension(
    world: World, type: DimensionType,
    val random: Random,
    private val skyVisible: Boolean = true,
    private val canSleep: Boolean = true,
    private val fogColor: Vec3d = Vec3d(0.5, 0.5, 0.5),
    private val dayLength: Int = 24_000,
    private val thickFog: Boolean = false,

    private var biomes: List<NFBiome>
) : DynamicDimension(world, type, 0.5F) {
    @Environment(EnvType.CLIENT)
    override fun isFogThick(x: Int, z: Int): Boolean {
        return thickFog
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
        // TODO: own chunk generator

        biomes = biomes.map {
            Registry.register(
                Registry.BIOME,
                Identifier(MOD_ID, "${it.name}__${this.dimType.suffix}"),
                it
            )
        }

        return ChunkGeneratorType.SURFACE.create(world, NFBiomeSource(
            name = "source__${this.dimType.suffix}",
            biomes = biomes,
            config = VanillaLayeredBiomeSourceConfig(this.world.levelProperties)),
            generatorConfig
        )
    }

    override fun getTopSpawningBlockPosition(x: Int, z: Int, checkMobSpawnValidity: Boolean): BlockPos? {
        return null
    }

    override fun hasVisibleSky(): Boolean {
        return skyVisible
    }

    override fun canPlayersSleep(): Boolean {
        return canSleep
    }

    override fun getFogColor(skyAngle: Float, tickDelta: Float): Vec3d {
        return fogColor
    }
}