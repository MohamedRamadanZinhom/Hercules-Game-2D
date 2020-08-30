/** @author Z. Mohamed Osama */

package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 1200; // Viewport - X
	public static final int V_HEIGHT = 600; // Viewport - Y
	public static final boolean FULLSCREEN = true;

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler

	public static final float gravityX = 0.0f;
	public static final float gravityY = -9.81f;

	public static GameState state = new GameState();

	@Override
	public void create() {

		state.create();
	}

	@Override
	public void render() {

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		if (Gdx.input.isKeyJustPressed(Keys.R)) {

			GameAdapter.state.updateState("menu-screen");
		}

		state.render();
	}

	@Override
	public void dispose() {

		state.dispose();

	}

}
