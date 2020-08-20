package com.hercules.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hercules.game.GameAdapter;

public class DesktopLauncher {

	static LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

	public static void main(String[] arg) {

		config.title = GameAdapter.TITEL;

		config.width = GameAdapter.V_WIDTH;
		config.height = GameAdapter.V_HEIGHT;

		config.fullscreen = false;

		new LwjglApplication(new GameAdapter(), config);
	}
}
