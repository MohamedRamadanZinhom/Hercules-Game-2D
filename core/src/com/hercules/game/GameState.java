package com.hercules.game;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.engine.ui.ConsoleLogger;
import com.hercules.gui.GameMenu;
import com.hercules.gui.OptionMenu;
import com.hercules.gui.UIResources;

public class GameState extends Game {

	public static HashMap<String, Screen> screenRepo = new HashMap<String, Screen>();
	public static String currentState = "menu-screen";

	public GameState() {

	}

	public void updateState(String state) {

		currentState = state;
		setScreen(screenRepo.get(currentState));
	}

	public void newPlayState() {

		// do something

		ConsoleLogger.setWarning("GameState", "Overwrite GameState = " + "play-screen");
	}

	public void drawBackGround(Batch batch) {

		batch.draw(UIResources.MenuBackGround, 0, 0);
	}

	public void drawTexture(Batch batch, Texture img, float x, float y, float width, float height) {

		batch.draw(img, x, y, width, height);
	}

	@Override
	public void create() {

		screenRepo.put("menu-screen", new GameMenu());
		screenRepo.put("option-screen", new OptionMenu());
		screenRepo.put("play-screen", new PlayScreen());

		this.setScreen(screenRepo.get(currentState));
	}

	@Override
	public void dispose() {

		super.dispose();

		for (String key : screenRepo.keySet()) {

			screenRepo.get(key).dispose();
		}
	}

}
