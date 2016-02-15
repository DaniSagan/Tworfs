package com.danisagan.dwarfgame.geometry;

/**
 * Created by daniel on 10/18/15.
 */
public class Vec3f {
    public float x;
    public float y;
    public float z;

    public Vec3f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3f add(Vec3f... vs) {
        Vec3f res = new Vec3f(0, 0, 0);
        for(Vec3f v: vs) {
            res.x += v.x;
            res.y += v.y;
            res.z += v.z;
        }
        return res;
    }

    public static Vec3f scale(Vec3f v, float scale) {
        return new Vec3f(v.x*scale, v.y*scale, v.z*scale);
    }

    public static Vec3f subs(Vec3f v1, Vec3f v2) {
        return new Vec3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public Vec3f transform(Matrix3x3f m) {
        return new Vec3f(this.x*m.get(0,0)+this.y*m.get(1,0)+this.z*m.get(2,0),
                         this.x*m.get(0,1)+this.y*m.get(1,1)+this.z*m.get(2,1),
                         this.x*m.get(0,2)+this.y*m.get(1,2)+this.z*m.get(2,2));
    }

    public String toString() {
        return String.format("%f, %f, %f", this.x, this.y, this.z);
    }

    public float getLength() {
        return (float)(Math.sqrt(x*x + y*y + z*z));
    }
}
