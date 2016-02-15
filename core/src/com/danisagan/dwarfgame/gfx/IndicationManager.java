package com.danisagan.dwarfgame.gfx;

import com.danisagan.dwarfgame.geometry.Rect2i;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.materials.Material;

/**
 * Created by daniel on 2/14/16.
 */
public class IndicationManager {

    public static int TILE_SIZE = 64;
    public static Indication NONE = new Indication(0, new Rect2i());
    public static Indication JOB = new Indication(1, new Rect2i(new Vec2i(0*TILE_SIZE, 0), new Vec2i(TILE_SIZE, TILE_SIZE)));

}
