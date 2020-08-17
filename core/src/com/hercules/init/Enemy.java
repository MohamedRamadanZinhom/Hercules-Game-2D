package com.hercules.init;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.animation.AnimationGenerator;
import com.engine.exception.OverwriteException;

public class Enemy {

	private String[][] demon_path = { { "./sprite-sheets/demon.png" }, { "./sprite-sheets/demon-left.png" } };

	private final int[] FRAME_COLS = { 21 };
	private final int[] FRAME_ROWS = { 1 };

	private final int[][] startKeys = { { 0, 2, 8, 12, 14 } };
	private final int[][] endKeys = { { 2, 8, 12, 14, 20 } };

	private final String[][] typeKeys = { { "idle", "walk", "attack", "hurt", "die" } };

	private final float frameDuration;
	private final float FPS_SCALE;

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
	 * @param posX:          float - Enemy initial position - X
	 * @param posY:          float - Enemy initial position - Y
	 * @param speed:         float - Enemy initial Speed
	 * @param FPS_SCALE:     float - SCALE Animtion Frames
	 * @param frameDuration: float
	 */

	public Enemy(String name, float posX, float posY, float speed, float frameDuration, float FPS_SCALE) {

		this.posX = posX;
		this.posY = posY;
		this.speed = speed;

		this.index = 0;
		this.currentMode = "idle";

		this.health = 100.0f;
		this.died = false;

		this.FPS_SCALE = FPS_SCALE;
		this.frameDuration = frameDuration;

		animator = new AnimationGenerator[2]; // 0 - right, 1-left

		animator[0] = new AnimationGenerator(demon_path[0]);
		animator[1] = new AnimationGenerator(demon_path[1]);
	}

	public void initEnemy() {

		try {
			animator[0].addAnimation(0, FRAME_ROWS[0], FRAME_COLS[0], this.frameDuration, startKeys[0], endKeys[0],
					typeKeys[0], "right");

			animator[1].addAnimation(0, FRAME_ROWS[0], FRAME_COLS[0], this.frameDuration, startKeys[0], endKeys[0],
					typeKeys[0], "left");

		} catch (OverwriteException error) {
			error.printStackTrace();
		}
	}

	public void animate() {

		animator[this.index].animate(this.currentMode, this.posX, this.posY, this.FPS_SCALE);

		this.currentMode = "idle";
	}

	public void handleInput(float speedScale, boolean deltaTimeScale, OrthographicCamera envCam) {

		float deltaTime = deltaTimeScale ? Gdx.graphics.getDeltaTime() : 1.0f;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			this.posX += speedScale * this.speed * deltaTime;
			envCam.position.x += speedScale * this.speed * deltaTime;
			
			this.index = 0;

			this.currentMode = "walk";
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			this.posX -= speedScale * this.speed * deltaTime;
			
			envCam.position.x -= speedScale * this.speed * deltaTime;
			
			this.index = 1;

			this.currentMode = "walk";
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			this.currentMode = "attack";
		}
	}

}
