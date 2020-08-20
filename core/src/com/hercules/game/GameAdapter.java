/** @author Z. Mohamed Osama */

package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.hercules.init.Enemy;
import com.hercules.init.Player;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 1200; // Viewport - X
	public static final int V_HEIGHT = 600; // Viewport - Y
	public static final boolean FULLSCREEN = true;

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler

	// ============ World
	GameLevel level;

	int[] levels = { 0 };

	// ============ Player
	Player player;
	Enemy demon;

	public static final String playerName = "m-zayan";

	public static float posX = 150.0f;
	public static float posY = 115.0f;

	public static float speed = 4.0f;
	public static final float runScale = 5.0f;
	public static final float jumpScale = 140.0f;
	public static final float smashingScale = 20.0f;
	// =============

	@Override
	public void create() {

		level = new GameLevel(levels[0], V_WIDTH, V_HEIGHT, GU);

		// Player
		player = new Player(playerName, posX, posY, speed, runScale, jumpScale, smashingScale);
		player.initPlayer(level.world2d);

		// Enemy
//		demon = new Enemy();

	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		// Input
		player.update(true);

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// World
		level.render();

		// Player
		player.animate();
	}

	@Override
	public void dispose() {

		level.dispose();
		player.dispose();
	}

}
