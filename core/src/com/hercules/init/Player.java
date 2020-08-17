package com.hercules.init;

public class Player extends Character {

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param name:           String - Player Id
	 * @param posX:           float - Player initial position - X
	 * @param posY:           float - Player initial position - Y
	 * @param speed:          float - Player initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultIndex    : int - Initial sprite sheet index (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Player(String[][] spritesDirname, String name, float posX, float posY, float speed, float FPS_SCALE,
			float frameDuration, int defaultIndex, String currentMode) {

		super(spritesDirname, name, posX, posY, speed, FPS_SCALE, frameDuration, defaultIndex, currentMode);
	}

	@Override
	public void animate() {
		// do something

	}

	/**
	 * @param speedScale     : float - Player speed scale
	 * @param deltaTimeScale : boolean - If true, scale character's movements, by
	 *                       delta time.
	 */
	public void handleInput(float speedScale, boolean deltaTimeScale) {
		// do something
	}

}
