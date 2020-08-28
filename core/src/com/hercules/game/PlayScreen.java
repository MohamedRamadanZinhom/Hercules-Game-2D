package com.hercules.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.engine.world.World2D;
import com.hercules.gui.ProgressBar;
import com.hercules.gui.UIResources;
import com.hercules.init.CharacterStatus;
import com.hercules.init.Demon;
import com.hercules.init.Player;

public class PlayScreen implements Screen {

	// GameState
	public GameState state;

	// ============ World
	public GameLevel level;

	int[] levels = { 0 };

	// ============ Player
	public Player player;

	public static final String playerName = "m-zayan";

	public static float posX = 150.0f;
	public static float posY = 115.0f;

	public static float speed = 2.0f;
	public static final float runScale = 3.0f;
	public static final float jumpScale = 200.0f;
	public static final float smashingScale = 5.0f;

	// ============ Demon - Enemy

	public Demon demon;

	public static float posXD = 1600.0f;
	public static float posYD = 100.0f;

	public static float speedD = 1.0f;
	public static final float runScaleD = 2.0f;
	public static final float jumpScaleD = 200.0f;
	public static final float smashingScaleD = 6.0f;

	public static final float forthStep = 200.0f;
	public static final float backStep = 200.0f;

	public static final float attackDistance = 190.0f;

	public static ProgressBar healthBar;

	public PlayScreen(GameState game) {

		level = new GameLevel(levels[0]);

		// Player
		CharacterStatus playerStatus = new CharacterStatus(posX, posY, speed, runScale, jumpScale, smashingScale, 1,
				"idle", 100.0f, false);

		player = new Player(playerName, playerStatus);
		player.initActor(level.world2d);

		// Demon - Enemy
		CharacterStatus demonStatus = new CharacterStatus(posXD, posYD, speedD, runScaleD, jumpScaleD, smashingScaleD,
				1, "idle", 100.0f, false);

		demon = new Demon(demonStatus, forthStep, backStep);
		demon.initActor(level.world2d);

		this.state = game;

		healthBar = new ProgressBar(new Texture(UIResources.pathUI + "blank.png"), this.state);

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		else if (player.isDied()) {
			Gdx.app.exit();
		}

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// Input & Update (Character & World)
		level.world2d.updateDebugInfo(600L, false, true, true); // // Physics simulation update debugger debug Info

		// Player
		player.update(true);

		// Enemy
		demon.wait(player, attackDistance);
		demon.update(true);

		// World Render
		level.render();

		// Character - animate
		player.animate(65.0f, 65.0f);
		demon.animate(50.0f, 10.0f);

		// UI - healthBar
		healthBar.render(World2D.SCREEN_WIDTH / 2 - 256.0f, World2D.SCREEN_HEIGHT - 20, 500.0f * healthBar.getValue(),
				20);

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
		demon.dispose();
		level.dispose();
	}

}
