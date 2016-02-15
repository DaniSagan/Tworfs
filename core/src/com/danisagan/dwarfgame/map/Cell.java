package com.danisagan.dwarfgame.map;

import com.danisagan.dwarfgame.entities.Entity;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.geometry.Vec3i;
import com.danisagan.dwarfgame.materials.Material;
import com.danisagan.dwarfgame.materials.MaterialManager;

/**
 * Created by daniel on 10/18/15.
 */
public class Cell {
    private Vec3i position;
    private Material floorMaterial;
    private Material blockMaterial;
    private Entity entity;

    public Cell(Vec3i position) {
        this.position = position;
        this.blockMaterial = MaterialManager.none;
        this.floorMaterial = MaterialManager.none;
        this.entity = null;
    }

    public Vec3i getPosition() {
        return position;
    }

    public void setPosition(Vec3i position) {
        this.position = position;
    }

    public Material getFloorMaterial() {
        return floorMaterial;
    }

    public void setFloorMaterial(Material floorMaterial) {
        this.floorMaterial = floorMaterial;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public void setBlockMaterial(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
