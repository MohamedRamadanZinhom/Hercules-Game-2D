package com.hercules.init;

import java.security.KeyException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.Body2D;
import com.engine.world.World2D;
import com.hercules.game.GameAdapter;

public class Demon extends Character { // Enemy

	public static boolean onGround = false;
	public static boolean hit = false; // if true: player health --> decrease
	public static boolean onBound = false;

	public static final String mainDir = "./sprite-sheets/enemy/1/";

	public static final String[][] spritesDirname = { { mainDir + "demon.png" }, { mainDir + "demon-left.png" } };

	public static final int[] FRAME_ROWS = { 1 };
	public static final int[] FRAME_COLS = { 21 };

	/**
	 * Use the same keys: pass reversed key order parameter - {@link #keysOrder)}
	 */
	public static final int[][] startKeys = { { 0, 3, 9, 13, 15 } };
	public static final int[][] endKeys = { { 2, 8, 12, 14, 20 } };

	public static final String[][] typeKeys = { { "idle", "walk", "attack", "hurt", "death" } };

	public static final String[] keysOrder = { "right", "left" };

	public static int index = 0;
	public static String currentMode = "idle";

	public static final HashMap<String, Float> FPS_SCALE = new HashMap<String, Float>() {

		private static final long serialVersionUID = -1386435843423357563L;

		{
			put("idle", 0.1f);
			put("walk", 0.1f);
			put("attack", 0.1f);
			put("hurt", 0.1f);
			put("die", 0.1f);

		}
	}; // Animation - Frame Key Speed

	public Demon(String name, float posX, float posY, float speed, float runScale, float jumpScale,
			float smashingScale) {

		super(spritesDirname, name, posX, posY, speed, runScale, jumpScale, smashingScale, FPS_SCALE,
				GameAdapter.frameDuration, index, currentMode, false);

	}

	@Override
	public void initActor(World2D world) { // copy-past - class Player::initActor(...)

		this.initCharacter(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(30.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(shape, 0.0f, 0.0f, 1.0f, World.BIT_ENEMY_T1, World.BIT_ANY, false, "enemy");

		float x = this.posX / World2D.GU + this.getTileWidth() / (2 * World2D.GU);
		float y = this.posY / World2D.GU + this.getTileHeight() / (2 * World2D.GU);

		this.actor = Body2D.bodies.get("enemy").get(0);
		this.actor.setTransform(new Vector2(x, y), 0.0f);

		// Demon fauchardR
		PolygonShape fauchardShapeR = new PolygonShape();
		fauchardShapeR.setAsBox(10.0f / World2D.GU, 10.0f / World2D.GU);

		world.createDynamicBody(fauchardShapeR, World.BIT_ENEMY_T1, World.BIT_ANY, true, "enemy-weapon");

		this.weaponMaskR = Body2D.bodies.get("enemy-weapon").get(0);
		this.weaponMaskR.setTransform(new Vector2(x + 0.5f, y + 0.5f), 0.0f);

		// Demon fauchardL
		PolygonShape fauchardShapeL = new PolygonShape();
		fauchardShapeL.setAsBox(10.0f / World2D.GU, 10.0f / World2D.GU);

		world.createDynamicBody(fauchardShapeL, World.BIT_ENEMY_T1, World.BIT_ANY, true, "enemy-weapon");

		this.weaponMaskL = Body2D.bodies.get("enemy-weapon").get(1);
		this.weaponMaskL.setTransform(new Vector2(x - 0.5f, y + 0.5f), 0.0f);
	}

	@Override
	public void animate() {

		if (!World2D.onPhysicsDebugMode) {

			float x = this.getPosX() * World2D.GU - this.getTileWidth() / 2;
			float y = this.getPosY() * World2D.GU - this.getTileHeight() / 2 + 50.0f;

			this.animator[index].getSpriteBatch().setProjectionMatrix(Character.camera.combined);

			// Animate
			try {

				this.animator[index].animate(currentMode, x, y, FPS_SCALE);

			} catch (KeyException error) {

				error.printStackTrace();
			}

			currentMode = "idle";

		}

	}

	@Override
	public void update(boolean deltaTimeScale) {

		float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() + 1.0f : 1.0f;

		float xStep = 0.0f; // this.speed * scale
		float yStep = 0.0f; // 10.0f * scale;

		float maskXStep = 60.0f;
		float maskYStep = 20.0f;

		float dir = (index == 0) ? 1 : -1;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			xStep = this.speed * scale;

			if (Demon.onGround) {

				currentMode = "walk";
			}

			index = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			xStep = -this.speed * scale;

			if (Demon.onGround) {

				currentMode = "walk";
			}

			index = 1;
		}

		if (Gdx.input.isKeyPressed(Keys.UP) && Demon.onGround) {

			yStep = this.jumpScale * scale;
			currentMode = "walk";
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN) && !Demon.onGround) {

			// Smashing
			xStep = dir * this.smashingScale * scale;
			yStep = -this.smashingScale * scale;

			if (Demon.onGround) {
				currentMode = "attack";
			}
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			// Attack

			currentMode = "attack";

			// do somthing
		}

		Vector2 actorPrevPos = new Vector2(this.actor.getPosition()); // copy
		Vector2 actorPos = this.actor.getPosition();
		actorPos.x += xStep / World2D.GU;

		// Demon Actor
		this.actor.setTransform(actorPos, 0.0f);

		// Sword
		Vector2 maskPosR = new Vector2(this.actor.getPosition());
		Vector2 maskPosL = new Vector2(this.actor.getPosition());

		maskPosR.x += maskXStep / World2D.GU;
		maskPosR.y += maskYStep / World2D.GU;

		maskPosL.x -= maskXStep / World2D.GU;
		maskPosL.y += maskYStep / World2D.GU;

		this.weaponMaskR.setTransform(maskPosR, 0.0f);
		this.weaponMaskL.setTransform(maskPosL, 0.0f);

		if (Demon.onBound) {
			actorPrevPos.x += (actorPrevPos.x * 0.01);
			this.actor.setTransform(actorPrevPos, 0.0f); // Set - Previous Position
		}

		this.actor.applyForceToCenter(0.0f, yStep, true); // Y-axis
	}

}
