package jp.ukon.ukon_core.debug.commands;

import jp.ukon.ukon_core.client.gui.ScreenOpener;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import jp.ukon.ukon_core.debug.TestWorldViewerScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;

public class UCDebugCommands {

    public static void registerDebugCommands(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands
            .literal("viewer")
                .then(Commands.argument("dim", DimensionArgument.dimension())
                    .then(Commands.argument("pos", Vec3Argument.vec3(false))
                        .executes(context -> {
                            ScreenOpener.open(new TestWorldViewerScreen(DimensionArgument.getDimension(context, "dim"), Vec3Argument.getVec3(context, "pos")));
                            return 0;
                        })
                    )
                )
        );
    }
}
