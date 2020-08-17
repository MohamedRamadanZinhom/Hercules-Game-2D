/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class StaticBody2D extends Body2D {

	public static final BodyType bdtype = BodyType.StaticBody;

	public StaticBody2D() {
		super(bdtype);
	}

}
