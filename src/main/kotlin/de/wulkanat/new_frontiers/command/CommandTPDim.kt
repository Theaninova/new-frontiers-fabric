package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType.integer
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

object CommandTPDim {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(LiteralArgumentBuilder.literal<ServerCommandSource>("tpdim")
            .then(CommandManager.argument(ARG_DIMENSION_ID, integer()).executes {
                val entity = it.source.entity
                val id = IntegerArgumentType.getInteger(it, ARG_DIMENSION_ID)

                if (entity != null) {
                    with (entity.pos) {
                        entity.changeDimension(DimensionType.byRawId(id))
                        entity.setPosition(x, y, z)
                    }

                    it.source.sendFeedback(
                        LiteralText("TP to dimension $id"), true)
                }
                1
            }))
    }
}