package com.danisagan.dwarfgame.gfx;

import com.danisagan.dwarfgame.geometry.Matrix2x2f;
import com.danisagan.dwarfgame.geometry.Matrix3x3f;
import com.danisagan.dwarfgame.geometry.Vec2f;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.geometry.Vec3f;

/**
 * Created by daniel on 2/13/16.
 */
public class IsoCamera extends Camera {

    private Vec2f xAxis;
    private Vec2f yAxis;
    private Matrix3x3f viewToTileMatrix;
    private Matrix3x3f tileToViewMatrix;

    public IsoCamera(Vec2f position, Vec2i viewSize, Vec2f xAxis, Vec2f yAxis) {
        super(position, viewSize);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        updateMatrices();
    }

    private void updateMatrices() {
        float[] data = new float[] {xAxis.x, yAxis.x, -getCurrPosition().x*xAxis.x-getCurrPosition().y*yAxis.x,
                                    xAxis.y, yAxis.y, -getCurrPosition().x*xAxis.y-getCurrPosition().y*yAxis.y,
                                    0,       0,       1};
        tileToViewMatrix = new Matrix3x3f(data);
        viewToTileMatrix = tileToViewMatrix.getInverse();
    }

    public Vec2f getRealPos(Vec2f screenPos) {
        Vec3f viewPos = new Vec3f(screenPos.x - getViewSize().x/2, getViewSize().y/2 - screenPos.y, 1);
        Vec3f transformed = viewPos.transform(viewToTileMatrix);
        return new Vec2f(transformed.x, transformed.y);
    }

    public Vec2f getScreenPos(Vec2f realPos) {
        Vec3f realPos3f = new Vec3f(realPos.x, realPos.y, 1.f);
        Vec3f viewPos = realPos3f.transform(tileToViewMatrix);
        return new Vec2f(viewPos.x + getViewSize().x/2, getViewSize().y/2 - viewPos.y);
    }

    public Vec2f getRealDeltaPos(Vec2f screenDeltaPos) {
        return screenDeltaPos.transform(viewToTileMatrix.getSubMatrix(2, 2));
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        updateMatrices();
    }
}
