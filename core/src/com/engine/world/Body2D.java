/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

abstract public class Body2D {

	protected final BodyType bdtype;
	protected BodyDef bdef;
	protected FixtureDef fdef;

	public Body2D(BodyType bdtype) {

		fdef = new FixtureDef();
		bdef = new BodyDef();

		this.bdtype = bdtype;
		bdef.type = this.bdtype;
	}

}
