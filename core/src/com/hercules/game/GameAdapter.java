/** @author Z. Mohamed Osama */

package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.BodyProperty;
import com.engine.world.World2D;
import com.hercules.init.World;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 960; // Center X
	public static final int V_HEIGHT = 540; // Center Y
	public static final int SCALE = 2; // Center Scale

	public static final float GU = 1.0f; // Box2D Game-Unit Scaler
	public static final float FPS = 1 / 60f; // Frame per second (Delta Time)

	// Test World
	public static final String mapDir = "./epic-scene/map/";

	public static final String[] mapFname = { "level-1.tmx" };
	public static final String[] mapId = { "1" };

	World world;
	public static final float gravityX = 0.0f;
	public static final float gravityY = -9.81f;
	public static final short[] categoryBits = { 2, 8 };
	public static final short[][] bitsMask = { { 4 }, { 2 }, { 12 } };

	HashMap<Integer, BodyProperty> bodyTypeSignature;

	World2D world2d; // game world

	OrthographicCamera envCam;

	@Override
	public void create() {

		float width = Gdx.graphics.getWidth() / GU;
		float height = Gdx.graphics.getHeight() / GU;

		envCam = new OrthographicCamera(width, height);
		envCam.setToOrtho(false);

		bodyTypeSignature = new HashMap<Integer, BodyProperty>();

		// bodyTypeSignature: for each layer
		bodyTypeSignature.put(0, null);
		bodyTypeSignature.put(1, null);
		bodyTypeSignature.put(2, new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, categoryBits[0], bitsMask[0]));
		bodyTypeSignature.put(3,
				new BodyProperty(BodyType.DynamicBody, 1.0f, 0.0f, 0.0f, categoryBits[1], bitsMask[1]));

		world = new World(mapDir, mapFname, mapId);

		world2d = world.getWorldFromMap("1", bodyTypeSignature, gravityX, gravityY, true, GU);
	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.renderMap("1", envCam);
		envCam.update();

		world2d.renderBox2dDebug(envCam.combined);
		world2d.update(FPS, 6, 2);
	}

	@Override
	public void dispose() {

	}

}
