package com.danisagan.dwarfgame.map;

import com.danisagan.dwarfgame.geometry.Rect2i;
import com.danisagan.dwarfgame.geometry.Vec2i;

/**
 * Created by daniel on 10/18/15.
 */
public class Utils {
    /*public static class DiagonalGenerator {
        private Vec2i currPos;
        private Vec2i size;
        private Rect2i rect;
        private boolean finished = false;

        public DiagonalGenerator(Vec2i size) {
            this.size = size;
            this.currPos = Vec2i.add(size, new Vec2i(-1, -1));
            this.rect = new Rect2i(new Vec2i(0, 0), this.size);
            this.finished = false;
        }

        public void start() {
            this.currPos = size;
            this.finished = false;
        }

        public int getCurrDiagonal() {
            return this.currPos.x + this.currPos.y;
        }

        public Vec2i next() {
            Vec2i res = this.currPos;
            if(this.currPos.x == 0 && this.currPos.y == 0) {
                this.finished = true;
            }
            if(!this.finished) {
                this.currPos = Vec2i.add(this.currPos, new Vec2i(1, -1));
                if(!this.rect.contains(this.currPos)) {
                    this.currPos = this.getStartForDiagonal(this.getCurrDiagonal() - 1);
                }
            }
            return res;
        }

        public boolean hasNext() {
            return !this.finished;
        }

        public Vec2i getStartForDiagonal(int diagonal) {
            if(diagonal > (this.size.x-1) + (this.size.y-1) || diagonal < 0) {
                throw new IllegalArgumentException("Bad value for diagonal");
            } else {
                if(diagonal < this.size.y) {
                    return new Vec2i(0, diagonal);
                } else {
                    return new Vec2i(diagonal - (this.size.y - 1), this.size.y - 1);
                }
            }
        }
    }*/

    public static class DiagonalGenerator {
        private Vec2i currPos;
        private Rect2i rect;
        private boolean finished = false;

        public DiagonalGenerator(Rect2i rect) {
            this.rect = rect;
            this.currPos = new Vec2i(rect.xmax(), rect.ymax());
            this.finished = false;
        }

        public void start() {
            this.currPos = new Vec2i(rect.xmax(), rect.ymax());
            this.finished = false;
        }

        public int getCurrDiagonal() {
            return this.currPos.x + this.currPos.y;
        }

        public Vec2i next() {
            Vec2i res = this.currPos;
            if(this.rect.size.x == 0 || this.rect.size.y == 0) {
                this.finished = true;
            }
            if(this.currPos.x == this.rect.xmin() && this.currPos.y == this.rect.ymin()) {
                this.finished = true;
            }
            if(!this.finished) {
                this.currPos = Vec2i.add(this.currPos, new Vec2i(1, -1));
                if(!this.rect.contains(this.currPos)) {
                    this.currPos = this.getStartForDiagonal(this.getCurrDiagonal() - 1);
                }
            }
            return res;
        }

        public boolean hasNext() {
            return !this.finished;
        }

        public Vec2i getStartForDiagonal(int diagonal) {
            if(diagonal > this.rect.xmax() + this.rect.ymax() || diagonal < this.rect.xmin() + this.rect.ymin()) {
                throw new IllegalArgumentException("Bad value for diagonal: " + diagonal + ", rect: " + this.rect.toString());
            } else {
                if(diagonal < this.rect.xmin() + this.rect.ymax()) {
                    return new Vec2i(this.rect.xmin(), diagonal-this.rect.xmin());
                } else {
                    return new Vec2i(diagonal - this.rect.ymax(), this.rect.ymax());
                }
            }
        }
    }

    public static int saturate(int value, int minValue, int maxValue) {
        if(value < minValue) {
            return minValue;
        } else if(value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }

    public static float saturate(float value, float minValue, float maxValue) {
        if(value < minValue) {
            return minValue;
        } else if(value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }
}
