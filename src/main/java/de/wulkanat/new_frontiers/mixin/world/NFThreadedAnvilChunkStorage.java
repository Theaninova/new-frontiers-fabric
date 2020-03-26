package de.wulkanat.new_frontiers.mixin.world;

import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ThreadedAnvilChunkStorage.class)
public class NFThreadedAnvilChunkStorage {
    @Shadow @Final private WorldGenerationProgressListener worldGenerationProgressListener;

    public WorldGenerationProgressListener getWorldGenerationProgressListener() {
        return worldGenerationProgressListener;
    }
}
