package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hercules.game.GameMenu;
import com.hercules.game.GameState;

public class OptionMenu implements Screen {

	private final GameState game;
	private final MenuButtons backButton;
	private final MenuButtons muteButton;
	private final MenuButtons playSoundButton;

	private final Stage stage;
	private BitmapFont soundfont;

	public OptionMenu(GameState gState) {

		stage = new Stage(new ScreenViewport());
		this.game = gState;

		backButton = new MenuButtons("Back", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 3 + 230.0f, Gdx.graphics.getHeight() / 2 - 200);
		backButton.getButton().setColor(Color.RED);
		backButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameMenu(game));

			}

		});

		muteButton = new MenuButtons("Mute Sound", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 4 + 500, Gdx.graphics.getHeight() / 2);
		muteButton.getButton().setColor(Color.GREEN);
		muteButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				UIResources.sound.Music_Pause();

			}

		});

		playSoundButton = new MenuButtons("Play Sound", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 4 + 250.0f, Gdx.graphics.getHeight() / 2);
		playSoundButton.getButton().setColor(Color.GREEN);
		playSoundButton.getButton().addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				UIResources.sound.Music_Play(100.0f);

			}

		});

		soundfont = new BitmapFont(Gdx.files.internal(UIResources.pathUI + "writing.fnt"));

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 100, 200, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw(UIResources.MenuBackGround, 0, 0); // for the backGround image of Screen .....>>

		GlyphLayout layout = new GlyphLayout(soundfont, "Volume : " + UIResources.Volume);
		soundfont.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2,
				Gdx.graphics.getHeight() - layout.height - 10);

		stage.act();
		stage.draw();

		game.batch.end();
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

	}
}
