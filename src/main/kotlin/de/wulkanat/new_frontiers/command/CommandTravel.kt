package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.extensions.entity.changePositionDirect
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.world.dimension.DimensionType

object CommandTravel {
    private const val ARG_DIMENSION_ID = "planet"

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("travel")
                .then(CommandManager.argument(ARG_DIMENSION_ID, StringArgumentType.string()).executes {
                    val entity = it.source.entity ?: return@executes 1
                    val id = StringArgumentType.getString(it, ARG_DIMENSION_ID).toLowerCase()

                    val dimensionType = DimensionType.byId(Identifier("${MOD_ID}_${id}__0")) ?: run {
                        it.source.sendError(LiteralText("Planet not found!"))
                        return@executes 1
                    }

                    entity.changePositionDirect(dimensionType)
                    it.source.sendFeedback(LiteralText("Traveling to $id"), true)
                    1
                })
        )
    }
}