/** @author Z. Mohamed Osama */

package com.engine.world;

import java.util.HashMap;
import java.util.Vector;

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

	public static HashMap<String, Vector<Body>> bodies = new HashMap<String, Vector<Body>>();

	public Body2D(BodyType bdtype) {

		fdef = new FixtureDef();
		bdef = new BodyDef();

		this.bdtype = bdtype;
		bdef.type = this.bdtype;
	}

	public void createBody(World world, float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, boolean isSensor, String bodyId) {

		Vector2 pos = new Vector2(posX, posY);

		if (scale) {
			Vec.div2D(pos, World2D.GU);
		}

		bdef.position.set(pos);

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}

		fdef.isSensor = isSensor;

		body.createFixture(fdef).setUserData(bodyId);

		if (bodies.containsKey(bodyId)) {

			bodies.get(bodyId).add(body);

		} else {

			bodies.put(bodyId, new Vector<Body>());
			bodies.get(bodyId).add(body);
		}
	}

	public void createBody(World world, float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, boolean isSensor, Object object) {

		Vector2 pos = new Vector2(posX, posY);

		if (scale) {
			Vec.div2D(pos, World2D.GU);
		}

		bdef.position.set(pos);

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}
		
		fdef.isSensor = isSensor;

		body.createFixture(fdef).setUserData(object);
	}

	public void createBody(World world, Shape shape, boolean scale, Vector2 position, float angle, short categoryBits,
			short bitsMask, boolean isSensor, String bodyId) {

		if (scale) {
			Vec.div2D(position, World2D.GU);
		}

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}
		
		fdef.isSensor = isSensor;

		body.createFixture(fdef).setUserData(bodyId);

		body.setTransform(position, angle);

		if (bodies.containsKey(bodyId)) {

			bodies.get(bodyId).add(body);

		} else {

			bodies.put(bodyId, new Vector<Body>());
			bodies.get(bodyId).add(body);
		}
	}

	public void createBody(World world, Shape shape, boolean scale, Vector2 position, float angle, short categoryBits,
			short bitsMask, boolean isSensor, Object object) {

		if (scale) {
			Vec.div2D(position, World2D.GU);
		}

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}

		fdef.isSensor = isSensor;

		body.createFixture(fdef).setUserData(object);

		body.setTransform(position, angle);

	}

	public void createBody(World world, Shape shape, short categoryBits, short bitsMask, boolean isSensor, String bodyId) {

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}
		
		fdef.isSensor = isSensor;

		body.createFixture(fdef).setUserData(bodyId);

		if (bodies.containsKey(bodyId)) {

			bodies.get(bodyId).add(body);

		} else {

			bodies.put(bodyId, new Vector<Body>());
			bodies.get(bodyId).add(body);
		}
	}

	// Bind object with Box2D body
	public void createBody(World world, Shape shape, short categoryBits, short bitsMask, boolean isSensor, Object object) {

		Body body = world.createBody(bdef);

		fdef.shape = shape;

		fdef.filter.categoryBits = categoryBits;
		
		fdef.isSensor = isSensor;

		if (bitsMask != -1) {
			fdef.filter.maskBits = bitsMask;
		}

		body.createFixture(fdef).setUserData(object);
	}

	// Debug
	public static void debug() {

		for (String key : Body2D.bodies.keySet()) {

			String size = Integer.toString(Body2D.bodies.get(key).size());

			System.out.println(key + " - No. of bodies :" + size);
		}
	}

}
