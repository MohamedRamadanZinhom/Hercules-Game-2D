package com.engine.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class World2D {

	private static Box2DDebugRenderer b2dr = new Box2DDebugRenderer();

	protected World world;
	protected OrthographicCamera envCam;

	public World2D(float gravityX, float gravityY, boolean doSleep) {

		this.world = new World(new Vector2(gravityX, gravityY), doSleep);
		this.envCam = new OrthographicCamera();

	}

	public void createStaticBody(float posX, float posY, Shape shape, boolean scale) {

		StaticBody2D body = new StaticBody2D();
		body.createBody(world, posX, posY, shape, scale);
	}

	public void createDynamicBody(float posX, float posY, Shape shape, boolean scale, float restitution, float density,
			float friction) {

		DynamicBody2D body = new DynamicBody2D(restitution, density, friction);
		body.createBody(world, posX, posY, shape, scale);
	}
	
	public void createDynamicBody(float posX, float posY, Shape shape, boolean scale) {

		DynamicBody2D body = new DynamicBody2D(0.0f, 0.0f, 0.0f);
		body.createBody(world, posX, posY, shape, scale);
	}

	public void update(float deltaTime, int velocityIterations, int positionIterations) {
		world.step(deltaTime, velocityIterations, positionIterations);
	}

	/**
	 * Debug bodies and physical object, properties
	 * --------------------------------------------
	 * 
	 * @param world:   World
	 * @param Matrix4: projMatrix
	 */
	public void renderBox2dDebug(Matrix4 projMatrix) {

		World2D.b2dr.render(world, projMatrix);
	}

}
