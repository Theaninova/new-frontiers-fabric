package de.wulkanat.new_frontiers.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

object CommandRecursiveCreate {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            LiteralArgumentBuilder.literal<ServerCommandSource>("registerCmd")
                .then(CommandManager.argument("command_name", StringArgumentType.string()).executes {
                    val arg = StringArgumentType.getString(it, "command_name")
                    dispatcher.register(LiteralArgumentBuilder.literal<ServerCommandSource>(arg).executes { innerIt ->
                        innerIt.source.sendFeedback(LiteralText("Yeaaaaaaa, works!"), false)
                        1
                    })
                    it.source.sendFeedback(LiteralText("Added new command <$arg>"), true)
                    1
                })
        )
    }
}