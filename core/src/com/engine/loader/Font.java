package com.engine.loader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Font extends BitmapFont {

	private SpriteBatch batch;

	public Font() {
		super();

		this.batch = new SpriteBatch();
	}

	public Font(FileHandle fontFile) {
		super(fontFile);
	}

	public Font(FileHandle fontFile, TextureRegion region) {
		super(fontFile, region);
	}

	public Font(FileHandle fontFile, TextureRegion region, boolean flip) {
		super(fontFile, region, flip);
	}

	public void render(String text, Color color, float x, float y) {

		batch.begin();

		this.setColor(color);
		this.draw(batch, text, x, y);
		batch.end();
	}

	public void render(String text, float x, float y) {

		batch.begin();
		this.draw(batch, text, x, y);
		batch.end();
	}

	public void disposeFont() {

		batch.dispose();
		this.dispose();
	}
}
