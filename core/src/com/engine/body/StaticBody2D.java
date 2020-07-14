package com.engine.body;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class StaticBody2D extends Body2DStack {
	
	private static final BodyType type = BodyType.StaticBody; 
	
	public StaticBody2D() {
		super(type);
	}
}