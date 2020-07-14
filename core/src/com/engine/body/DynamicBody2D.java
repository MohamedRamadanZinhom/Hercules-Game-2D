package com.engine.body;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class DynamicBody2D extends Body2DStack {
	
	private static final BodyType type = BodyType.DynamicBody; 
	
	public DynamicBody2D() {
		super(type);
	}
	
}
