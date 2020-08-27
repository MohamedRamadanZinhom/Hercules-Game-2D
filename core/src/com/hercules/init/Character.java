/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.util.HashMap;

import com.badlogic.gdx.physics.box2d.Body;
import com.engine.animation.AnimationGenerator;
import com.engine.exception.InconsistentSpriteSheetException;
import com.engine.exception.OverwriteException;
import com.engine.world.World2D;

public abstract class Character {

	public final String typeId;
	public final String weaponName;

	private final String[][] spritesDirname;

	protected final float frameDuration;
	protected final HashMap<String, Float> FPS_SCALE;

	protected CharacterStatus status;

	protected AnimationGenerator[] animator;

	public Body actor;

	public Body weaponMaskR;
	public Body weaponMaskL;

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it -
	 *                        key: {@link #index}
	 * 
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
	public Character(String typeId, String weaponName, String[][] spritesDirname, HashMap<String, Float> FPS_SCALE,
			float frameDuration, CharacterStatus status, boolean scaleByGU) {

		this.spritesDirname = spritesDirname;

		this.FPS_SCALE = FPS_SCALE;
		this.frameDuration = frameDuration;
		this.status = status;

		this.typeId = typeId;
		this.weaponName = weaponName;

		CharacterStatus.statusRepo.put(typeId, status);

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

			this.animator[i] = new AnimationGenerator(this.spritesDirname[i]);

			for (int j = 0; j < this.spritesDirname[i].length; j++) {

				try {

					this.animator[i].addAnimation(j, FRAME_ROWS[j], FRAME_COLS[j], this.frameDuration, startKeys[j],
							endKeys[j], typeKeys[j], keysOrder[i]);

				} catch (OverwriteException | InconsistentSpriteSheetException error) {

					error.printStackTrace();
				}
			}

		}
		;
	}

	public abstract void initActor(World2D world);

	/**
	 * Animate - character
	 * 
	 * @param thresholdX : float - tuning x position, the relative position between
	 *                   actor and sprite sprite_position_x = posX + thresholdX;
	 * 
	 * @param thresholdY : float - tuning y position, the relative position between
	 *                   actor and sprite sprite_position_y = posY + thresholdY;
	 * 
	 */
	public abstract void animate(float thresholdX, float thresholdY);

	/**
	 * Movements, Status
	 * 
	 * @param speedScale     : float - Player speed scale
	 * @param deltaTimeScale : boolean - If true, scale character's movements, by
	 *                       delta time.
	 */

	public abstract void update(boolean deltaTimeScale);

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
		return this.animator[this.status.isDirRight()].getTileWidth();
	}

	public float getTileHeight() {
		return this.animator[this.status.isDirRight()].getTileHeight();
	}

	/**
	 * @return the weaponName
	 */
	public String getWeaponId() {
		return this.typeId + "-" + this.weaponName;
	}
	
	public boolean isDied() {
		return status.isDied();
	}

}
