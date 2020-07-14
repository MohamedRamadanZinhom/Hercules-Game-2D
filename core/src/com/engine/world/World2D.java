package com.engine.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class World2D {
	
	private World world;
	private OrthographicCamera envCam;
	
	public World2D(Vector2 gravity, boolean doSleep) {
		this.world = new World(gravity, doSleep);
	}
}
