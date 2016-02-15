package com.danisagan.dwarfgame.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.danisagan.dwarfgame.geometry.Matrix2x2f;
import com.danisagan.dwarfgame.geometry.Rect2i;
import com.danisagan.dwarfgame.geometry.Vec2f;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.map.Job;
import com.danisagan.dwarfgame.map.Map;
import com.danisagan.dwarfgame.map.Utils;
import com.danisagan.dwarfgame.materials.MaterialManager;
import com.danisagan.dwarfgame.utils.Clock;

import java.util.logging.Logger;

/**
 * Created by daniel on 10/18/15.
 */
public class MapRenderer {
    public static final Vec2i V_X = new Vec2i(32, 17);
    public static final Vec2i V_Y = new Vec2i(-32, 17);
    public static final int SCALE = 4;
    static final Vec2i TEXTURE_OFFSET = new Vec2i(-32, 0);

    private Texture tilesTexture;
    private Texture indicationsTexture;
    private Clock clock = new Clock();

    public MapRenderer() {
        tilesTexture = new Texture("tiles/tiles64.png");
        indicationsTexture = new Texture("tiles/indications.png");
    }

    public void render(Map map, SpriteBatch batch, IsoCamera camera) {
        //Utils.DiagonalGenerator generator = new Utils.DiagonalGenerator(new Rect2i(new Vec2i(0, 0), map.getSize()));
        Rect2i visibleRect = getVisibleRect(camera, map.getSize());
        Utils.DiagonalGenerator generator = new Utils.DiagonalGenerator(visibleRect);
        Rect2i textureRect;
        Vec2i texturePosition;
        batch.begin();
        while (generator.hasNext()) {
            Vec2i p = generator.next();
            textureRect = map.getCell(p.x, p.y, 0).getFloorMaterial().getTextureRect();
            texturePosition = Vec2i.add(Vec2i.scale(TEXTURE_OFFSET, camera.getScale()), new Vec2i(screenToGdxPosition(getPixelForPos(p.toVec2f(), camera), camera)));

            batch.draw(tilesTexture, texturePosition.x, texturePosition.y, 64.f*camera.getScale(), 64.f*camera.getScale(), textureRect.position.x, textureRect.position.y, textureRect.size.x, textureRect.size.y, false, false);
            if(map.getCell(p, 0).getEntity() != null) {
                textureRect = MaterialManager.dwarf.getTextureRect();
                batch.draw(tilesTexture, texturePosition.x, texturePosition.y, 64.f*camera.getScale(), 64.f*camera.getScale(), textureRect.position.x, textureRect.position.y, textureRect.size.x, textureRect.size.y, false, false);
            }
        }
        renderIndications(map, batch, camera);
        batch.end();
    }

    public Vec2f getPixelForPos(Vec2f position, IsoCamera camera) {
        return camera.getScreenPos(position);
    }

    public Vec2f getPosForPixel(Vec2i pixel, IsoCamera camera) {
        return camera.getRealPos(pixel.toVec2f());
    }

    public Rect2i getVisibleRect(IsoCamera camera, Vec2i mapSize) {
        int xmin = Utils.saturate((int)getPosForPixel(new Vec2i(0, camera.getRect().ymax()), camera).x, 0, mapSize.x - 1);
        int xmax = Utils.saturate((int)getPosForPixel(new Vec2i(camera.getRect().xmax(), 0), camera).x+1, 0, mapSize.x - 1);
        int ymin = Utils.saturate((int)getPosForPixel(camera.getViewSize(), camera).y, 0, mapSize.y - 1);
        int ymax = Utils.saturate((int)getPosForPixel(new Vec2i(0, 0), camera).y+1, 0, mapSize.y -1);
        return new Rect2i(new Vec2i(xmin, ymin), new Vec2i(xmax-xmin, ymax-ymin));
    }

    public Vec2f screenToGdxPosition(Vec2f screenPosition, Camera camera) {
        return new Vec2f(screenPosition.x, camera.getViewSize().y-screenPosition.y);
    }

    private void renderIndications(Map map, SpriteBatch batch, IsoCamera camera) {
        Rect2i visibleRect = getVisibleRect(camera, map.getSize());
        for(Job j: map.getJobManager().getJobList()) {
            if(visibleRect.contains(j.getTile())) {
                renderIndication(map, batch, camera, IndicationManager.JOB, Vec2f.add(j.getTile().toVec2f(), new Vec2f(0.5f, 0.5f)));
            }
        }
    }

    private void renderIndication(Map map, SpriteBatch batch, IsoCamera camera, Indication indication, Vec2f position) {
        Rect2i textureRect = indication.getTextureRect();
        Vec2i texturePosition = Vec2i.add(Vec2i.scale(TEXTURE_OFFSET, camera.getScale()), new Vec2i(screenToGdxPosition(getPixelForPos(position, camera), camera)));
        float yOffset = 30.f * (float)Math.abs(Math.sin(4.f * clock.getElapsedTime()));
        batch.draw(indicationsTexture, texturePosition.x, (int)yOffset + texturePosition.y, 64.f*camera.getScale(), 64.f*camera.getScale(), textureRect.position.x, textureRect.position.y, textureRect.size.x, textureRect.size.y, false, false);
        Gdx.app.log("MapRenderer", "Rendering indication");
    }
}
