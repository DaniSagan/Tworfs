package com.danisagan.dwarfgame.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 10/25/15.
 */
public class Matrix2x2f {
    List<Float> data = new ArrayList<Float>(4);

    public Matrix2x2f() {
        for(int k = 0; k < 4; k++) {
            this.data.add(0.f);
        }
    }

    public Matrix2x2f(float[] values) {
        if(values.length != 4) {
            throw new IllegalArgumentException("Array must contain 4 elements");
        } else {
            this.data.add(values[0]);
            this.data.add(values[1]);
            this.data.add(values[2]);
            this.data.add(values[3]);
        }
    }

    public float get(Vec2i index) {
        if(index.x < 0 || index.x >= 2 || index.y < 0 || index.y >= 2) {
            throw new IllegalArgumentException("index component values should be betweeen 0 and 1");
        }
        return this.data.get(index.y*2 + index.x);
    }

    public float get(int x, int y) {
        return get(new Vec2i(x, y));
    }

    public void set(Vec2i index, float value) {
        if(index.x < 0 || index.x >= 2 || index.y < 0 || index.y >= 2) {
            throw new IllegalArgumentException("index component values should be betweeen 0 and 2");
        }
        this.data.set(index.y * 2 + index.x, value);
    }

    public void set(int x, int y, float value) {
        set(new Vec2i(x, y), value);
    }

    public float getDeterminant() {
        return data.get(0)*data.get(3)-data.get(1)*data.get(2);
    }

    public Matrix2x2f getInverse() {
        Matrix2x2f res = new Matrix2x2f();
        float invDet = 1.0f/getDeterminant();
        res.data.set(0, invDet*data.get(3));
        res.data.set(1, -invDet*data.get(1));
        res.data.set(2, -invDet*data.get(2));
        res.data.set(3, invDet*data.get(0));
        return res;
    }
}
