package com.hercules.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends Game {

	public SpriteBatch batch;

	public GameState() {

		this.create();
	}

	@Override
	public void create() {

		batch = new SpriteBatch();
		super.setScreen(new GameMenu(this));

	}

	@Override
	public void dispose() {

		batch.dispose();
		super.dispose();
	}

	public void setState(Screen screen) {

		this.setScreen(this.screen);
	}

}
