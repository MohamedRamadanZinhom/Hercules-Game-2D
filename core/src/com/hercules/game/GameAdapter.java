/** @author Z. Mohamed Osama */

package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

import com.hercules.init.CharacterStatus;
import com.hercules.init.Player;

public class GameAdapter extends ApplicationAdapter {

	public static final String TITEL = "Hercules"; // Screen Title

	public static final int V_WIDTH = 1980; // Viewport - X
	public static final int V_HEIGHT = 600; // Viewport - Y
	public static final boolean FULLSCREEN = false;

	public static final float GU = 100.0f; // Box2D Game-Unit Scaler

	public static final float gravityX = 0.0f;
	public static final float gravityY = -9.81f;

	// ============ World
	GameLevel level;

	int[] levels = { 0 };

	// ============ Player
	Player player;

	public static final String playerName = "m-zayan";

	public static float posX = 150.0f;
	public static float posY = 115.0f;

	public static float speed = 4.0f;
	public static final float runScale = 5.0f;
	public static final float jumpScale = 200.0f;
	public static final float smashingScale = 15.0f;

	@Override
	public void create() {

		level = new GameLevel(levels[0]);

		// Player
		CharacterStatus playerStatus = new CharacterStatus(posX, posY, speed, runScale, jumpScale, smashingScale, 1,
				"idle", 100.0f, false);

		player = new Player(playerName, playerStatus);
		player.initActor(level.world2d);

	}

	@Override
	public void render() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// Input & Update (Character & World)
		level.world2d.updateDebugInfo(1000L, false, true); // // Physics simulation update debugger debug Info
		player.update(true);

		// World Render
		level.render();

		// Character - animate
		player.animate(0.0f, 40.0f);

	}

	@Override
	public void dispose() {

		level.dispose();

	}

}
