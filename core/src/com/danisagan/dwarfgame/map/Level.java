package com.danisagan.dwarfgame.map;

import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.geometry.Vec3i;
import com.danisagan.dwarfgame.materials.Material;

/**
 * Created by daniel on 10/18/15.
 */
public class Level {
    private Vec2i size;
    private Cell[] cells;
    private int zPosition;

    public void create(Vec2i size, Material material, int zPosition) {
        this.zPosition = zPosition;
        this.cells = new Cell[size.x * size.y];
        this.size = size;
        for(int i = 0; i < this.cells.length; i++) {
            try {
                this.cells[i] = new Cell(new Vec3i(i%this.size.x, i / this.size.x, zPosition));
                this.cells[i].setBlockMaterial(material);
                this.cells[i].setFloorMaterial(material);
            } catch (Exception ex) {
                int a = 0;
            }
        }
    }

    public Cell getCell(int x, int y) {
        return this.cells[x + this.size.x*y];
    }

    public Cell getCell(Vec2i position) {
        return this.cells[position.x + this.size.x*position.y];
    }
}
