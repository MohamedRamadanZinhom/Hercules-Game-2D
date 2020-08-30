package com.hercules.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProgressBar {

	private SpriteBatch batch;
	private Texture image;

	private float value;

	public ProgressBar(Texture img) {

		this.batch = new SpriteBatch();
		this.image = img;

		this.value = 1.0f;

	}

	public void render(float x, float y, float width, float height) {

		if (getValue() > 0.6) {
			setBatchColor(Color.valueOf("#2DF312"));
		} else if (getValue() < 0.6 && getValue() > 0.1) {
			setBatchColor(Color.valueOf("#FAF707"));
		} else if (getValue() < 0.1 && getValue() > -0.5) {
			setBatchColor(Color.valueOf("#F31912"));
		}

		batch.begin();
		batch.draw(this.image, x, y, width, height);
		batch.end();

	}

	public void setValue(float value) {
		if (value >= -0.5)
			this.value = value;
	}

	public float getValue() {
		return this.value;
	}

	public void setBatchColor(Color color) {
		batch.setColor(color);
	}

}
