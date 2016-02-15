package com.danisagan.dwarfgame.geometry;

/**
 * Created by daniel on 10/18/15.
 */
public class Vec2f {
    public float x;
    public float y;

    public Vec2f() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2i v) {
        this.x = (float)v.x;
        this.y = (float)v.y;
    }

    public static Vec2f add(Vec2f... vs) {
        Vec2f res = new Vec2f(0.f, 0.f);
        for(Vec2f v: vs) {
            res.x += v.x;
            res.y += v.y;
        }
        return res;
    }

    public static Vec2f scale(Vec2f v, float scale) {
        return new Vec2f(v.x*scale, v.y*scale);
    }

    public Vec2f getScaled(float scale) {
        return Vec2f.scale(this, scale);
    }

    public static Vec2f subs(Vec2f v1, Vec2f v2) {
        return new Vec2f(v1.x - v2.x, v1.y - v2.y);
    }

    public Vec2i toVec2i() {
        return new Vec2i((int)this.x, (int)this.y);
    }

    public static Vec2f flipX(Vec2f v) {
        return new Vec2f(-v.x, v.y);
    }

    public static Vec2f flipY(Vec2f v) {
        return new Vec2f(v.x, -v.y);
    }

    public Vec2f transform(Matrix2x2f m) {
        return new Vec2f(this.x*m.get(0,0)+this.y*m.get(1,0), this.x*m.get(0,1)+this.y*m.get(1,1));
    }

    public String toString() {
        return String.format("%f, %f", this.x, this.y);
    }

    public float getLength() {
        return (float)(Math.sqrt(x*x + y*y));
    }
}
