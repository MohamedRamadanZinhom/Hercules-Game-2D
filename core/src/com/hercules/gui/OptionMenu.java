package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hercules.game.GameAdapter;

public class OptionMenu implements Screen {

	private final MenuButton backButton;
	private final MenuButton muteButton;
	private final MenuButton playSoundButton;

	private Stage stage;
	private SpriteBatch batch;

	private BitmapFont soundFont;

	private GlyphLayout layout;

	public OptionMenu() {

		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());

		backButton = new MenuButton("Back", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 3 + 230.0f, Gdx.graphics.getHeight() / 2 - 200);
		backButton.getButton().setColor(Color.RED);

		muteButton = new MenuButton("Mute Sound", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 4 + 500, Gdx.graphics.getHeight() / 2);
		muteButton.getButton().setColor(Color.GREEN);

		playSoundButton = new MenuButton("Play Sound", new Skin(Gdx.files.internal(UIResources.uiSkinPath)), stage,
				Gdx.graphics.getWidth() / 4 + 250.0f, Gdx.graphics.getHeight() / 2);
		playSoundButton.getButton().setColor(Color.GREEN);

		soundFont = new BitmapFont(Gdx.files.internal(UIResources.pathUI + "writing.fnt"));

		layout = new GlyphLayout(soundFont, "Volume : " + UIResources.Volume);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		batch.begin();

		GameAdapter.state.drawBackGround(batch);
		soundFont.draw(batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2,
				Gdx.graphics.getHeight() - layout.height - 10);

		batch.end();

		menuInputUpdate();

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

		soundFont.dispose();
		stage.dispose();
	}

	public void menuInputUpdate() {

		if (muteButton.isOver(0.0f, -50.0f)) {

			if (MenuButton.isLeftClick()) {

				UIResources.sound.pause();
				layout.setText(soundFont, "Volume : " + 0);
			}
		}

		if (playSoundButton.isOver(-1.0f, -50.0f)) {

			if (MenuButton.isLeftClick()) {

				UIResources.sound.play(100.0f);
				layout.setText(soundFont, "Volume : " + 100);
			}
		}

		if (backButton.isOver(0.0f, 351.0f)) {

			if (MenuButton.isLeftClick()) {

				GameAdapter.state.updateState("menu-screen");
			}
		}
	}
}
