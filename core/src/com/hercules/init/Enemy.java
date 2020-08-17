package com.hercules.init;

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
	public Enemy(String[][] spritesDirname, String name, float posX, float posY, float speed, float FPS_SCALE,
			float frameDuration, int defaultIndex, String currentMode) {

		super(spritesDirname, name, posX, posY, speed, FPS_SCALE, frameDuration, defaultIndex, currentMode);
	}

	@Override
	public void animate() {
		// do something

	}

	public void attack() {
		// do something
	}
}
