/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class KinematicBody extends Body2D {

	public static final BodyType bdtype = BodyType.KinematicBody;

	public KinematicBody() {

		super(bdtype);

	}

}
