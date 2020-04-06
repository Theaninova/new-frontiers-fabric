package de.wulkanat.new_frontiers.abstraction.block

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.world.dimension.DimensionType

// BIG TODO BIG TODO
abstract class NFCommand(
    command: CommandRoot
) {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("lsdim")
                .executes {
                    val dimensions = DimensionType.getAll()
                        .joinToString { type -> type.rawId.toString() }

                    it.source.sendFeedback(LiteralText("Dimensions: [$dimensions]"), true)
                    1
                })
    }
}

interface Arg { val then: (Any?) -> Int }
//class StringArgument(val name: String, override val then: (String) -> Int) : Arg
class CommandRoot(val name: String)

object TestCommand : NFCommand(
    CommandRoot("test")
)