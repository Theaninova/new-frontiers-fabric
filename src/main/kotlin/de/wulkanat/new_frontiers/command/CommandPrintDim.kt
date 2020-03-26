package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

object CommandPrintDim {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(LiteralArgumentBuilder.literal<ServerCommandSource>("lsdim")
            .executes {
                val dimensions = DimensionType.getAll()
                    .joinToString { type -> type.rawId.toString() }

                it.source.sendFeedback(LiteralText("Dimensions: [$dimensions]"), true)
                1
            })
    }
}