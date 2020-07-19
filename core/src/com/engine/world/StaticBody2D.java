package com.engine.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.math.Vec;
import com.hercules.game.GameAdapter;

public class StaticBody2D extends Body2D {

	public static final BodyType bdtype = BodyType.StaticBody;

	public StaticBody2D() {
		super(bdtype);
	}

	public void createBody(World world, float posX, float posY, Shape shape, boolean scale) {

		Vector2 pos = new Vector2(posX, posY);

		if (scale) {
			Vec.div2D(pos, GameAdapter.GU);
		}

		bdef.position.set(pos);

		Body body = world.createBody(bdef);

		fdef.shape = shape;
		body.createFixture(fdef);
	}
}
