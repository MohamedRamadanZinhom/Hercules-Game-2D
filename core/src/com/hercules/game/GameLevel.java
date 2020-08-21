/** @author Z. Mohamed Osama */

package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.loader.MapGenerator;
import com.engine.ui.ConsoleLogger;
import com.engine.world.BodyProperty;
import com.engine.world.Camera2D;
import com.engine.world.World2D;
import com.hercules.events.CollisionSignal;
import com.hercules.init.World;

public class GameLevel { // No. of Levels = 5

	private HashMap<Integer, BodyProperty> bodyTypeSignature;

	public static final String mapDir = "./epic-scene/map/";

	public static final String[] mapFname = { "level-1.tmx" };
	public static final String[] mapId = { "1" };
	public static final int[] nLayers = { 4 };

	public static final float FPS = 1 / 60f; // Frame per second (Delta Time)

	public int level;

	public World gameMap;
	public World2D world2d; // game world

	public Camera2D envCam;
	public Camera2D playerCam;
	public Camera2D box2dCam; // debug

	/**
	 * @param level
	 * @param GU    - Box2D Game-Unit Scaler, or Pixel Per Meter
	 */
	public GameLevel(int level, float V_WIDTH, float V_HEIGHT, float GU) {

		if (level < 0 || level > mapFname.length) {

			ConsoleLogger.setWarning(GameLevel.class.getName(), "Invalid Game Level = " + Integer.toString(level));

			this.level = 1;
		}

		else {

			this.level = level;
		}

		this.gameMap = new World(mapDir, mapFname, mapId);

		this.bodyTypeSignature = new HashMap<Integer, BodyProperty>();
		this.initLevel(); // fill - bodyTypeSignature

		this.world2d = gameMap.getWorldFromMap(mapId[this.level], this.bodyTypeSignature, GameAdapter.gravityX,
				GameAdapter.gravityY, true, GU);

		this.world2d.setContactListener(new CollisionSignal()); // Collision Signal

		// Environment - Box2d Camera
		float width = V_WIDTH / GU;
		float height = V_HEIGHT / GU;

		box2dCam = new Camera2D(width, height); // box2d debugging camera

		box2dCam.position.x = box2dCam.viewportWidth / 2;
		box2dCam.position.y = box2dCam.viewportHeight / 2;
		box2dCam.update();

		// Environment - Camera
		envCam = new Camera2D(V_WIDTH, V_HEIGHT);
		envCam.position.x = envCam.viewportWidth / 2;
		envCam.position.y = envCam.viewportHeight / 2;
		envCam.update();
	}

	/**
	 * fill {@link #bodyTypeSignature} - layer's objects properties
	 */
	private void initLevel() {

		BodyProperty[] layerProperty = new BodyProperty[nLayers[this.level]];

		if (level == 0) {

			layerProperty[0] = null; // null - means, layer has no object property

			layerProperty[1] = null;

			layerProperty[2] = new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, World.BIT_BOUNDS, World.BIT_ANY,
					false);

			layerProperty[3] = new BodyProperty(BodyType.StaticBody, 0.0f, 0.0f, 0.0f, World.BIT_GROUND, World.BIT_ANY,
					false);
		}

		for (int i = 0; i < nLayers[level]; i++) {
			bodyTypeSignature.put(i, layerProperty[i]);
		}

	}

	public void render() {

		this.gameMap.renderMap("1", envCam);

		// Debug
		world2d.renderBox2dDebug(box2dCam.combined);

		// update world
		world2d.update(FPS, 6, 2);

		box2dCam.update();
	}

	public void dispose() {

		this.world2d.dispose();
		MapGenerator.dispose();
	}
}
