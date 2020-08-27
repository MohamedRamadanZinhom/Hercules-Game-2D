package com.hercules.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.engine.world.World2D;
import com.hercules.gui.MenuButtons;
import com.hercules.gui.OptionMenu;
import com.hercules.gui.UIResources;

public class GameMenu implements Screen {

	private final GameState state;

	private final MenuButtons playButton;
	private final MenuButtons optionButton;
	private final MenuButtons exitButton;
	private final Stage stage;

	public GameMenu(GameState gState) {

		state = gState;
		stage = new Stage(new ScreenViewport());

		// <Play Button--->
		playButton = new MenuButtons("Play", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2);
		playButton.getButton().setColor(Color.GREEN);

		playButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				state.setScreen(new PlayScreen(state));
			}

		});

		// <--OptionMenu-->

		optionButton = new MenuButtons("Option", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2 - 75);
		optionButton.getButton().setColor(Color.GREEN);

		optionButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				state.setScreen(new OptionMenu(state));

			}

		});

		// <<--Exit Button-->>

		exitButton = new MenuButtons("Exit", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				World2D.SCREEN_WIDTH / 2 - 100.0f, World2D.SCREEN_HEIGHT / 2 - 150);
		exitButton.getButton().setColor(Color.GREEN);

		exitButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();

			}

		});

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			Gdx.app.exit();
		}

		Gdx.gl.glClearColor(1, 100, 200, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		state.batch.begin();

		state.batch.draw(UIResources.MenuBackGround, 0, 0); // for the backGround image of Screen

		Button_Animation();

		UIResources.sound.Music_Play(UIResources.Volume);

		stage.act();
		stage.draw();

		state.batch.end();

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
		state.dispose();
		stage.dispose();
	}

	public final void DrawImage(Texture img, float x, float y, float width, float height) {
		state.batch.draw(img, x, y, width, height);
	}

	public final void Button_Animation() {

		if ((Gdx.input.getX() < UIResources.play_button_X + UIResources.play_button_width
				+ playButton.getButton().getWidth() && Gdx.input.getX() > UIResources.play_button_X)
				&& (Gdx.input.getY() < UIResources.play_button_Y + UIResources.play_button_height - 50
						&& Gdx.input.getY() > UIResources.play_button_Y - 50)) {
			DrawImage(UIResources.PlayButton_Active, UIResources.play_button_X - 20, UIResources.play_button_Y,
					UIResources.play_button_width, UIResources.play_button_height);

		} else {

			DrawImage(UIResources.PlayButton_UnActive, UIResources.play_button_X - 20, UIResources.play_button_Y,
					UIResources.play_button_width, UIResources.play_button_height);

		}

		// ---------------------------------------------

		if ((Gdx.input.getX() < UIResources.Setting_button_X + UIResources.Setting_button_width
				+ optionButton.getButton().getWidth() - 5 && Gdx.input.getX() > UIResources.Setting_button_X)
				&& (Gdx.input.getY() < UIResources.Setting_button_Y + UIResources.Setting_button_height + 100
						&& Gdx.input.getY() > UIResources.Setting_button_Y + 80)) {
			DrawImage(UIResources.SettingButton_Active, UIResources.Setting_button_X - 20, UIResources.Setting_button_Y,
					UIResources.Setting_button_width, UIResources.Setting_button_height);

		} else {

			DrawImage(UIResources.SettingButton_UnActive, UIResources.Setting_button_X - 20,
					UIResources.Setting_button_Y, UIResources.Setting_button_width, UIResources.Setting_button_height);

		}

		// ----------------------------------

		if ((Gdx.input.getX() < UIResources.Exit_button_X + UIResources.Exit_button_width
				+ exitButton.getButton().getWidth() - 5 && Gdx.input.getX() > UIResources.Exit_button_X)
				&& (Gdx.input.getY() < UIResources.Exit_button_Y + UIResources.Exit_button_height
						+ exitButton.getButton().getHeight() + 200
						&& Gdx.input.getY() > UIResources.Exit_button_Y + 250)) {
			DrawImage(UIResources.Exit_Activated, UIResources.Exit_button_X - 20, UIResources.Exit_button_Y,
					UIResources.Exit_button_width, UIResources.Exit_button_height);

		} else {

			DrawImage(UIResources.Exit_UnActivated, UIResources.Exit_button_X - 20, UIResources.Exit_button_Y,
					UIResources.Exit_button_width, UIResources.Exit_button_height);

		}

	}

}
