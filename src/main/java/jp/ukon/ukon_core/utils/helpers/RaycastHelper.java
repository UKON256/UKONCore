package jp.ukon.ukon_core.utils.helpers;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class RaycastHelper {
    public static Vec3 getTraceTarget(Player playerIn, double range, Vec3 origin) {
        float f = playerIn.getXRot();
        float f1 = playerIn.getYRot();
        float f2 = Mth.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = Mth.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = range;
        Vec3 Vector3d1 = origin.add((double) f6 * d3, (double) f5 * d3, (double) f7 * d3);
        return Vector3d1;
    }
}
