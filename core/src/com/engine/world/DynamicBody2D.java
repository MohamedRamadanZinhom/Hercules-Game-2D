/** @author Z. Mohamed Osama */

package com.engine.world;

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

	public void createBody(World world, float posX, float posY, Shape shape, boolean scale) {

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

		body.createFixture(fdef);
	}

	public void createBody(World world, Shape shape, Vector2 position, float angle) {

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.restitution = this.restitution;
		fdef.density = this.density;
		fdef.friction = this.friction;

		body.createFixture(fdef);

		body.setTransform(position, angle);

	}

	public void setDynamicProperty(float restitution, float density, float friction) {

		this.restitution = restitution;
		this.density = density;
		this.friction = friction;
	}
}
