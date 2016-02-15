package com.danisagan.dwarfgame.gfx;

import com.danisagan.dwarfgame.geometry.Rect2i;

/**
 * Created by daniel on 2/14/16.
 */
public class Indication {
    private int id;
    private Rect2i textureRect;


    public Indication(int id, Rect2i textureRect) {
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
