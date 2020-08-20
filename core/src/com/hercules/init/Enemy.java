/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.security.KeyException;
import java.util.HashMap;

public class Enemy extends Character {

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param name:           String - Enemy Id
	 * @param posX:           float - Enemy initial position - X
	 * @param posY:           float - Enemy initial position - Y
	 * @param speed:          float - Enemy initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultIndex    : int - Initial sprite sheet index (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Enemy(String[][] spritesDirname, String name, float posX, float posY, float speed,
			HashMap<String, Float> FPS_SCALE, float frameDuration, int defaultIndex, String currentMode) {

		super(spritesDirname, name, posX, posY, speed, FPS_SCALE, frameDuration, defaultIndex, currentMode, false);
	}

	public void initEnemy(int[] FRAME_ROWS, int[] FRAME_COLS, int[][] startKeys, int[][] endKeys, String[][] typeKeys,
			String[] keysOrder) {
		this.initCharacter(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);
	}

	@Override
	public void animate() {

		try {

			this.animator[this.index].animate(this.currentMode, this.posX, this.posY, this.FPS_SCALE);

		} catch (KeyException error) {

			error.printStackTrace();
		}

		// do something

	}

	public void update(boolean deltaTimeScale) {
		// do something
	}
}
