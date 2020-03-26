package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.extensions.entity.changePositionDirect
import net.minecraft.command.arguments.IdentifierArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

object CommandPrintDim {
    private const val ARG_DIMENSION_ID = "dimension_id"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("printdim")
                .executes {

                    val dimensions = DimensionType.getAll()
                        .joinToString { type -> type.rawId.toString() }

                    it.source.sendFeedback(LiteralText("Dimensions: [$dimensions]"), true)
                    1
                })
    }
}