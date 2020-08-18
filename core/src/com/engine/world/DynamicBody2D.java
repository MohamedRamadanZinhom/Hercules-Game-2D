/** @author Z. Mohamed Osama */

package com.engine.world;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.math.Vec;

public class DynamicBody2D extends Body2D {

	public static final BodyType bdtype = BodyType.DynamicBody;

	private float restitution;
	private float friction;
	private float density;

	public DynamicBody2D(float restitution, float density, float friction) {

		super(bdtype);

		this.restitution = restitution;
		this.density = density;
		this.friction = friction;
	}

	@Override
	public void createBody(World world, float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, String bodyId) {

		Vector2 pos = new Vector2(posX, posY);

		if (scale) {
			Vec.div2D(pos, World2D.GU);
		}

		bdef.position.set(pos);

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.restitution = this.restitution;
		fdef.density = this.density;
		fdef.friction = this.friction;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}

		body.createFixture(fdef).setUserData(bodyId);

		if (bodies.containsKey(bodyId)) {

			bodies.get(bodyId).add(body);

		} else {

			bodies.put(bodyId, new Vector<Body>());
			bodies.get(bodyId).add(body);
		}
	}

	@Override
	public void createBody(World world, Shape shape, short categoryBits, short bitsMask, String bodyId) {

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.restitution = this.restitution;
		fdef.density = this.density;
		fdef.friction = this.friction;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}

		body.createFixture(fdef).setUserData(bodyId);

		if (bodies.containsKey(bodyId)) {

			bodies.get(bodyId).add(body);

		} else {

			bodies.put(bodyId, new Vector<Body>());
			bodies.get(bodyId).add(body);
		}
	}

	public void setDynamicProperty(float restitution, float density, float friction) {

		this.restitution = restitution;
		this.density = density;
		this.friction = friction;
	}
}
