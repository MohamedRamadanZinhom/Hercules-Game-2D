/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.util.HashMap;

import javax.xml.bind.ValidationException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.exception.KeyException;
import com.engine.exception.OverwriteException;
import com.engine.loader.MapGenerator;
import com.engine.world.BodyProperty;
import com.engine.world.World2D;

public class World {

	public static final short BIT_GROUND = 2;
	public static final short BIT_PLAYER = 4;
	public static final short BIT_ENEMY_T1 = 8;
	public static final short BIT_ENEMY_T2 = 16;

	public static final short BIT_RANDOM_OBJECTS = 32; // coins, health, ...etc
	public static final short BIT_BOUNDS = 64; // World BOUNDS

	public static final short BIT_ANY = -1;

	/**
	 * @param mainDir
	 * @param mapFname
	 * @param mapId
	 */
	public World(String mainDir, String[] mapFname, String[] mapId) {

		for (int i = 0; i < mapId.length; i++) {

			try {

				MapGenerator.setMap(mainDir, mapFname[i], mapId[i]);

			} catch (OverwriteException error) {

				error.printStackTrace();
			}
		}
	}

	/**
	 * Convert Map to box2d bodies, for BodyProperty != null
	 * 
	 * @param mapId
	 * @param bodyTypeSignature
	 * @param gravityX
	 * @param gravityY
	 * @param doSleep
	 * @param GU
	 * 
	 * @return World2D
	 */
	public World2D getWorldFromMap(String mapId, HashMap<Integer, BodyProperty> bodyTypeSignature, float gravityX,
			float gravityY, boolean doSleep, float GU) {

		World2D world = null;

		try {

			world = MapGenerator.buildWorld(mapId, bodyTypeSignature, gravityX, gravityY, doSleep, GU);

		} catch (ValidationException error) {

			error.printStackTrace();
		}

		return world;
	}

	public void renderMap(String mapId, OrthographicCamera envCam) {

		try {

			MapGenerator.renderMap(mapId, envCam);

		} catch (KeyException error) {

			error.printStackTrace();
		}
	}
}
