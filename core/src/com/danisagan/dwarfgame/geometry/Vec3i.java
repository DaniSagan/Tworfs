package com.danisagan.dwarfgame.geometry;

/**
 * Created by daniel on 10/18/15.
 */
public class Vec3i {
    public int x;
    public int y;
    public int z;

    public Vec3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3i add(Vec3i... vs) {
        Vec3i res = new Vec3i(0, 0, 0);
        for(Vec3i v: vs) {
            res.x += v.x;
            res.y += v.y;
            res.z += v.z;
        }
        return res;
    }

    public static Vec3i scale(Vec3i v, int scale) {
        return new Vec3i(v.x*scale, v.y*scale, v.z*scale);
    }

    public static Vec3i subs(Vec3i v1, Vec3i v2) {
        return new Vec3i(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public String toString() {
        return String.format("%d, %d, %d", this.x, this.y, this.z);
    }

}
