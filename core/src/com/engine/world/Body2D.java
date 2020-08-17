/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.math.Vec;

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

	public void createBody(World world, float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, String bodyId) {

		Vector2 pos = new Vector2(posX, posY);

		if (scale) {
			Vec.div2D(pos, World2D.GU);
		}

		bdef.position.set(pos);

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;
		fdef.filter.maskBits = bitsMask;

		body.createFixture(fdef).setUserData(bodyId);
	}

	public void createBody(World world, Shape shape, boolean scale, Vector2 position, float angle, short categoryBits,
			short bitsMask, String bodyId) {

		if (scale) {
			Vec.div2D(position, World2D.GU);
		}

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;
		fdef.filter.maskBits = bitsMask;

		body.createFixture(fdef).setUserData(bodyId);

		body.setTransform(position, angle);

	}

	public void createBody(World world, Shape shape, short categoryBits, short bitsMask, String bodyId) {

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;
		fdef.filter.maskBits = bitsMask;

		body.createFixture(fdef).setUserData(bodyId);
	}

}
