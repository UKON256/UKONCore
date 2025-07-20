package jp.ukon.postengine.util;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Cinemachine {
    private List<Point> AllPoints;

    public Cinemachine() {
        AllPoints = new ArrayList<>();
    }

    public Cinemachine addPoint(Point point) {
        AllPoints.add(point);
        return this;
    }

    public class Point {
        Vec3 pos;
    }
}
