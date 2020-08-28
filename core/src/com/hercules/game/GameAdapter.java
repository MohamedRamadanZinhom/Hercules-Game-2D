/** @author Z. Mohamed Osama */

package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 1200; // Viewport - X
	public static final int V_HEIGHT = 600; // Viewport - Y
	public static final boolean FULLSCREEN = false;

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler

	public static final float gravityX = 0.0f;
	public static final float gravityY = -9.81f;

	public static GameState gameState;

	@Override
	public void create() {

		gameState = new GameState();

	}

	@Override
	public void render() {

		gameState.render();
	}

	@Override
	public void dispose() {
		gameState.dispose();
	}

}
