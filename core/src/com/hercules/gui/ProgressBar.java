package com.hercules.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hercules.game.GameState;

public class ProgressBar {

	private float Value;
	private Texture image;
	private final GameState game;

	public ProgressBar(Texture img, GameState game) {

		this.image = img;
		this.Value = 1;
		this.game = game;
	}

	public void render(float x, float y, float width, float height) {

		if (getValue() > 0.6) {
			setBatchColor(Color.valueOf("#2DF312"));
		} else if (getValue() < 0.6 && getValue() > 0.1) {
			setBatchColor(Color.valueOf("#FAF707"));
		} else if (getValue() < 0.1 && getValue() > -0.5) {
			setBatchColor(Color.valueOf("#F31912"));
		}

		this.game.batch.begin();
		this.game.batch.draw(this.image, x, y, width, height);
		this.game.batch.end();

	}

	public void setValue(float value) {
		if (value >= -0.5)
			this.Value = value;
	}

	public float getValue() {
		return this.Value;
	}

	public SpriteBatch getGameBatch() {
		return this.game.batch;
	}

	public void setBatchColor(Color color) {
		this.game.batch.setColor(color);
	}

}
