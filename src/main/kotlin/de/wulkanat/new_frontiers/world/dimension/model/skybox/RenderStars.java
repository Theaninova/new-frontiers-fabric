package de.wulkanat.new_frontiers.world.dimension.model.skybox;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;

import java.util.Random;

public class RenderStars {
    private void renderStars(BufferBuilder buffer) {
        Random random = new Random(10842L);
        buffer.begin(7, VertexFormats.POSITION);

        for(int i = 0; i < 1500; ++i) {
            double x1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double y1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double z1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double g = (double)(0.15F + random.nextFloat() * 0.1F);
            double distance1 = x1 * x1 + y1 * y1 + z1 * z1;
            if (distance1 < 1.0D && distance1 > 0.01D) {
                distance1 = 1.0D / Math.sqrt(distance1);
                x1 *= distance1;
                y1 *= distance1;
                z1 *= distance1;
                double x1_scaled = x1 * 100.0D;
                double y1_scaled = y1 * 100.0D;
                double z1_scaled = z1 * 100.0D;
                double x1_z1_atan2 = Math.atan2(x1, z1);
                double n = Math.sin(x1_z1_atan2);
                double o = Math.cos(x1_z1_atan2);
                double p = Math.atan2(Math.sqrt(x1 * x1 + z1 * z1), y1);
                double q = Math.sin(p);
                double r = Math.cos(p);
                double rand_two_pi = random.nextDouble() * 3.141592653589793D * 2.0D;
                double t = Math.sin(rand_two_pi);
                double u = Math.cos(rand_two_pi);

                for(int j = 0; j < 4; ++j) {
                    double w = 0.0D;
                    double x = (double)((j & 2) - 1) * g;
                    double y = (double)((j + 1 & 2) - 1) * g;
                    double z = 0.0D;
                    double aa = x * u - y * t;
                    double ab = y * u + x * t;
                    double ad = aa * q + 0.0D * r;
                    double ae = 0.0D * q - aa * r;
                    double af = ae * n - ab * o;
                    double ah = ab * n + ae * o;
                    buffer.vertex(x1_scaled + af, y1_scaled + ad, z1_scaled + ah).next();
                }
            }
        }

    }
}
