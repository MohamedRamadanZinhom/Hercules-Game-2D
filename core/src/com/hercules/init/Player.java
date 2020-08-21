/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.security.KeyException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.Body2D;
import com.engine.world.Camera2D;
import com.engine.world.World2D;
import com.hercules.game.GameAdapter;

public class Player extends Character {

	public static boolean onGround = false;
	public static boolean hit = false; // if true: enemy health --> decrease
	public static boolean onBound = false;

	public static final String mainDir = "./sprite-sheets/player/";

	public static final String[][] spritesDirname = { { mainDir + "iwr.png", mainDir + "jhad.png" },
			{ mainDir + "iwr-left.png", mainDir + "jhad-left.png" } };

	public static final int[] FRAME_ROWS = { 1, 1 };
	public static final int[] FRAME_COLS = { 30, 40 };

	/**
	 * Use the same keys: pass reversed key order parameter - {@link #keysOrder)}
	 */
	public static final int[][] startKeys = { { 0, 9, 19 }, { 0, 9, 19, 29 } };
	public static final int[][] endKeys = { { 9, 19, 29 }, { 9, 19, 29, 39 } };

	public static final String[][] typeKeys = { { "idle", "walk", "run" }, { "jump", "attack", "hurt", "die" } };

	public static final String[] keysOrder = { "right", "left" };

	public static int index = 0;
	public static String currentMode = "idle";

	public static final HashMap<String, Float> FPS_SCALE = new HashMap<String, Float>() {

		private static final long serialVersionUID = -1386435843423357563L;

		{
			put("idle", 0.3f);
			put("walk", 0.3f);
			put("run", 0.5f);
			put("jump", 0.15f);
			put("attack", 0.5f);
			put("hurt", 0.5f);
			put("die", 0.5f);

		}
	}; // Animation - Frame Key Speed

	public final float runScale;
	public final float jumpScale;
	public final float smashingScale;

	public Body playerActor;

	public Body swordR;
	public Body swordL;

	public Camera2D camera;

