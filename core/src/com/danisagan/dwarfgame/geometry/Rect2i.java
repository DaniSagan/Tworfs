package com.danisagan.dwarfgame.geometry;

/**
 * Created by daniel on 10/18/15.
 */
public class Rect2i {
    public Vec2i position;
    public Vec2i size;

    public Rect2i() {
        this.position = new Vec2i();
        this.size = new Vec2i();
    }

    public Rect2i(Vec2i position, Vec2i size) {
        this.position = position;
        this.size = size;
    }

    public static Rect2i from2Points(Vec2i p1, Vec2i p2) {
        return new Rect2i(
            new Vec2i(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y)),
            new Vec2i(Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y))
        );
    }

    public boolean contains(Vec2i point) {
        return (this.position.x <= point.x) && (point.x < this.position.x + this.size.x) &&
                (this.position.y <= point.y) && (point.y < this.position.y + this.size.y);
    }

    public int xmax() {
        return this.position.x + this.size.x - 1;
    }

    public int ymax() {
        return this.position.y + this.size.y - 1;
    }

    public int xmin() {
        return this.position.x;
    }

    public int ymin() {
        return this.position.y;
    }

    public String toString() {
        return String.format("position: (%s), size: (%s)", this.position.toString(), this.size.toString());
    }
}
