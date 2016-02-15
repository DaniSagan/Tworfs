package com.danisagan.dwarfgame.geometry;

/**
 * Created by daniel on 10/18/15.
 */
public class Vec2i {
    public int x;
    public int y;

    public Vec2i() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(Vec2f v) {
        this.x = (int)v.x;
        this.y = (int)v.y;
    }

    public static Vec2i add(Vec2i... vs) {
        Vec2i res = new Vec2i(0, 0);
        for(Vec2i v: vs) {
            res.x += v.x;
            res.y += v.y;
        }
        return res;
    }

    public static Vec2i scale(Vec2i v, int scale) {
        return new Vec2i(v.x*scale, v.y*scale);
    }

    public Vec2i getScaled(int scale) {
        return Vec2i.scale(this, scale);
    }

    public static Vec2i shrink(Vec2i v, int scale) {
        return new Vec2i(v.x/scale, v.y/scale);
    }

    public static Vec2i subs(Vec2i v1, Vec2i v2) {
        return new Vec2i(v1.x - v2.x, v1.y - v2.y);
    }

    public Vec2f toVec2f() {
        return new Vec2f((float)this.x, (float)this.y);
    }

    public static Vec2i flipX(Vec2i v) {
        return new Vec2i(-v.x, v.y);
    }

    public static Vec2i flipY(Vec2i v) {
        return new Vec2i(v.x, -v.y);
    }

    public String toString() {
        return String.format("%d, %d", this.x, this.y);
    }
}
