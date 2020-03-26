package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.extensions.entity.changePositionDirect
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

object CommandTPRaw {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("tpdimraw")
                .then(CommandManager.argument(ARG_DIMENSION_ID, IntegerArgumentType.integer()).executes {
                    val entity = it.source.entity ?: return@executes 1
                    val id = IntegerArgumentType.getInteger(it, ARG_DIMENSION_ID)

                    val dimensionType = DimensionType.byRawId(id) ?: run {
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