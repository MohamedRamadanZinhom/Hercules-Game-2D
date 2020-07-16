package com.engine.body;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.engine.math.Vec;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.hercules.game.GameAdapter;

abstract public class Body2DStack {
	
	private static Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
	
	protected static BodyType type;
	protected static BodyDef bdef = new BodyDef();
	protected static Body body;
	protected static FixtureDef fdef = new FixtureDef();
	
	public Body2DStack(BodyType bType) {
		Body2DStack.type = bType;
	}
	
	/**Debug bodies and physical object, properties
	 * --------------------------------------------
	 * @param world: World
	 * @param Matrix4: projMatrix
	 */
	public static void render2DBody(World world
			, Matrix4 projMatrix) {
		
		Body2DStack.b2dr.render(world, projMatrix);
	}
	
	public static void createBody(World world, Vector2 pos
			, BodyType type, Shape shape) {
		
		Body2DStack.body = world.createBody(Body2DStack.bdef);
		
		Body2DStack.bdef.position.set(pos);
		Body2DStack.fdef.shape = shape;

		Body2DStack.body.createFixture(fdef);
	}
	
	public static void setPos(Vector2 pos) {
		
		Vec.div2D(pos, GameAdapter.GU);
		Body2DStack.bdef.position.set(pos);
	}
}
