package com.danisagan.dwarfgame.materials;

import com.danisagan.dwarfgame.geometry.Rect2i;
import com.danisagan.dwarfgame.geometry.Vec2i;

/**
 * Created by daniel on 10/18/15.
 */
public class MaterialManager {
    public static int TILE_SIZE = 64;
    public static Material none = new Material(0, new Rect2i());
    public static Material stone = new Material(1, new Rect2i(new Vec2i(1*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material grass = new Material(2, new Rect2i(new Vec2i(2*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material dirt = new Material(3, new Rect2i(new Vec2i(3*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material sand = new Material(4, new Rect2i(new Vec2i(4*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material water = new Material(5, new Rect2i(new Vec2i(5*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material tree = new Material(6, new Rect2i(new Vec2i(6*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material tree1 = new Material(7, new Rect2i(new Vec2i(7*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material tree2 = new Material(8, new Rect2i(new Vec2i(0*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material cactus = new Material(9, new Rect2i(new Vec2i(1*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material boulder = new Material(10, new Rect2i(new Vec2i(2*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material log = new Material(11, new Rect2i(new Vec2i(3*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    public static Material dwarf = new Material(12, new Rect2i(new Vec2i(1*TILE_SIZE, 2*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    //public static Material palmTree = new Material(0, new Rect2i(new Vec2i(5*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    //public static Material Tree = new Material(0, new Rect2i(new Vec2i(6*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    //public static Material cactus = new Material(0, new Rect2i(new Vec2i(7*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));
    //public static Material boulder = new Material(0, new Rect2i(new Vec2i(1*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
    //public static Material flowers = new Material(0, new Rect2i(new Vec2i(2*TILE_SIZE, 1*TILE_SIZE), new Vec2i(TILE_SIZE, TILE_SIZE)));
}
