/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class World2D {

	private static Box2DDebugRenderer b2dr = new Box2DDebugRenderer();
	public static boolean onDebugMode = false;

	protected World world;
	protected Camera2D envCam;

	public static float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static float SCREEN_HEIGHT = Gdx.graphics.getHeight();

	public static float GU = 1.0f;

	public World2D(float gravityX, float gravityY, float GU, boolean doSleep) {

		this.world = new World(new Vector2(gravityX, gravityY), doSleep);
		this.envCam = new Camera2D();

		World2D.GU = GU;
	}

	public void createStaticBody(Shape shape, short categoryBits, short bitsMask, boolean isSensor, String bodyId) {

		StaticBody2D body = new StaticBody2D();
		body.createBody(world, shape, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createStaticBody(float posX, float posY, Shape shape, boolean scale, short categoryBits, short bitsMask,
			boolean isSensor, String bodyId) {

		StaticBody2D body = new StaticBody2D();
		body.createBody(world, posX, posY, shape, scale, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createDynamicBody(Shape shape, short categoryBits, short bitsMask, boolean isSensor, String bodyId) {

		DynamicBody2D body = new DynamicBody2D(0.0f, 0.0f, 0.0f);
		body.createBody(world, shape, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createDynamicBody(float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, boolean isSensor, String bodyId) {

		DynamicBody2D body = new DynamicBody2D(0.0f, 0.0f, 0.0f);
		body.createBody(world, posX, posY, shape, scale, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createDynamicBody(Shape shape, float restitution, float density, float friction, short categoryBits,
			short bitsMask, boolean isSensor, String bodyId) {

		DynamicBody2D body = new DynamicBody2D(restitution, density, friction);
		body.createBody(world, shape, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createDynamicBody(float posX, float posY, Shape shape, boolean scale, float restitution, float density,
			float friction, short categoryBits, short bitsMask, boolean isSensor, String bodyId) {

		DynamicBody2D body = new DynamicBody2D(restitution, density, friction);
		body.createBody(world, posX, posY, shape, scale, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createKinematicBody(float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, boolean isSensor, String bodyId) {

		KinematicBody body = new KinematicBody();
		body.createBody(world, posX, posY, shape, scale, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createKinematicBody(float posX, float posY, Shape shape, boolean scale, short categoryBits,
			short bitsMask, boolean isSensor, Object object) {

		KinematicBody body = new KinematicBody();
		body.createBody(world, posX, posY, shape, scale, categoryBits, bitsMask, isSensor, object);
	}

	public void createKinematicBody(Shape shape, short categoryBits, short bitsMask, boolean isSensor, String bodyId) {

		KinematicBody body = new KinematicBody();
		body.createBody(world, shape, categoryBits, bitsMask, isSensor, bodyId);
	}

	public void createKinematicBody(Shape shape, short categoryBits, short bitsMask, boolean isSensor, Object object) {

		KinematicBody body = new KinematicBody();
		body.createBody(world, shape, categoryBits, bitsMask, isSensor, object);
	}

	public void update(float deltaTime, int velocityIterations, int positionIterations) {
		world.step(deltaTime, velocityIterations, positionIterations);
	}

	public void setContactListener(ContactListener listener) {
		world.setContactListener(listener);
	}

	/**
	 * Debug bodies and physical object, properties
	 * --------------------------------------------
	 * 
	 * @param world:   World
	 * @param Matrix4: projMatrix
	 */
	public void renderBox2dDebug(Matrix4 projMatrix) {

		if (Gdx.input.isKeyJustPressed(Keys.D)) {
			World2D.onDebugMode = !onDebugMode;
		}

		if (World2D.onDebugMode) {
			World2D.b2dr.render(world, projMatrix);
		}
	}

	public static PolygonShape getRectangle(RectangleMapObject rectangleObject) {

		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();

		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / World2D.GU,
				(rectangle.y + rectangle.height * 0.5f) / World2D.GU);

		polygon.setAsBox(rectangle.width * 0.5f / World2D.GU, rectangle.height * 0.5f / World2D.GU, size, 0.0f);
		return polygon;
	}

	public static CircleShape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(circle.radius / World2D.GU);
		circleShape.setPosition(new Vector2(circle.x / World2D.GU, circle.y / World2D.GU));
		return circleShape;
	}

	public static ChainShape getPolyline(PolygonMapObject polygonObject) {

		ChainShape polygon = new ChainShape();
		float[] vertices = polygonObject.getPolygon().getTransformedVertices();

		for (int i = 0; i < vertices.length; ++i) {
			vertices[i] = vertices[i] / World2D.GU;
		}

		polygon.createChain(vertices);
		return polygon;
	}

	public void dispose() {

		b2dr.dispose();
		world.dispose();
	}
}
