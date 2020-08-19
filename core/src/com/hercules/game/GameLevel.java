package com.hercules.game;

import com.hercules.events.Warning;

public class GameLevel { // No. of Levels = 5

	public int level;

	public GameLevel(int level) {

		if (level < 1 || level > 5) {

			Warning.setWarning(GameLevel.class.getName(), "Invalid Game Level = " + Integer.toString(level));

			this.level = 1;
		}

		else {

			this.level = level;
		}
	}
}
