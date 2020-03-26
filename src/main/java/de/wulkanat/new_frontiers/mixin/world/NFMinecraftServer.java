package de.wulkanat.new_frontiers.mixin.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(MinecraftServer.class)
public class NFMinecraftServer {
    @Shadow @Final private Map<DimensionType, ServerWorld> worlds;

    public Map<DimensionType, ServerWorld> getWorldsRaw() {
        return this.worlds;
    }
}
