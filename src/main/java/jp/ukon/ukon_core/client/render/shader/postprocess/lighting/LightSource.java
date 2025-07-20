package jp.ukon.ukon_core.client.render.shader.postprocess.lighting;

import jp.ukon.postengine.util.Color;
import net.minecraft.client.Minecraft;

import java.util.Comparator;

public class LightSource {
    public static final Comparator<LightSource> SORTER_BY_DISTANCE = new Comparator<LightSource>() {
        @Override
        public int compare(LightSource o1, LightSource o2) {
            double dx1 = o1.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
            double dy1 = o1.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
            double dz1 = o1.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
            double dx2 = o2.x - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().x();
            double dy2 = o2.y - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y();
            double dz2 = o2.z - Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().z();
            double d1 = Math.sqrt(dx1 * dx1 + dy1 * dy1 + dz1 * dz1);
            double d2 = Math.sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2);

            return Double.compare(d1, d2);
        }
    };

    public final double x, y, z, radius;
    public final float r, g, b;

    public LightSource(double x, double y, double z, double radius, int intColor) {
        Color color = new Color(intColor);
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.r = color.getRedAsFloat();
        this.g = color.getGreenAsFloat();
        this.b = color.getBlueAsFloat();
    }

    public LightSource(double x, double y, double z, double radius, Color color) {
        this(x, y, z, radius, color.getRedAsFloat(), color.getGreenAsFloat(), color.getBlueAsFloat());
    }

    public LightSource(double x, double y, double z, double radius, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
