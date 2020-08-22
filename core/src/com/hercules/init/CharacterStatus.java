package com.hercules.init;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.engine.ui.ConsoleLogger;

public class CharacterStatus {

	public static HashMap<String, CharacterStatus> statusRepo = new HashMap<>(); // characters status repo

	private final Vector2 startingPos; // relative - initial position, the real postion is actor position

	private Vector2 initialVelocity;

	private float runScale;
	private float jumpScale;
	private float smashingScale;

	// dir: int - sprite index, ex. index: dir = 0 - is right dir sprite, dir
	private int dir;
	private String currentMode;

	private float health;
	private boolean died;

	private boolean onGround;
	private boolean hit; // if attack = true: ...
	private boolean onBound;

	public CharacterStatus(Vector2 startingPos, Vector2 initialVelocity, float runScale, float jumpScale,
			float smashingScale, int dir, String currentMode, float health, boolean died) {

		this.startingPos = startingPos;

		this.setInitialVelocity(initialVelocity);

		this.setRunScale(runScale);
		this.setJumpScale(jumpScale);
		this.setSmashingScale(smashingScale);

		this.setDir(dir);
		this.setCurrentMode(currentMode);

		this.setHealth(health);
		this.setDied(died);

		this.setOnGround(false);
		this.setOnBound(false);
		this.setHit(false);

	}

	public CharacterStatus(Vector2 startingPos, Vector2 initialVelocity, Vector3 movementScale, int dir,
			String currentMode, float health, boolean died) {

		this.startingPos = startingPos;

		this.setInitialVelocity(initialVelocity);

		this.setRunScale(movementScale.x);
		this.setJumpScale(movementScale.y);
		this.setSmashingScale(movementScale.z);

		this.setDir(dir);
		this.setCurrentMode(currentMode);

		this.setHealth(health);
		this.setDied(died);

		this.setOnGround(false);
		this.setOnBound(false);
		this.setHit(false);
	}

	public CharacterStatus(float startingPosX, float startingPosY, float velocityX, float velocityY, float runScale,
			float jumpScale, float smashingScale, int dir, String currentMode, float health, boolean died) {

		this.startingPos = new Vector2(startingPosX, startingPosY);

		this.setInitialVelocity(new Vector2(velocityX, velocityY));

		this.setRunScale(runScale);
		this.setJumpScale(jumpScale);
		this.setSmashingScale(smashingScale);

		this.setDir(dir);
		this.setCurrentMode(currentMode);

		this.setHealth(health);
		this.setDied(died);

		this.setOnGround(false);
		this.setOnBound(false);
		this.setHit(false);
	}

	public CharacterStatus(float startingPosX, float startingPosY, float velocityX, float runScale, float jumpScale,
			float smashingScale, int dir, String currentMode, float health, boolean died) {

		this.startingPos = new Vector2(startingPosX, startingPosY);

		this.setInitialVelocity(new Vector2(velocityX, 0.0f));

		this.setRunScale(runScale);
		this.setJumpScale(jumpScale);
		this.setSmashingScale(smashingScale);

		this.setDir(dir);
		this.setCurrentMode(currentMode);

		this.setHealth(health);
		this.setDied(died);

		this.setOnGround(false);
		this.setOnBound(false);
		this.setHit(false);
	}

	/**
	 * @return the startingPos
	 */
	public Vector2 getStartingPos() {
		return startingPos;
	}

	/**
	 * @return the startingPos on X
	 */
	public float getStartingPosX() {
		return startingPos.x;
	}

	/**
	 * @return the startingPos on Y
	 */
	public float getStartingPosY() {
		return startingPos.y;
	}

	/**
	 * @return the initialVelocity
	 */
	public Vector2 getInitialVelocity() {
		return initialVelocity;
	}

	/**
	 * @return the initialVelocity
	 */
	public float getInitialVelocityX() {
		return initialVelocity.x;
	}

	/**
	 * @return the initialVelocity
	 */
	public float getInitialVelocityY() {
		return initialVelocity.y;
	}

	/**
	 * @param initialVelocity the initialVelocity to set
	 */
	public void setInitialVelocity(Vector2 initialVelocity) {
		this.initialVelocity = initialVelocity;
	}

	/**
	 * @return the dir, if return 0; dir == 1, else if (dir == -1) return 1,
	 *         otherwise return -2, and set warning
	 */
	public int getDir() {

		if (this.dir == 1) {

			return 0;

		} else if (this.dir == -1) {
			return 1;
		}

		else {

			ConsoleLogger.setWarning(CharacterStatus.class.getName(),
					"Invaild Direction: dir = " + Integer.toString(this.dir));

			return -2;
		}
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}

	/**
	 * @return the currentMode
	 */
	public String getCurrentMode() {
		return currentMode;
	}

	/**
	 * @param currentMode the currentMode to set
	 */
	public void setCurrentMode(String currentMode) {
		this.currentMode = currentMode;
	}

	/**
	 * @return the health
	 */
	public float getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(float health) {
		this.health = health;
	}

	/**
	 * @return the died
	 */
	public boolean isDied() {
		return died;
	}

	/**
	 * @param died the died to set
	 */
	public void setDied(boolean died) {
		this.died = died;
	}

	/**
	 * @return the runScale
	 */
	public float getRunScale() {
		return runScale;
	}

	/**
	 * @param runScale the runScale to set
	 */
	public void setRunScale(float runScale) {
		this.runScale = runScale;
	}

	/**
	 * @return the jumpScale
	 */
	public float getJumpScale() {
		return jumpScale;
	}

	/**
	 * @param jumpScale the jumpScale to set
	 */
	public void setJumpScale(float jumpScale) {
		this.jumpScale = jumpScale;
	}

	/**
	 * @return the smashingScale
	 */
	public float getSmashingScale() {
		return smashingScale;
	}

	/**
	 * @param smashingScale the smashingScale to set
	 */
	public void setSmashingScale(float smashingScale) {
		this.smashingScale = smashingScale;
	}

	/**
	 * @return the onGround
	 */
	public boolean isOnGround() {
		return onGround;
	}

	/**
	 * @param onGround the onGround to set
	 */
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @param hit the hit to set
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	/**
	 * @return the onBound
	 */
	public boolean isOnBound() {
		return onBound;
	}

	/**
	 * @param onBound the onBound to set
	 */
	public void setOnBound(boolean onBound) {
		this.onBound = onBound;
	}

}
