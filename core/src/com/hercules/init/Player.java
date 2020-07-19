package com.hercules.init;

import com.engine.animation.AnimationGenerator;

public class Player {

	private final String name;
	protected String spritePath;

	protected float posX;
	protected float posY;
	protected float health;
	protected boolean died;
	protected AnimationGenerator animator;

	public Player(String name, float posX, float posY) {

		this.name = name;

		this.posX = posX;
		this.posY = posY;
		this.health = 100.0f;
		this.died = false;
	}

	/**
	 * @return the name, Leaderboard
	 */
	public String getName() {
		return name;
	}
}
