package com.hercules.init;

import java.util.HashMap;

public final class ResourceManager {

	// Player
	static public Resource getPlayerResources_1() {

		final String mainDir = "./sprite-sheets/player/1/";

		final String[][] spritesDirname = { { mainDir + "iwr.png", mainDir + "jhad.png" },
				{ mainDir + "iwr-left.png", mainDir + "jhad-left.png" } };

		final int[] FRAME_ROWS = { 1, 1 };
		final int[] FRAME_COLS = { 30, 40 };

		final int[][] startKeys = { { 0, 9, 19 }, { 0, 9, 19, 29 } };
		final int[][] endKeys = { { 9, 19, 29 }, { 9, 19, 29, 39 } };

		final String[][] typeKeys = { { "idle", "walk", "run" }, { "jump", "attack", "hurt", "die" } };

		final String[] keysOrder = { "right", "left" };

		final float frameDuration = 0.015f; // Animation - Frame Duration

		final float scale = 1.0f;

		final HashMap<String, Float> FPS_SCALE = new HashMap<String, Float>() {

			private static final long serialVersionUID = -7381621390319389771L;

			{
				put("idle", 0.3f);
				put("walk", 0.3f);
				put("run", 0.5f);
				put("jump", 0.2f);
				put("attack", 0.5f);
				put("hurt", 0.5f);
				put("die", 0.5f);

			}
		}; // Animation - Frame Key Speed

		Resource playerResource = new Resource(mainDir, spritesDirname, FRAME_ROWS, FRAME_COLS, startKeys, endKeys,
				typeKeys, keysOrder, frameDuration, scale, FPS_SCALE);

		return playerResource;
	}

	// Player
	static public Resource getPlayerResources_2() {

		final String mainDir = "./sprite-sheets/player/2/";

		final String[][] spritesDirname = { { mainDir + "iwr.png", mainDir + "ja2jad.png" },
				{ mainDir + "iwr-left.png", mainDir + "ja2jad-left.png" } };

		final int[] FRAME_ROWS = { 1, 1 };
		final int[] FRAME_COLS = { 30, 40 };

		final int[][] startKeys = { { 0, 9, 19 }, { 0, 9, 19, 29 } };
		final int[][] endKeys = { { 9, 19, 29 }, { 9, 19, 29, 39 } };

		final String[][] typeKeys = { { "idle", "walk", "run" }, { "jump", "attack", "jump_attack", "die" } };

		final String[] keysOrder = { "right", "left" };

		final float frameDuration = 0.015f; // Animation - Frame Duration

		final float scale = 0.3f;

		final HashMap<String, Float> FPS_SCALE = new HashMap<String, Float>() {

			private static final long serialVersionUID = -7381621390319389771L;

			{
				put("idle", 0.4f);
				put("walk", 0.4f);
				put("run", 0.4f);
				put("jump", 0.4f);
				put("jump_attack", 0.4f);
				put("attack", 0.3f);
				put("die", 0.4f);

			}
		}; // Animation - Frame Key Speed

		Resource playerResource = new Resource(mainDir, spritesDirname, FRAME_ROWS, FRAME_COLS, startKeys, endKeys,
				typeKeys, keysOrder, frameDuration, scale, FPS_SCALE);

		return playerResource;
	}

	// Enemy
	public static Resource getDemonrResources() {

		final String mainDir = "./sprite-sheets/enemy/1/";

		final String[][] spritesDirname = { { mainDir + "demon.png" }, { mainDir + "demon-left.png" } };

		final int[] FRAME_ROWS = { 1 };
		final int[] FRAME_COLS = { 21 };

		final int[][] startKeys = { { 0, 3, 9, 13, 15 } };
		final int[][] endKeys = { { 2, 8, 12, 14, 20 } };

		final String[][] typeKeys = { { "idle", "walk", "attack", "hurt", "death" } };

		final String[] keysOrder = { "right", "left" };

		final float frameDuration = 0.045f;

		final float scale = 1.0f;

		final HashMap<String, Float> FPS_SCALE = new HashMap<String, Float>() {

			private static final long serialVersionUID = -1386435843423357563L;

			{
				put("idle", 0.1f);
				put("walk", 0.1f);
				put("attack", 0.1f);
				put("hurt", 0.1f);
				put("die", 0.1f);

			}
		}; // Animation - Frame Key Speed

		Resource demonResource = new Resource(mainDir, spritesDirname, FRAME_ROWS, FRAME_COLS, startKeys, endKeys,
				typeKeys, keysOrder, frameDuration, scale, FPS_SCALE);

		return demonResource;
	}
}
