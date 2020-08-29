package com.hercules.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

import com.hercules.init.CharacterStatus;
import com.hercules.init.Demon;
import com.hercules.init.Player;

public class PlayScreen implements Screen {

	int[] levels = { 0 };

	public static long endGameTime = -1L;

	// GameState
	public static GameState state;

	// ============ World
	public static GameLevel level;

	// ============ Player
	public static Player player;

	public static final String playerName = "m-zayan";

	public static float posX = 150.0f;
	public static float posY = 115.0f;

	public static float speed = 5.0f;
	public static final float runScale = 3.0f;
	public static final float jumpScale = 200.0f;
	public static final float smashingScale = 15.0f;

	public static final float damageScale = 4.0f;

	// ============ Demon - Enemy

	public static Demon[] demon;
	public static int n = 2;
	public static int op = 0; // current enemy

	public static float[] posXD = { 1600.0f, 2800.0f };
	public static float[] posYD = { 100.0f, 200.0f };

	public static float speedD = 1.0f;
	public static final float runScaleD = 2.0f;
	public static final float jumpScaleD = 200.0f;
	public static final float smashingScaleD = 6.0f;

	public static final float forthStep = 200.0f;
	public static final float backStep = 200.0f;

	public static final float attackDistance = 190.0f;

	public static final float damageScaleDemon = 2.0f;

	public PlayScreen(GameState game) {

		state = game;

		level = new GameLevel(levels[0]);

		// Player
		CharacterStatus playerStatus = new CharacterStatus(posX, posY, speed, runScale, jumpScale, smashingScale,
				damageScale, 1, "idle", 100.0f, false);

		player = new Player(playerName, playerStatus);
		player.initActor(level.world2d);

		// Demon - Enemy
		CharacterStatus[] demonStatus = new CharacterStatus[n];
		demon = new Demon[n];

		for (int i = 0; i < n; i++) {

			demonStatus[i] = new CharacterStatus(posXD[i], posYD[i], speedD, runScaleD, jumpScaleD, smashingScaleD,
					damageScaleDemon, 1, "idle", 100.0f, false);

			demon[i] = new Demon(demonStatus[i], forthStep, backStep);

			demon[i].initActor(level.world2d);

			if (i != op) {

				demon[i].deactivate();
			}
		}

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			// return to menu
		}

		if (!demon[op].isActivate() && op >= n) {

			// return to menu
		}

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// Input & Update (Character & World)
		level.world2d.updateDebugInfo(600L, false, true, true); // // Physics simulation update debugger debug Info

		// Player
		player.update(true);

		// Enemy
		demon[op].update(true);

		// World Render
		level.render();

		// Enemy state
		demon[op].wait(player, attackDistance);

		// Character - animate
		player.animate(90.0f, 70.0f);
		demon[op].animate(50.0f, 10.0f);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

		player.dispose();

		for (int i = 0; i < n; i++)
			demon[i].dispose();

		level.dispose();
	}

}
