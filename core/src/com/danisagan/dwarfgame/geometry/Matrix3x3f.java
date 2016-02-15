package com.danisagan.dwarfgame.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 10/24/15.
 */
public class Matrix3x3f {
    List<Float> data = new ArrayList<Float>(9);

    public Matrix3x3f() {
        for(int i = 0; i < 9; i++) {
            data.add(0.f);
        }
    }

    public Matrix3x3f(float[] values) {
        if(values.length != 9) {
            throw new IllegalArgumentException("Array must contain 4 elements");
        } else {
            for(float v: values)
            {
                this.data.add(v);
            }
        }
    }

    public float get(Vec2i index) {
        if(index.x < 0 || index.x >= 3 || index.y < 0 || index.y >= 3) {
            throw new IllegalArgumentException("index component values should be betweeen 0 and 2");
        }
        return this.data.get(index.y*3 + index.x);
    }

    public float get(int x, int y) {
        return get(new Vec2i(x, y));
    }

    public void set(Vec2i index, float value) {
        if(index.x < 0 || index.x >= 3 || index.y < 0 || index.y >= 3) {
            throw new IllegalArgumentException("index component values should be betweeen 0 and 2");
        }
        this.data.set(index.y * 3 + index.x, value);
    }

    public void set(int x, int y, float value) {
        set(new Vec2i(x, y), value);
    }

    public float getDeterminant() {
        return
                data.get(0)*(data.get(4)*data.get(8)-data.get(5)*data.get(7)) +
                data.get(1)*(data.get(5)*data.get(6)-data.get(3)*data.get(8)) +
                data.get(2)*(data.get(3)*data.get(7)-data.get(4)*data.get(6));
    }

    public Matrix3x3f getInverse() {
        Matrix3x3f res = new Matrix3x3f();
        float idet = 1.0f / this.getDeterminant();
        res.set(0, 0, idet*(data.get(4)*data.get(8)-data.get(5)*data.get(7)));
        res.set(1, 0, idet*(data.get(2)*data.get(7)-data.get(1)*data.get(8)));
        res.set(2, 0, idet*(data.get(1)*data.get(5)-data.get(2)*data.get(4)));
        res.set(0, 1, idet*(data.get(5)*data.get(6)-data.get(3)*data.get(8)));
        res.set(1, 1, idet*(data.get(0)*data.get(8)-data.get(2)*data.get(6)));
        res.set(2, 1, idet*(data.get(2)*data.get(3)-data.get(0)*data.get(5)));
        res.set(0, 2, idet*(data.get(3)*data.get(7)-data.get(4)*data.get(6)));
        res.set(1, 2, idet*(data.get(1)*data.get(6)-data.get(0)*data.get(7)));
        res.set(2, 2, idet*(data.get(0)*data.get(4)-data.get(1)*data.get(3)));
        return res;
    }

    public Matrix2x2f getSubMatrix(Vec2i index) {
        float[] values = new float[4];
        int count = 0;
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if(x != index.x && y != index.y) {
                    values[count] = get(x, y);
                    count++;
                }
            }
        }
        return new Matrix2x2f(values);
    }

    public Matrix2x2f getSubMatrix(int indexX, int indexY) {
        return getSubMatrix(new Vec2i(indexX, indexY));
    }
}
