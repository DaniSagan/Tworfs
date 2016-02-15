package com.danisagan.dwarfgame.map;

import com.danisagan.dwarfgame.entities.Entity;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.materials.Material;

/**
 * Created by daniel on 2/13/16.
 */
public class Job {
    private Vec2i tile;
    private Material startMaterial;
    private Material endMaterial;
    private Entity worker;

    public Job(Vec2i tile, Material startMaterial, Material endMaterial) {
        this.tile = tile;
        this.startMaterial = startMaterial;
        this.endMaterial = endMaterial;
    }

    public Vec2i getTile() {
        return tile;
    }

    public void setTile(Vec2i tile) {
        this.tile = tile;
    }

    public Material getStartMaterial() {
        return startMaterial;
    }

    public void setStartMaterial(Material startMaterial) {
        this.startMaterial = startMaterial;
    }

    public Material getEndMaterial() {
        return endMaterial;
    }

    public void setEndMaterial(Material endMaterial) {
        this.endMaterial = endMaterial;
    }

    public Entity getWorker() {
        return worker;
    }

    public void setWorker(Entity worker) {
        this.worker = worker;
    }
}
