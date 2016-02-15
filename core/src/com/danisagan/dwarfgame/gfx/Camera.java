package com.danisagan.dwarfgame.gfx;

import com.danisagan.dwarfgame.geometry.Rect2i;
import com.danisagan.dwarfgame.geometry.Vec2f;
import com.danisagan.dwarfgame.geometry.Vec2i;

/**
 * Created by daniel on 10/18/15.
 */
public class Camera {
    private Vec2f targetPosition;
    private Vec2f currPosition;
    private Vec2i viewSize;
    private int scale;

    public Camera(Vec2f position, Vec2i viewSize) {
        this.currPosition = position;
        this.targetPosition = position;
        this.viewSize = viewSize;
        this.scale = 4;
    }

    public Vec2f getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(Vec2f currPosition) {
        this.currPosition = currPosition;
    }

    public Vec2f getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Vec2f targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Vec2i getViewSize() {
        return viewSize;
    }

    public void setViewSize(Vec2i viewSize) {
        this.viewSize = viewSize;
    }

    public void update(float dt) {
        this.currPosition = Vec2f.add(
            this.currPosition,
            Vec2f.scale(Vec2f.subs(this.targetPosition, this.currPosition), 100.f*dt)
        );
    }


    public void move(Vec2f deltaPosition) {
        this.targetPosition = Vec2f.add(this.targetPosition, deltaPosition);
    }

    public Vec2i getViewCenterPixel() {
        return Vec2i.shrink(this.getViewSize(), 2);
    }

    public Rect2i getRect() {
        return new Rect2i(new Vec2i(0, 0), this.viewSize);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
