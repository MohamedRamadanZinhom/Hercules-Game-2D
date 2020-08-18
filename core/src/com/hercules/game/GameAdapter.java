/** @author Z. Mohamed Osama */

package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.world.Body2D;
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

	public static final short[] categoryBits = { World.BIT_GROUND, World.BIT_RANDOM_OBJECTS };
	public static final short[] bitsMask = { World.BIT_RANDOM_OBJECTS, World.BIT_GROUND };

	HashMap<Integer, BodyProperty> bodyTypeSignature;

	World2D world2d; // game world

	OrthographicCamera envCam;
	OrthographicCamera playerCam;
	OrthographicCamera box2dCam; // debug

	// Test Player | Enemy
	Player player;
	public static final String playerName = "m-zayan";
	public static final String[][] spritesDirname = { { "./sprite-sheets/demon.png" },
			{ "./sprite-sheets/demon-left.png" } };

	public static final int[] FRAME_ROWS = { 1, 1 };
	public static final int[] FRAME_COLS = { 21, 21 };

	/**
	 * Use the same keys: pass reversed key order parameter - {@link #keysOrder)}
	 */
	public static final int[][] startKeys = { { 0, 2, 8, 12, 14 }, { 0, 2, 8, 12, 14 } };
	public static final int[][] endKeys = { { 2, 8, 12, 14, 20 }, { 2, 8, 12, 14, 20 } };

	public static final String[][] typeKeys = { { "idle", "walk", "attack", "hurt", "die" },
			{ "idle", "walk", "attack", "hurt", "die" } };

	public static final String[] keysOrder = { "right", "left" };

	public static final float FPS_SCALE = 1 / 30.0f;
	public static final float frameDuration = 0.0025f;

	public static float posX = 100.0f;
	public static float posY = 100.0f;
	public static float speed = 100.0f;

	public static int defaultIndex = 0;
	public static String currentMode = "walk";

	public static final float speedScale = 1.0f;

	@Override
	public void create() {

		float width = Gdx.graphics.getWidth() / GU;
		float height = Gdx.graphics.getHeight() / GU;

		envCam = new OrthographicCamera();
		envCam.setToOrtho(false);

		box2dCam = new OrthographicCamera(width, height); // box2d debugging camera

		box2dCam.position.x = V_WIDTH / GU;
		box2dCam.position.y = V_HEIGHT / GU;
		box2dCam.update();

		bodyTypeSignature = new HashMap<Integer, BodyProperty>();

		// bodyTypeSignature: for each layer (in order, bottom-up)
		bodyTypeSignature.put(0, null);
		bodyTypeSignature.put(1, null);
		bodyTypeSignature.put(2, new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, categoryBits[0], bitsMask[0]));
		bodyTypeSignature.put(3, new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, categoryBits[1], bitsMask[1]));

		world = new World(mapDir, mapFname, mapId);

		world2d = world.getWorldFromMap("1", bodyTypeSignature, gravityX, gravityY, true, GU);
		world2d.setContactListener(new CollisionSignal());

		// Player
		player = new Player(spritesDirname, playerName, posX, posY, speed, FPS_SCALE, frameDuration, defaultIndex,
				currentMode);

		player.initPlayer(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);

		// KinematicBody
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20.0f / GU, 20.0f / GU);
		world2d.createKinematicBody(shape, World.BIT_PLAYER, World.BIT_GROUND, "player");

		Body2D.debug(); // print bodies id.

	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		player.handleInput(speedScale, true); // Player Input

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.renderMap("1", envCam);
		envCam.update();

		world2d.renderBox2dDebug(box2dCam.combined);
		world2d.update(FPS, 6, 2);

		box2dCam.update();

		// player

		Body2D.bodies.get("player").get(0).setTransform(new Vector2(player.getPosX() / GU, player.getPosY() / GU),
				0.0f); // Collision - Actor
		player.animate();
	}

	@Override
	public void dispose() {

	}

}
