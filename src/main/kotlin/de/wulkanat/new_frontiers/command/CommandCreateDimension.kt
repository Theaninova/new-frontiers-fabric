package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.extensions.entity.changePositionDirect
import de.wulkanat.new_frontiers.world.dimension.DimensionBertiBotts
import de.wulkanat.new_frontiers.world.dimension.DynamicDimension
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.world.dimension.DimensionType

object CommandCreateDimension {
    private const val ARG_DIMENSION_ID = "dimension_id"
    private const val ARG_DIMENSION_SEED = "dimension_seed"
    private const val ARG_TELEPORT = "teleport"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("mkdim").then(
                CommandManager.argument(ARG_DIMENSION_ID, StringArgumentType.string()).then(
                    CommandManager.argument(ARG_DIMENSION_SEED, IntegerArgumentType.integer()).then(
                        CommandManager.argument(ARG_TELEPORT, StringArgumentType.string()).executes { context ->
                            val id = StringArgumentType.getString(context, ARG_DIMENSION_ID)
                            val seed = IntegerArgumentType.getInteger(context, ARG_DIMENSION_SEED)
                            val teleport = StringArgumentType.getString(context, ARG_TELEPORT)!!.toBoolean()

                            if (DynamicDimension.createDimension(
                                    "${id}__${seed.toLong()}",
                                    context.source.minecraftServer
                                )
                                { world, type -> DimensionBertiBotts(world, type, seed.toLong()) }
                            ) {
                                context.source.sendFeedback(LiteralText("Dimension '$id' was created!"), true)
                                if (teleport) context.source.entity?.changePositionDirect(
                                    DimensionType.byId(
                                        Identifier(MOD_ID, "${id}__${seed.toLong()}")
                                    )!!
                                )
                            } else {
                                context.source.sendError(LiteralText("Dimension already exists!"))
                            }
                            1
                        })
                )
            )
        )
    }
}