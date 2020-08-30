package com.hercules.init;

import java.util.HashMap;

public final class Resource {

	public final String mainDir;

	public final String[][] spritesDirname;

	public final int[] FRAME_ROWS;
	public final int[] FRAME_COLS;

	/**
	 * Use the same keys: pass reversed key order parameter - {@link #keysOrder)}
	 */
	public final int[][] startKeys;
	public final int[][] endKeys;

	public final String[][] typeKeys;

	public final String[] keysOrder;

	public final float frameDuration;

	public final float scale;
	public final HashMap<String, Float> FPS_SCALE; // Animation - Frame Key Speed

	public Resource(String mainDir, String[][] spritesDirname, int[] FRAME_ROWS, int[] FRAME_COLS, int[][] startKeys,
			int[][] endKeys, String[][] typeKeys, String[] keysOrder, float frameDuration, float scale,
			HashMap<String, Float> FPS_SCALE) {

		this.mainDir = mainDir;
		this.spritesDirname = spritesDirname;

		this.FRAME_ROWS = FRAME_ROWS;
		this.FRAME_COLS = FRAME_COLS;

		this.startKeys = startKeys;
		this.endKeys = endKeys;
		this.typeKeys = typeKeys;
		this.keysOrder = keysOrder;
		this.frameDuration = frameDuration;

		this.scale = scale;
		this.FPS_SCALE = FPS_SCALE;

	}

}
