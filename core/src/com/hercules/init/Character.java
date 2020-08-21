/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.util.HashMap;

import com.badlogic.gdx.physics.box2d.Body;
import com.engine.animation.AnimationGenerator;
import com.engine.exception.InconsistentSpriteSheetException;
import com.engine.exception.OverwriteException;
import com.engine.world.Camera2D;
import com.engine.world.World2D;
import com.hercules.game.GameAdapter;

public abstract class Character {

	private final String[][] spritesDirname;

	protected final float frameDuration;
	protected final HashMap<String, Float> FPS_SCALE;

	protected String name;

	protected float posX;
	protected float posY;
	protected float speed;

	// index: int - sprite index, ex. index: dir = 0 - is right dir sprite, dir
	protected int index;
	protected String currentMode;

	protected float health;
	protected boolean died;

	protected AnimationGenerator[] animator;

	static public Camera2D camera = new Camera2D(GameAdapter.V_WIDTH, GameAdapter.V_HEIGHT);

	public float runScale;
	public float jumpScale;
	public float smashingScale;

	public Body actor;

	public Body weaponMaskR;
	public Body weaponMaskL;

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it -
	 *                        key: {@link #index}
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
	public Character(String[][] spritesDirname, String name, float posX, float posY, float speed, float runScale,
			float jumpScale, float smashingScale, HashMap<String, Float> FPS_SCALE, float frameDuration,
			int defaultIndex, String currentMode, boolean scale) {

		this.spritesDirname = spritesDirname;
		this.name = name;

		this.posX = scale ? posX / World2D.GU : posX;
		this.posY = scale ? posY / World2D.GU : posY;
		this.speed = scale ? speed / World2D.GU : speed;

		this.index = defaultIndex;
		this.currentMode = currentMode;

		this.health = 100.0f;
		this.died = false;

		this.FPS_SCALE = FPS_SCALE;
		this.frameDuration = frameDuration;

		this.runScale = runScale;
		this.jumpScale = jumpScale;
		this.smashingScale = smashingScale;

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
	protected void initCharacter(int[] FRAME_ROWS, int[] FRAME_COLS, int[][] startKeys, int[][] endKeys,
			String[][] typeKeys, String[] keysOrder) {

		this.animator = new AnimationGenerator[keysOrder.length]; // ex. 2: 0 - right, 1-left

		for (int i = 0; i < keysOrder.length; i++) {

			animator[i] = new AnimationGenerator(this.spritesDirname[i]);

			for (int j = 0; j < this.spritesDirname[i].length; j++) {

				try {

					animator[i].addAnimation(j, FRAME_ROWS[j], FRAME_COLS[j], this.frameDuration, startKeys[j],
							endKeys[j], typeKeys[j], keysOrder[i]);

				} catch (OverwriteException | InconsistentSpriteSheetException error) {

					error.printStackTrace();
				}
			}

		}

		Character.camera.position.x = camera.viewportWidth / 2;
		Character.camera.position.y = camera.viewportHeight / 2;

		Character.camera.update();
	}

	public abstract void initActor(World2D world);

	/**
	 * Animate - character
	 */
	abstract public void animate();

	/**
	 * Movements, States
	 * 
	 * @param speedScale     : float - Player speed scale
	 * @param deltaTimeScale : boolean - If true, scale character's movements, by
	 *                       delta time.
	 */
	abstract public void update(boolean deltaTimeScale);

	public void dispose() {

		for (int i = 0; i < this.animator.length; i++) {
			this.animator[i].dispose();
		}
	}

	public float getPosX() {
		return this.actor.getPosition().x;
	}

	public float getPosY() {
		return this.actor.getPosition().y;
	}

	public float getTileWidth() {
		return this.animator[this.index].getTileWidth();
	}

	public float getTileHeight() {
		return this.animator[this.index].getTileHeight();
	}
}
