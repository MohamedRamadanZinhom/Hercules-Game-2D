package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.*;

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

	@Override
	public void create() {

		envCam = new OrthographicCamera(V_WIDTH * SCALE / GU, V_HEIGHT * SCALE / GU);
	}

	@Override
	public void render() {

	}

	@Override
	public void dispose() {

	}

}
