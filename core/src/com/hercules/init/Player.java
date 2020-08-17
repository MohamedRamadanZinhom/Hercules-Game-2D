package com.hercules.init;

import com.engine.animation.AnimationGenerator;
import com.engine.exception.OverwriteException;

public class Player {

	private final String name;

	private String[] path = { "./test-assets/walk.png" };

	private final int[] FRAME_COLS = { 10 };
	private final int[] FRAME_ROWS = { 1 };

	private final int[][] startKeys = { { 0 } };
	private final int[][] endKeys = { { 10 } };

	private final String[][] typeKeys = { { "walk" } };

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
	 * @param posX:          float - Player initial position - X
	 * @param posY:          float - Player initial position - Y
	 * @param speed:         float - Player initial Speed
	 * @param FPS_SCALE:     float - SCALE Animtion Frames
	 * @param frameDuration: float
	 */
	public Player(String name, float posX, float posY, float speed, float FPS_SCALE, float frameDuration) {

		this.name = name;

		this.posX = posX;
		this.posY = posY;
		this.speed = speed;

		this.index = 0;
		this.currentMode = "idle";

		this.health = 100.0f;
		this.died = false;

		this.FPS_SCALE = FPS_SCALE;
		this.frameDuration = frameDuration;

		animator = new AnimationGenerator[1];

		animator[0] = new AnimationGenerator(path);
	}

	public void initPlayer() {

		try {
			animator[0].addAnimation(0, FRAME_ROWS[0], FRAME_COLS[0], this.frameDuration, startKeys[0], endKeys[0],
					typeKeys[0], "right");

		} catch (OverwriteException error) {
			error.printStackTrace();
		}
	}

	public void animate() {
		animator[this.index].animate(this.currentMode, this.posX, this.posY, this.FPS_SCALE);
	}

	/**
	 * @return the name, Leaderboard
	 */
	public String getName() {
		return name;
	}
}
