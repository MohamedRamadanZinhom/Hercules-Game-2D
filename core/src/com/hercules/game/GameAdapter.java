package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.engine.animation.AnimationGenerator;
import com.engine.exception.KeyException;
import com.engine.exception.OverwriteException;
import com.engine.loader.MapGenerator;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 960; // Center X
	public static final int V_HEIGHT = 540; // Center Y
	public static final int SCALE = 2; // Center Scale

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler
	public static final float FPS = 1 / 60f; // Frame per second (Delta Time)

	// Test MapGenerator
	public static final String MAP_DIR = "./epic-scene/map/";
	public static final String[] MAP = { "level-1.tmx" };
	public static int initial_zoom_level = 1500;

	OrthographicCamera envCam;
	OrthographicCamera playerCam;

	// Test AnimationGenerator
	AnimationGenerator demon;

	public static final String name = "demon";
	public static final String path = "./sprite-sheets/demon.png";
	public static final float frameDuration = 0.09f;
	public static final int nFrame = 21;

	public static final int[] startKeys = { 0, 3, 9, 13, 15 };
	public static final int[] endKeys = { 2, 8, 12, 14, 20 };
	public static final String[] typeKeys = { "idle", "walk", "attack", "hurt", "death" };

	// init-render
	public float stateTime = 0.0f;
	public float x_postion = 32.0f; // player - x
	public float y_postion = 32.0f; // player - y
	public String mode = "idle";

	@Override
	public void create() {

		// Test MapGenerator
		MapGenerator.setMap(MAP_DIR, MAP[0], 0);

		// Test AnimationGenerator

		demon = new AnimationGenerator(path); // enemy
		try {
			demon.addAnimation(name, nFrame, frameDuration, startKeys, endKeys, typeKeys);
		} catch (OverwriteException error) {
			error.printStackTrace();
		}

		float x = MapGenerator.getLayerWidth("0", 0);
		float y = MapGenerator.getLayerHeight("0", 0);

		envCam = new OrthographicCamera(V_WIDTH * SCALE, V_HEIGHT * SCALE);
		envCam.setToOrtho(false, x - initial_zoom_level, y * 2.25f - initial_zoom_level);

	}

	@Override
	public void render() {

		/////////////// Handle Inputs //////////////
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x_postion += 3;
			mode = "walk";
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			x_postion -= 3;
			mode = "walk";
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			mode = "attack";
		}

		///////////// Clear Screen /////////////////////////
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

		//////////////// Update Camera ///////////////////
		envCam.update();

		//////////////// World ///////////////////////////
		try {
			MapGenerator.renderMap("0", envCam);
		} catch (KeyException error) {
			error.printStackTrace();
		}

		///////////// Update /////////////////////

		demon.animate(name, mode, x_postion, y_postion);

		// re-init
		mode = "idle";
	}

	@Override
	public void dispose() {
		
		MapGenerator.disposeAll();
		demon.dispose();
		AnimationGenerator.disposeAll();
	}

}