	/**
	 * @param spritesDirname: String [][] - 2d array, each index specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param name:           String - Player Id
	 * @param posX:           float - Player initial position - X
	 * @param posY:           float - Player initial position - Y
	 * @param speed:          float - Player initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultIndex    : int - Initial sprite sheet index (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Player(String name, float posX, float posY, float speed, float runScale, float jumpScale,
			float smashingScale) {

		super(spritesDirname, name, posX, posY, speed, FPS_SCALE, GameAdapter.frameDuration, index, currentMode, false);

		this.runScale = runScale;
		this.jumpScale = jumpScale;
		this.smashingScale = smashingScale;

		this.camera = new Camera2D(GameAdapter.V_WIDTH, GameAdapter.V_HEIGHT);

		this.camera.position.x = this.camera.viewportWidth / 2;
		this.camera.position.y = this.camera.viewportHeight / 2;

		this.camera.update();
	}

	public void initPlayer(World2D world) {

		this.initCharacter(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);

		// Player Actor
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(40.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(shape, 0.0f, 0.0f, 1.0f, World.BIT_PLAYER, World.BIT_ANY, false, "player");

		float x = this.posX / World2D.GU + this.getTileWidth() / (2 * World2D.GU);
		float y = this.posY / World2D.GU + this.getTileHeight() / (2 * World2D.GU);

		this.playerActor = Body2D.bodies.get("player").get(0);
		this.playerActor.setTransform(new Vector2(x, y), 0.0f);

		// Player swordR
		PolygonShape swordShapeR = new PolygonShape();
		swordShapeR.setAsBox(10.0f / World2D.GU, 10.0f / World2D.GU);

		world.createDynamicBody(swordShapeR, World.BIT_PLAYER, World.BIT_ANY, true, "sword");

		this.swordR = Body2D.bodies.get("sword").get(0);
		this.swordR.setTransform(new Vector2(x + 0.5f, y + 0.5f), 0.0f);

		// Player swordL
		PolygonShape swordShapeL = new PolygonShape();
		swordShapeL.setAsBox(10.0f / World2D.GU, 10.0f / World2D.GU);

		world.createDynamicBody(swordShapeL, World.BIT_PLAYER, World.BIT_ANY, true, "sword");

		this.swordL = Body2D.bodies.get("sword").get(1);
		this.swordL.setTransform(new Vector2(x - 0.5f, y + 0.5f), 0.0f);

	}

	@Override
	public void animate() {

		if (!World2D.onPhysicsDebugMode) {
			
			float x = this.getPosX() * World2D.GU - this.getTileWidth() / 2;
			float y = this.getPosY() * World2D.GU - this.getTileHeight() / 2 + 40.0f;

			this.animator[index].getSpriteBatch().setProjectionMatrix(this.camera.combined);

			// Animate
			try {

				this.animator[index].animate(currentMode, x, y, FPS_SCALE);

			} catch (KeyException error) {

				error.printStackTrace();
			}

			if (Player.onGround) {

				currentMode = "idle";
			}
		}
	}

	/**
	 * Movements, States
	 * 
	 * @param speedScale     : float - Player speed scale
	 * @param deltaTimeScale : boolean - If true, scale character's movements, by
	 *                       delta time.
	 */
	@Override
	public void update(boolean deltaTimeScale) {

		float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() + 1.0f : 1.0f;

		float xStep = 0.0f; // this.speed * scale
		float yStep = 0.0f; // 10.0f * scale;

		float swordXStep = 90.0f;
		float swordYStep = 30.0f;

		float dir = (index == 0) ? 1 : -1;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			xStep = this.speed * scale;

			if (Player.onGround) {

				currentMode = "walk";
			}

			index = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			xStep = -this.speed * scale;

			if (Player.onGround) {

				currentMode = "walk";
			}

			index = 1;
		}

		if (Gdx.input.isKeyPressed(Keys.UP) && Player.onGround) {

			yStep = this.jumpScale * scale;
			currentMode = "jump";
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN) && !Player.onGround) {

			// Smashing
			xStep = dir * this.smashingScale * scale;
			yStep = -this.smashingScale * scale;

			if (Player.onGround) {
				currentMode = "attack";
			}
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			// Attack

			currentMode = "attack";

			// do somthing
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)
				&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

			xStep += this.runScale;

			currentMode = "run";
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)
				&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

			xStep -= this.runScale;

			currentMode = "run";
		}

		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			// Spying

			// do somthing
		}

		Vector2 actorPrevPos = new Vector2(this.playerActor.getPosition()); // copy
		Vector2 actorPos = this.playerActor.getPosition();
		actorPos.x += xStep / World2D.GU;

		// Player Actor
		this.playerActor.setTransform(actorPos, 0.0f);

		// Sword
		Vector2 swordPosR = new Vector2(this.playerActor.getPosition());
		Vector2 swordPosL = new Vector2(this.playerActor.getPosition());

		swordPosR.x += swordXStep / World2D.GU;
		swordPosR.y += swordYStep / World2D.GU;

		swordPosL.x -= swordXStep / World2D.GU;
		swordPosL.y += swordYStep / World2D.GU;

		this.swordR.setTransform(swordPosR, 0.0f);
		this.swordL.setTransform(swordPosL, 0.0f);

		if (Player.onBound) {
			actorPrevPos.x += (actorPrevPos.x * 0.01);
			this.playerActor.setTransform(actorPrevPos, 0.0f); // Set - Previous Position
		}

		this.playerActor.applyForceToCenter(0.0f, yStep, true); // Y-axis

	}

	@Override
	public float getPosX() {
		return this.playerActor.getPosition().x;
	}

	@Override
	public float getPosY() {
		return this.playerActor.getPosition().y;
	}

}
