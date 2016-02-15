package com.danisagan.dwarfgame.materials;

import com.danisagan.dwarfgame.geometry.Rect2i;

/**
 * Created by daniel on 10/18/15.
 */
public class Material {
    private int id;
    private Rect2i textureRect;

    public Material(int id, Rect2i textureRect) {
        this.id = id;
        this.textureRect = textureRect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rect2i getTextureRect() {
        return textureRect;
    }

    public void setTextureRect(Rect2i textureRect) {
        this.textureRect = textureRect;
    }


}
