package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.extensions.server.getWorldsRaw
import de.wulkanat.new_frontiers.extensions.server.world.getWorldGenerationProgressListener
import de.wulkanat.new_frontiers.extensions.server.world.getWorldSaveHandler
import de.wulkanat.new_frontiers.mixin.world.NFMinecraftServer
import de.wulkanat.new_frontiers.mixin.world.NFServerWorld
import de.wulkanat.new_frontiers.world.dimension.DimensionBertiBotts
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType
import net.minecraft.block.pattern.BlockPattern
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.world.SecondaryServerWorld
import net.minecraft.server.world.ServerChunkManager
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Heightmap
import net.minecraft.world.dimension.DimensionType

object CommandCreateDimension {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("mkdim")
                .then(
                    CommandManager.argument(ARG_DIMENSION_ID, StringArgumentType.string()).executes { context ->
                        val id = StringArgumentType.getString(context, ARG_DIMENSION_ID)

                        DimensionType.byId(Identifier(MOD_ID, id)) ?: run {
                            context.source.sendFeedback(LiteralText("Creating Dimension '$id'"), true)
                            val dimensionType = FabricDimensionType.builder()
                                .defaultPlacer { oldEntity: Entity, destinationWorld: ServerWorld, _: Direction?, _: Double, _: Double ->
                                    BlockPattern.TeleportTarget(
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
                                .buildAndRegister(Identifier(MOD_ID, id))

                            val server = context.source.entity?.server
                            val worlds = server!!.getWorldsRaw()
                            val nether = worlds[DimensionType.THE_NETHER]
                            worlds.put(dimensionType, SecondaryServerWorld(
                                worlds[DimensionType.OVERWORLD],
                                server,
                                server.workerExecutor,
                                worlds[DimensionType.THE_NETHER].getWorldSaveHandler(),
                                dimensionType,
                                server.profiler,
                                (nether!!.chunkManager as ServerChunkManager).threadedAnvilChunkStorage.getWorldGenerationProgressListener()
                            ))

                            return@executes 1
                        }

                        context.source.sendError(LiteralText("Dimension already exists!"))
                        return@executes 1
                        1
                    })
        )
    }
}