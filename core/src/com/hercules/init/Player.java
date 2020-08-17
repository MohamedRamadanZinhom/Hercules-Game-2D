/** @author Z. Mohamed Osama */

package com.hercules.init;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

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

	public void initPlayer(int[] FRAME_ROWS, int[] FRAME_COLS, int[][] startKeys, int[][] endKeys, String[][] typeKeys,
			String[] keysOrder) {
		this.initCharacter(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);
	}

	@Override
	public void animate() {

		this.animator[this.index].animate(this.currentMode, this.posX, this.posY, this.FPS_SCALE);

		this.currentMode = "idle";
		this.index = 0;
	}

	/**
	 * @param speedScale     : float - Player speed scale
	 * @param deltaTimeScale : boolean - If true, scale character's movements, by
	 *                       delta time.
	 */
	public void handleInput(float speedScale, boolean deltaTimeScale) {

		float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() : 0.0f;
		float speed = this.speed * scale * speedScale;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.posX += speed;

			this.currentMode = "walk";
			this.index = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			this.posX -= speed;

			this.currentMode = "walk";
			this.index = 1;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			// Jumping
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			// Smashing
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			// Attack

			this.currentMode = "attack";
		}

		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
			// Speed Up (Run)
		}

		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			// Spying
		}
	}

}
