package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.extensions.entity.changePositionDirect
import net.minecraft.command.arguments.IdentifierArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

object CommandTPDim {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
                LiteralArgumentBuilder.literal<ServerCommandSource>("tpdim")
                    .then(CommandManager.argument(ARG_DIMENSION_ID, IdentifierArgumentType.identifier()).executes {
                        val entity = it.source.entity ?: return@executes 1
                        val id = IdentifierArgumentType.getIdentifier(it, ARG_DIMENSION_ID)

                        val dimensionType = DimensionType.byId(id) ?: run {
                            it.source.sendError(LiteralText("Dimension not found!"))
                            return@executes 1
                        }

                        entity.changePositionDirect(dimensionType)
                        it.source.sendFeedback(LiteralText("TP to dimension $id"), true)
                        1
                    })
                )
    }
}