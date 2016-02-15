package com.danisagan.dwarfgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.danisagan.dwarfgame.geometry.Vec2f;
import com.danisagan.dwarfgame.geometry.Vec2i;
import com.danisagan.dwarfgame.gfx.Camera;
import com.danisagan.dwarfgame.gfx.IsoCamera;
import com.danisagan.dwarfgame.gfx.MapRenderer;
import com.danisagan.dwarfgame.map.Job;
import com.danisagan.dwarfgame.map.JobManager;
import com.danisagan.dwarfgame.map.Map;
import com.danisagan.dwarfgame.utils.Clock;

import java.util.HashMap;

public class DwarfGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	BitmapFont font;
	Map map;
	MapRenderer mapRenderer;
	IsoCamera camera;
	Vec2f touchPosition = new Vec2f();
	Clock clock = new Clock();

	static class TouchInfo {
		public Vec2i position;
		public Vec2i deltaPosition;
		public State state = State.UP;
		public enum State {
			UP,
			DOWN,
			DRAGGED
		}
		public long time;
	}

	private java.util.Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		//map = new Map(new Vec2i(256, 256), 1);
		map = new Map("maps/map2.png");
		camera = new IsoCamera(new Vec2f(0.f, 0.f), new Vec2i(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), MapRenderer.V_X.toVec2f().getScaled(MapRenderer.SCALE), MapRenderer.V_Y.toVec2f().getScaled(MapRenderer.SCALE));
		mapRenderer = new MapRenderer();
		Gdx.input.setInputProcessor(this);
		for(int i = 0; i < 5; i++){
			touches.put(i, new TouchInfo());
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mapRenderer.render(this.map, this.batch, this.camera);
		batch.begin();
		font.draw(batch, touchPosition.toString(), 20, 20);
		batch.end();
		this.handleInput();
		this.update();
		//font.draw(batch, "Hello World!", 200, 200);
	}

	public void handleInput() {
		if(this.touches.get(0).state == TouchInfo.State.DRAGGED) {
			Vec2f screenDelta = this.touches.get(0).deltaPosition.toVec2f();
			Vec2f movement = camera.getRealDeltaPos(screenDelta);
			this.camera.move(movement);
		}

		if(this.touches.get(0).state == TouchInfo.State.DOWN) {
			// Touch down for longer than a second
			if(System.currentTimeMillis() - this.touches.get(0).time > 1e3) {
				Vec2f mapPos = camera.getRealPos(touches.get(0).position.toVec2f());
				Job job = new Job(mapPos.toVec2i(), null, null);
				map.getJobManager().addNewJob(job);
				Gdx.app.log("DwarfGame", "Added new job @ " + job.getTile().toString());
				//map.applyAction(mapPos.toVec2i());
				Gdx.input.vibrate(20);
				this.touches.get(0).time = TimeUtils.millis();
			}
		}
	}

	public void update() {
		float frameTime = clock.reset();
		this.camera.update(frameTime);
		//Gdx.app.log("DwarfGame", "FPS: " + (int)(1.f / frameTime));
		this.map.update();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			touches.get(pointer).position = new Vec2i(screenX, screenY);
			touches.get(pointer).state = TouchInfo.State.DOWN;
			touches.get(pointer).time = System.currentTimeMillis();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5) {
			touches.get(pointer).position = new Vec2i(screenX, screenY);
			touches.get(pointer).state = TouchInfo.State.UP;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(pointer < 5) {
			touches.get(pointer).deltaPosition = Vec2i.subs(new Vec2i(screenX, screenY), touches.get(pointer).position);
			touches.get(pointer).deltaPosition.x = -touches.get(pointer).deltaPosition.x;
			touches.get(pointer).position = new Vec2i(screenX, screenY);
			if(touches.get(pointer).deltaPosition.x == 0 && touches.get(pointer).deltaPosition.y == 0) {
				touches.get(pointer).state = TouchInfo.State.DOWN;
			} else {
				touches.get(pointer).state = TouchInfo.State.DRAGGED;
			}
		}
		touchPosition = mapRenderer.getPosForPixel(new Vec2i(screenX, Gdx.graphics.getHeight() - screenY - 1), camera);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
