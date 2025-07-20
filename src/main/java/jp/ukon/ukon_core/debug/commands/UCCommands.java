package jp.ukon.ukon_core.debug.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class UCCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("uc").requires(cs -> cs.hasPermission(1));

        LiteralArgumentBuilder<CommandSourceStack> debugBuilder = Commands.literal("debug");
        UCDebugCommands.registerDebugCommands(debugBuilder);
        builder.then(debugBuilder);

        dispatcher.register(builder);
    }
}
