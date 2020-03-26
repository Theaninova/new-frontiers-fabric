package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.world.dimension.DimensionBertiBotts
import de.wulkanat.new_frontiers.world.dimension.DynamicDimension
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

object CommandCreateDimension {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("mkdim")
                .then(
                    CommandManager.argument(ARG_DIMENSION_ID, StringArgumentType.string()).executes { context ->
                        val id = StringArgumentType.getString(context, ARG_DIMENSION_ID)
                        if (DynamicDimension.createDimension(
                                id,
                                context.source.minecraftServer
                            ) { world, type -> DimensionBertiBotts(world, type) }
                        ) {
                            context.source.sendFeedback(LiteralText("Dimension '$id' was created!"), true)
                        } else {
                            context.source.sendError(LiteralText("Dimension already exists!"))
                        }
                        1
                    })
        )
    }
}