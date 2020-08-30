package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.engine.world.World2D;
import com.hercules.game.GameAdapter;

public class GameMenu implements Screen {

	private final MenuButton playButton;
	private final MenuButton optionButton;
	private final MenuButton exitButton;

	private Stage stage;
	private SpriteBatch batch;

	public GameMenu() {

		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());

		// Play Button
		playButton = new MenuButton("Play", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2);
		playButton.getButton().setColor(Color.GREEN);

		// Option Button
		optionButton = new MenuButton("Option", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2 - 75);
		optionButton.getButton().setColor(Color.GREEN);

		// Exit Button
		exitButton = new MenuButton("Exit", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2 - 150);
		exitButton.getButton().setColor(Color.GREEN);

		UIResources.sound.play(100.0f);

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		GameAdapter.state.drawBackGround(batch);
		menuInputUpdate(batch); // animate buttons
		batch.end();

		stage.draw();
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
		stage.dispose();
	}

	public final void menuInputUpdate(Batch batch) {

		if (playButton.isOver(0.0f, -50.0f)) {

			GameAdapter.state.drawTexture(batch, UIResources.PlayButton_Active, UIResources.play_button_X - 20,
					UIResources.play_button_Y, UIResources.play_button_width, UIResources.play_button_height);

			if (MenuButton.isLeftClick()) {

				GameAdapter.state.updateState("play-screen");
			}

		} else {

			GameAdapter.state.drawTexture(batch, UIResources.PlayButton_UnActive, UIResources.play_button_X - 20,
					UIResources.play_button_Y, UIResources.play_button_width, UIResources.play_button_height);

		}

		// ---------------------------------------------

		if (optionButton.isOver(0.0f, 100.0f)) {

			GameAdapter.state.drawTexture(batch, UIResources.SettingButton_Active, UIResources.Setting_button_X - 20,
					UIResources.Setting_button_Y, UIResources.Setting_button_width, UIResources.Setting_button_height);

			if (MenuButton.isLeftClick()) {

				GameAdapter.state.updateState("option-screen");
			}

		} else {

			GameAdapter.state.drawTexture(batch, UIResources.SettingButton_UnActive, UIResources.Setting_button_X - 20,
					UIResources.Setting_button_Y, UIResources.Setting_button_width, UIResources.Setting_button_height);

		}

		// ----------------------------------

		if (exitButton.isOver(0.0f, 250.0f)) {

			GameAdapter.state.drawTexture(batch, UIResources.Exit_Activated, UIResources.Exit_button_X - 20,
					UIResources.Exit_button_Y, UIResources.Exit_button_width, UIResources.Exit_button_height);

			if (MenuButton.isLeftClick()) {

				Gdx.app.exit();
			}

		} else {

			GameAdapter.state.drawTexture(batch, UIResources.Exit_UnActivated, UIResources.Exit_button_X - 20,
					UIResources.Exit_button_Y, UIResources.Exit_button_width, UIResources.Exit_button_height);

		}

	}

}
