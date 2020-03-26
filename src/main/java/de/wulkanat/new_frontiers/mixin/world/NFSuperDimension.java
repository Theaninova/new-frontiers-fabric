package de.wulkanat.new_frontiers.mixin.world;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

abstract public class NFSuperDimension extends Dimension {
    public NFSuperDimension(World world, DimensionType type, float f) {
        super(world, type, f);
    }

    // override
    @Environment(EnvType.CLIENT)
    public float getCloudHeight(long time) {
        return 128.0F;
    }
}
