package com.hercules.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hercules.game.GameAdapter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config =
				new LwjglApplicationConfiguration();
		
		config.title  = GameAdapter.TITEL;
		config.width  = GameAdapter.V_WIDTH * GameAdapter.SCALE;
		config.height = GameAdapter.V_HEIGHT * GameAdapter.SCALE;
		new LwjglApplication(new GameAdapter(), config);
	}
}
