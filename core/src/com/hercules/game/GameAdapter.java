package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.engine.exception.KeyException;
import com.engine.loader.MapGenerator;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 960; // Center X
	public static final int V_HEIGHT = 540; // Center Y
	public static final int SCALE = 2; // Center Scale

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler
	public static final float FPR = 1 / 60f; // Frame per second (Delta Time)

	// Test MapGenerator
	public static final String MAP_DIR = "./epic-scene/map/";
	public static final String[] MAP = { "level-1.tmx" };

	OrthographicCamera envCam;

	@Override
	public void create() {
		
		// Test MapGenerator
		MapGenerator.setMap(MAP_DIR, MAP[0], 0);

		envCam = new OrthographicCamera(V_WIDTH * SCALE, V_HEIGHT * SCALE);
		
		float x = MapGenerator.getLayerWidth("0", 0);
		float y = MapGenerator.getLayerHeight("0", 0);
				
		envCam.setToOrtho(false, x, y * 2.25f);
		envCam.update();
	}

	@Override
	public void render() {
		
		/////////////// Handle Inputs //////////////
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
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
	}

	@Override
	public void dispose() {
		MapGenerator.disposeAll();
	}

}
