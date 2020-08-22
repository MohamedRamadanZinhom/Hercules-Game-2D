package com.hercules.init;

import java.util.HashMap;

import com.engine.world.World2D;

public class Demon extends Character {

	public Demon(String typeId, String weaponName, String[][] spritesDirname, HashMap<String, Float> FPS_SCALE,
			float frameDuration, CharacterStatus status, boolean scaleByGU) {
		super(typeId, weaponName, spritesDirname, FPS_SCALE, frameDuration, status, scaleByGU);
	}

	@Override
	public void initActor(World2D world) {

	}

	@Override
	public void update(boolean deltaTimeScale) {

	}

}
