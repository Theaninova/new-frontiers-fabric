package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.world.dimension.DimensionBertiBotts
import de.wulkanat.new_frontiers.world.dimension.DynamicDimension
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

object CommandCreateDimension {
    private const val ARG_DIMENSION_ID = "dimension_id"
    private const val ARG_DIMENSION_SEED = "dimension_seed"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(LiteralArgumentBuilder.literal<ServerCommandSource>("mkdim")
            .then(CommandManager.argument(ARG_DIMENSION_ID, StringArgumentType.string())
                .then(CommandManager.argument(ARG_DIMENSION_SEED, IntegerArgumentType.integer())
                    .executes { context ->
                        val id = StringArgumentType.getString(context, ARG_DIMENSION_ID)
                        val seed = IntegerArgumentType.getInteger(context, ARG_DIMENSION_SEED)

                        if (DynamicDimension.createDimension(id, context.source.minecraftServer)
                            { world, type -> DimensionBertiBotts(world, type, seed.toLong()) }
                        ) {
                            context.source.sendFeedback(LiteralText("Dimension '$id' was created!"), true)
                        } else {
                            context.source.sendError(LiteralText("Dimension already exists!"))
                        }
                        1
                    }
                )
            )
        )
    }
}