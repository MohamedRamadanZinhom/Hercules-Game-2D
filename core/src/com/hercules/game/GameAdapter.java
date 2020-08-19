/** @author Z. Mohamed Osama */

package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.loader.MapGenerator;
import com.engine.world.BodyProperty;
import com.engine.world.World2D;
import com.hercules.events.CollisionSignal;
import com.hercules.init.Player;
import com.hercules.init.World;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 600; // 960; // Center X
	public static final int V_HEIGHT = 300; // 540; // Center Y

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
	OrthographicCamera playerCam;
	OrthographicCamera box2dCam; // debug

	// ============ Player
	Player player;

	public static final String playerName = "m-zayan";

	public static float posX = 150.0f;
	public static float posY = 115.0f;

	public static float speed = 4.0f;
	public static final float runScale = 5.0f;
	public static final float jumpScale = 160.0f;
	public static final float smashingScale = 20.0f;
	// =============

	@Override
	public void create() {

		bodyTypeSignature = new HashMap<Integer, BodyProperty>();

		BodyProperty bounds = new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, World.BIT_BOUNDS, World.BIT_ANY,
				false);

		BodyProperty ground = new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, World.BIT_GROUND, World.BIT_ANY,
				false);

		// bodyTypeSignature: for each layer (in order, bottom-up)
		bodyTypeSignature.put(0, null);
		bodyTypeSignature.put(1, bounds);
		bodyTypeSignature.put(2, ground);

		// Build map
		world = new World(mapDir, mapFname, mapId);

		world2d = world.getWorldFromMap("1", bodyTypeSignature, gravityX, gravityY, true, GU);
		world2d.setContactListener(new CollisionSignal());

		// Player
		player = new Player(playerName, posX, posY, speed, runScale, jumpScale, smashingScale);
		player.initPlayer(world2d);

		// Environment - Box2d Camera
		float width = Gdx.graphics.getWidth() / GU;
		float height = Gdx.graphics.getHeight() / GU;

		box2dCam = new OrthographicCamera(width, height); // box2d debugging camera

		box2dCam.position.x = box2dCam.viewportWidth / 2;
		box2dCam.position.y = box2dCam.viewportHeight / 2 - 1.0f;
		box2dCam.update();

		// Environment - Camera

		envCam = new OrthographicCamera(1200, 600);
		envCam.position.x = envCam.viewportWidth / 2;
		envCam.position.y = envCam.viewportHeight / 2;
		envCam.update();

		// Player Camera
//		GameLevel level = new GameLevel(0);
	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		// Input
		player.update(false);

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Map
		world.renderMap("1", envCam);

		// Debug
		world2d.renderBox2dDebug(box2dCam.combined);
		world2d.update(FPS, 6, 2);

		box2dCam.update();

		// Player
		player.animate(envCam);
	}

	@Override
	public void dispose() {
		player.dispose();
		world2d.dispose();
		MapGenerator.dispose();
	}

}
