package de.wulkanat.new_frontiers.mixin.world;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldSaveHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerWorld.class)
public class NFServerWorld {
    @Shadow @Final private WorldSaveHandler worldSaveHandler;

    public WorldSaveHandler getWorldSaveHandler() {
        return worldSaveHandler;
    }
}
