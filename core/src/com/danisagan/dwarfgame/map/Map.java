package com.danisagan.dwarfgame.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.danisagan.dwarfgame.entities.Entity;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.geometry.Vec3i;
import com.danisagan.dwarfgame.materials.Material;
import com.danisagan.dwarfgame.materials.MaterialManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by daniel on 10/18/15.
 */
public class Map {
    private List<Level> levels;
    private Vec2i size;
    private Entity dwarf;
    private JobManager jobManager = new JobManager();

    public Vec2i getSize() {
        return size;
    }

    public Map(Vec2i size, int levelCount) {
        this.size = size;

        levels = new ArrayList<Level>();
        Random rn = new Random();
        for(int k = 0; k < levelCount; k++) {
            levels.add(new Level());
            levels.get(k).create(size, MaterialManager.grass, k);
            for(int j = 0; j < this.size.y; j++) {
                for(int i = 0; i < this.size.x; i++) {
                    int r = rn.nextInt() % 11;
                    if(r == 0) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.grass);
                    } else if(r == 1) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.dirt);
                    } else if(r == 2) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.sand);
                    } else if(r == 3) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.water);
                    } else if(r == 4) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.stone);
                    } else if(r == 5) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.tree);
                    } else if(r == 6) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.tree1);
                    } else if(r == 7) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.tree2);
                    } else if(r == 8) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.cactus);
                    } else if(r == 9) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.boulder);
                    } else if(r == 10) {
                        levels.get(k).getCell(new Vec2i(i, j)).setFloorMaterial(MaterialManager.log);
                    }
                }
            }
        }
        dwarf = new Entity(new Vec3i(64, 64, 0), this);
    }

    public Map(String filename) {
        Texture img = new Texture(filename);
        if (!img.getTextureData().isPrepared()) {
            img.getTextureData().prepare();
        }
        Pixmap pixels = img.getTextureData().consumePixmap();

        Vec2i size = new Vec2i(img.getWidth(), img.getHeight());
        this.levels = new ArrayList<Level>();
        this.levels.add(new Level());
        levels.get(0).create(size, MaterialManager.grass, 0);
        Random rn = new Random();
        for(int j = 0; j < size.y; j++) {
            for(int i = 0; i < size.x; i++) {
                Color pxColor = new Color(pixels.getPixel(i, size.y-j-1));
                if(pxColor.equals(Color.GREEN)) {
                    if(rn.nextInt(10) < 1) {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.tree);
                    } else if(rn.nextInt(10) < 1) {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.tree1);
                    } else if(rn.nextInt(10) < 1) {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.tree2);
                    } else {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.grass);
                    }
                } else if(pxColor.equals(Color.BLUE)) {
                    levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.water);
                } else if(pxColor.equals(Color.RED)) {
                    levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.dirt);
                } else if(pxColor.equals(Color.YELLOW)) {
                    if(rn.nextInt(20) < 1) {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.cactus);
                    } else {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.sand);
                    }
                } else if(pxColor.equals(Color.GRAY)) {
                    if(rn.nextInt(20) < 1) {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.boulder);
                    } else {
                        levels.get(0).getCell(i, j).setFloorMaterial(MaterialManager.stone);
                    }
                }
            }
        }
        this.size = size;
        this.dwarf = new Entity(new Vec3i(64, 64, 0), this);
    }

    public Cell getCell(int x, int y, int level) {
        return this.levels.get(level).getCell(x, y);
    }

    public Cell getCell(Vec2i position, int level) {
        return this.levels.get(level).getCell(position);
    }

    public Cell getCell(Vec3i position) {
        return this.levels.get(position.z).getCell(position.x, position.y);
    }

    public void update() {
        if(dwarf.getCurrentJob() == null && jobManager.getJobCount() != 0) {
            if(jobManager.assignJob(dwarf)) {
                Gdx.app.log("Map", "Assigned job to dwarf");
            }
        }
        dwarf.update(this);
    }

    public boolean applyAction(Vec2i tileIndex) {
        Cell cell = getCell(tileIndex, 0);
        Material floorMaterial = cell.getFloorMaterial();
        if(floorMaterial.getId() == MaterialManager.grass.getId()) {
            cell.setFloorMaterial(MaterialManager.dirt);
            return true;
        }
        if(floorMaterial.getId() == MaterialManager.tree.getId() || floorMaterial.getId() == MaterialManager.tree1.getId() || floorMaterial.getId() == MaterialManager.tree2.getId()) {
            cell.setFloorMaterial(MaterialManager.log);
            return true;
        }
        return false;
    }

    public JobManager getJobManager() {
        return jobManager;
    }
}
