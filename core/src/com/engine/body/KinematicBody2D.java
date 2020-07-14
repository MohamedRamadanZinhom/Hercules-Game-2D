package com.engine.body;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class KinematicBody2D extends Body2DStack {
	
	private static final BodyType type = BodyType.KinematicBody; 
	
	public KinematicBody2D() {
		super(type);
	}
}