/** @author Z. Mohamed Osama */

package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.world.BodyProperty;
import com.engine.world.World2D;
import com.hercules.init.World;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 960; // Center X
	public static final int V_HEIGHT = 540; // Center Y
	public static final int SCALE = 2; // Center Scale

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler
	public static final float FPS = 1 / 60f; // Frame per second (Delta Time)

	// Test World
	public static final String mapDir = "./epic-scene/map/";

	public static final String[] mapFname = { "level-1.tmx" };
	public static final String[] mapId = { "1" };

	World world;
	public static final float gravityX = 0.0f;
	public static final float gravityY = -9.81f;

	HashMap<Integer, BodyProperty> bodyTypeSignature;

	World2D world2d; // game world

	OrthographicCamera envCam;

	@Override
	public void create() {

		envCam = new OrthographicCamera(Gdx.graphics.getWidth() / GU, Gdx.graphics.getHeight() / GU);

		bodyTypeSignature = new HashMap<Integer, BodyProperty>();

		bodyTypeSignature.put(0, null);
		bodyTypeSignature.put(1, new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f));

		world = new World(mapDir, mapFname, mapId);

		world2d = world.getWorldFromMap("1", bodyTypeSignature, gravityX, gravityY, true, GU);
	}

	@Override
	public void render() {

		world2d.renderBox2dDebug(envCam.combined);
	}

	@Override
	public void dispose() {

	}

}
