/** @author Z. Mohamed Osama */

package com.hercules.init;

import com.engine.animation.AnimationGenerator;
import com.engine.exception.OverwriteException;

public abstract class Character {

	private final float frameDuration;

	protected String name;

	protected final float FPS_SCALE;

	protected float posX;
	protected float posY;
	protected float speed;

	// index: int - sprite index, ex. index: dir = 0 - is right dir sprite, dir
	protected int index;
	protected String currentMode;

	protected float health;
	protected boolean died;

	protected AnimationGenerator[] animator;

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param name:           String - Character Id
	 * @param posX:           float - Character initial position - X
	 * @param posY:           float - Character initial position - Y
	 * @param speed:          float - Character initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultIndex    : int - Initial sprite sheet index (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Character(String[][] spritesDirname, String name, float posX, float posY, float speed, float FPS_SCALE,
			float frameDuration, int defaultIndex, String currentMode) {

		this.name = name;

		this.posX = posX;
		this.posY = posY;
		this.speed = speed;

		this.index = defaultIndex;
		this.currentMode = currentMode;

		this.health = 100.0f;
		this.died = false;

		this.FPS_SCALE = FPS_SCALE;
		this.frameDuration = frameDuration;

		animator = new AnimationGenerator[spritesDirname.length]; // ex. 2: 0 - right, 1-left

		for (int i = 0; i < spritesDirname.length; i++) {
			animator[i] = new AnimationGenerator(spritesDirname[i]);
		}

	}

	/**
	 * @param FRAME_ROWS : 1d array specify FRAME_ROWS, for each sprite.
	 * @param FRAME_COLS : 1d array specify FRAME_COLS, for each sprite.
	 * 
	 * @param startKeys  : 2d array specify start keys for all animation for each
	 *                   sprite.
	 * 
	 * @param endKeys    : 2d array specify end keys for all animation for each
	 *                   sprite.
	 * 
	 * @param typeKeys   : 2d array specify animation type ("walk", run", ..etc.)
	 *                   from a specific starting key to an end key or each sprite.
	 * 
	 * @param keysOrder  : 1d array ("right" or "left" index order).
	 */
	public void initCharacter(int[] FRAME_ROWS, int[] FRAME_COLS, int[][] startKeys, int[][] endKeys,
			String[][] typeKeys, String[] keysOrder) {

		try {

			for (int i = 0; i < animator.length; i++) {

				animator[i].addAnimation(i, FRAME_ROWS[i], FRAME_COLS[i], this.frameDuration, startKeys[i], endKeys[i],
						typeKeys[i], keysOrder[i]);
			}

		} catch (OverwriteException error) {
			error.printStackTrace();
		}
	}

	/**
	 * Animate - character
	 */
	abstract public void animate();

}
