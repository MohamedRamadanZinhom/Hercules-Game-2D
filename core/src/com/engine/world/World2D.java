/** @author Z. Mohamed Osama */

package com.engine.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
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

	public static PolygonShape getPolygon(PolygonMapObject polygonObject) {

		PolygonShape polygon = new PolygonShape();
		float[] vertices = polygonObject.getPolygon().getTransformedVertices();

		float[] worldVertices = new float[vertices.length];

		for (int i = 0; i < vertices.length; ++i) {
			worldVertices[i] = vertices[i] / World2D.GU;
		}

		polygon.set(worldVertices);
		return polygon;
	}

	public static ChainShape getPolyline(PolylineMapObject polylineObject) {
		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];

		for (int i = 0; i < vertices.length / 2; ++i) {
			worldVertices[i] = new Vector2();
			worldVertices[i].x = vertices[i * 2] / World2D.GU;
			worldVertices[i].y = vertices[i * 2 + 1] / World2D.GU;
		}

		ChainShape chain = new ChainShape();
		chain.createChain(worldVertices);
		return chain;
	}

	public void dispose() {

		b2dr.dispose();
		world.dispose();
	}
}
