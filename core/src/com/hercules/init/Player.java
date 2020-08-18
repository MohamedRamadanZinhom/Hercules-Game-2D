/** @author Z. Mohamed Osama */

package com.hercules.init;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.Body2D;
import com.engine.world.World2D;

public class Player extends Character {

	public static boolean OnGround = true;

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

	public static final float FPS_SCALE = 1 / 20.0f;
	public static final float frameDuration = 0.0025f;

	public static final float runScale = 5.0f;

	Body playerActor;

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
	public Player(String name, float posX, float posY, float speed) {

		super(spritesDirname, name, posX, posY, speed, FPS_SCALE, frameDuration, index, currentMode, false);

	}

	public void initPlayer(World2D world) {

		this.initCharacter(FRAME_ROWS, FRAME_COLS, startKeys, endKeys, typeKeys, keysOrder);
		
		// Player Actor
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20.0f / World2D.GU, 5.0f / World2D.GU);

		short mask = -1;

		world.createKinematicBody(shape, World.BIT_PLAYER, mask, "player");

		playerActor = Body2D.bodies.get("player").get(0);
		playerActor.setTransform(new Vector2(65.0f / World2D.GU, 115.0f / World2D.GU), 0.0f);
	}

	@Override
	public void animate() {

		this.animator[index].animate(currentMode, this.posX, this.posY, FPS_SCALE);

		currentMode = "idle";
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

		float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() + 0.6f : 1.0f;

		float xStep = this.speed * scale;
		float yStep = 10.0f * scale;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			this.posX += xStep;

			currentMode = "walk";
			index = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			this.posX -= xStep;

			currentMode = "walk";
			index = 1;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {

			this.posY += yStep;

			currentMode = "jump";
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			// Smashing
			this.posY -= yStep;
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			// Attack

			currentMode = "attack";
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)
				&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

			this.posX += runScale;

			currentMode = "run";
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)
				&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

			this.posX -= runScale;

			currentMode = "run";
		}

		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			// Spying
		}
	}
}
