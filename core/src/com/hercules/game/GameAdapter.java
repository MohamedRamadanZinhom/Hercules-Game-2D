package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.physics.box2d.*;
import com.engine.exception.KeyException;
import com.engine.exception.OverwriteException;
import com.engine.loader.MapGenerator;
import com.engine.world.World2D;
import com.hercules.init.Enemy;
import com.hercules.init.Player;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 960; // Center X
	public static final int V_HEIGHT = 540; // Center Y
	public static final int SCALE = 2; // Center Scale

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler
	public static final float FPS = 1 / 60f; // Frame per second (Delta Time)

	// Test MapGenerator
//	public static final String MAP_DIR = "./epic-scene/map/";
//	public static final String[] MAP = { "level-1.tmx" };
	public static int initial_zoom_level = 1500;
//
	OrthographicCamera envCam;
//	OrthographicCamera playerCam;
//
//	Enemy enemy;

	World2D world;

	@Override
	public void create() {

//
		envCam = new OrthographicCamera(Gdx.graphics.getWidth() / GU, Gdx.graphics.getHeight() / GU);

//
//		float posX = 100.0f;
//		float posY = 100.0f;
//
//		float speed = 3.0f;
//
//		enemy = new Enemy("Demon", posX, posY, speed, 0.085f, 0.98f);
//		enemy.initEnemy();
//		
//		playerCam = new OrthographicCamera(posX , posY);
//
//
//		try {
//			MapGenerator.setMap(MAP_DIR, MAP[0], "1");
//		} catch (OverwriteException error) {
//			error.printStackTrace();
//		}

		world = new World2D(0, -9.8f, true);

		PolygonShape static_shape = new PolygonShape();
		static_shape.setAsBox(300.0f / GU, 20.0f / GU);

		world.createStaticBody(100.0f, 50.0f, static_shape, true);

		PolygonShape dynamic = new PolygonShape();
		dynamic.setAsBox(20.0f / GU, 20.0f / GU);

		world.createDynamicBody(100.0f, 500.0f, dynamic, true, 1.0f, 20.0f, 1.0f);
		
		PolygonShape dynamic1 = new PolygonShape();
		dynamic1.setAsBox(20.0f / GU, 20.0f / GU);

		world.createDynamicBody(50.0f, 500.0f, dynamic1, true, 1.0f, 50.0f, 1.0f);
	}

	@Override
	public void render() {

//	      Gdx.gl.glClearColor(0, 0.5f, 0.9f, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


//		enemy.handleInput(1.0f, false, playerCam); // input
//
//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//			Gdx.app.exit();
//		}
//		
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		try {
//			MapGenerator.renderMap("1", envCam);
//		} catch (KeyException error) {
//			error.printStackTrace();
//		}
//		
//		playerCam.update();
//		enemy.animate();

		world.renderBox2dDebug(envCam.combined);
		world.update(FPS, 9, 8);
		envCam.update();
	}

	@Override
	public void dispose() {

	}

}
