package com.hercules.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends Game {

	public SpriteBatch batch;
	// Texture img;

	public GameState() {

		this.create();
	}

	@Override
	public void create() {

		batch = new SpriteBatch();
		super.setScreen(new GameMenu(this)); // <<-- To set which screen will be shown -->> ?!

	}

	public void render() {

		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		super.dispose();
	}
}
