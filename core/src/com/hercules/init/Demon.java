package com.hercules.init;

import java.security.KeyException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.Body2D;
import com.engine.world.World2D;
import com.hercules.game.GameLevel;
import com.hercules.game.PlayScreen;

public class Demon extends Character {

	private static int count = 0;

	private static final Resource res = ResourceManager.getDemonrResources();
	private float steps;
	private boolean inFrustum;

	protected float deathTime;

	/**
	 * @return the inFrustum
	 */
	public boolean isInFrustum() {
		return inFrustum;
	}

	/**
	 * @param inFrustum the inFrustum to set
	 */
	public void setInFrustum(boolean inFrustum) {
		this.inFrustum = inFrustum;
	}

	public final float backStep;
	public final float forthStep;

	/**
	 * @param spritesDirname: String [][] - 2d array, each dir specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param posX:           float - Enemy initial position - X
	 * @param posY:           float - Enemy initial position - Y
	 * @param speed:          float - Enemy initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultdir      : int - Initial sprite sheet dir (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Demon(CharacterStatus status, float forthStep, float backStep) {

		super("demon_" + Integer.toString(count), "fauchard", res.spritesDirname, res.FPS_SCALE, res.frameDuration,
				status, false);

		this.setSteps(0.0f);
		this.setInFrustum(false);

		this.backStep = backStep;
		this.forthStep = forthStep;

		count += 1;
	}

	@Override
	public void initActor(World2D world) {

		initCharacter(res.FRAME_ROWS, res.FRAME_COLS, res.startKeys, res.endKeys, res.typeKeys, res.keysOrder);

		// Enemy Actor
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(40.0f / World2D.GU, 50.0f / World2D.GU);

		world.createDynamicBody(shape, 0.0f, 0.0f, 1.0f, World.BIT_ENEMY_T1, World.BIT_ANY, false, typeId);

		float x = status.getStartingPosX() / World2D.GU + getTileWidth() / (2 * World2D.GU);
		float y = status.getStartingPosY() / World2D.GU + getTileHeight() / (2 * World2D.GU);

		actor = Body2D.bodies.get(typeId).get(0);
		actor.setTransform(new Vector2(x, y), 0.0f);

		PolygonShape fauchardShapeR = new PolygonShape();
		fauchardShapeR.setAsBox(5.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(fauchardShapeR, 0.0f, 0.0f, 0.0f, World.BIT_ENEMY_T1, World.BIT_PLAYER, true,
				getWeaponId());

		weaponMaskR = Body2D.bodies.get(getWeaponId()).get(0);
		weaponMaskR.setTransform(new Vector2(x + 0.5f, y + 0.5f), 0.0f);

		// Player swordL
		PolygonShape fauchardShapeL = new PolygonShape();
		fauchardShapeL.setAsBox(5.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(fauchardShapeL, 0.0f, 0.0f, 0.0f, World.BIT_ENEMY_T1, World.BIT_PLAYER, true,
				getWeaponId());

		weaponMaskL = Body2D.bodies.get(getWeaponId()).get(1);
		weaponMaskL.setTransform(new Vector2(x - 0.5f, y + 0.5f), 0.0f);

		// Death
		this.deathTime = this.animator[status.isDirRight()].getAnimationDuration("death");

	}

	@Override
	public void animate(float thresholdX, float thresholdY) {

		float x = this.getPosX() * World2D.GU - this.getTileWidth() / 2 + status.getDir() * thresholdX;
		float y = this.getPosY() * World2D.GU - this.getTileHeight() / 2 + thresholdY;

		this.animator[this.status.isDirRight()].getSpriteBatch().setProjectionMatrix(GameLevel.envCam.combined);

		if (!World2D.onPhysicsDebugMode) {

			if (!isDied()) {
				// Animate

				try {

					this.animator[this.status.isDirRight()].animate(status.getCurrentMode(), x, y, res.scale,
							this.FPS_SCALE);

				} catch (KeyException error) {

					error.printStackTrace();
				}

				if (this.status.isOnGround()) {

					this.status.setCurrentMode("idle");
				}
			}

			else if (this.deathTime - Gdx.graphics.getDeltaTime() > 0.0f) { // Death

				try {

					this.animator[this.status.isDirRight()].animate(status.getCurrentMode(), x, y, res.scale,
							this.FPS_SCALE);

				} catch (KeyException error) {

					error.printStackTrace();
				}

				this.deathTime -= Gdx.graphics.getDeltaTime();
			}
		}

	}

	public void wait(Player player, float distance) {

		if (!isDied()) {

			Vector2 playerPos = player.actor.getPosition().scl(World2D.GU);
			Vector2 demonPos = actor.getPosition().scl(World2D.GU);

			float damageScale = Gdx.graphics.getDeltaTime() + 0.4f;

			if (Math.abs(playerPos.x - demonPos.x) <= distance) {

				status.setDir(-player.status.getDir());
				status.setCurrentMode("attack");
			}

			else if (Math.abs(playerPos.x - demonPos.x) <= (4 * distance)) {

				setInFrustum(true);
				status.setCurrentMode("walk");
			}

			else {
				setInFrustum(false);
			}

			if (status.isHit() && status.getCurrentMode().equals("attack")) {

				if (player.status.getHealth() - 1.0 * damageScale <= 0.0f) {

					player.status.setHealth(0.0f);
					player.status.setDied(true);

					player.status.setCurrentMode("death");

					PlayScreen.healthBar.setValue(player.status.getHealth() / 100.0f);

					PlayScreen.endGameTime = System.currentTimeMillis();

					return;
				}

				player.status.addHealth(-1.0f * damageScale);
				PlayScreen.healthBar.setValue(player.status.getHealth() / 100.0f);

			}
		}

	}

	@Override
	public void update(boolean deltaTimeScale) {

		if (!isDied()) {
			float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() + 1.0f : 1.0f;

			float xStep = 0.0f;

			float weaponXStep = 50.0f;
			float weaponYStep = 55.0f;

			if (status.getCurrentMode().equals("attack")) {

			}

			else if (status.getDir() == 1 && getSteps() >= forthStep) {

				setSteps(0.0f);
				status.setDir(-1);
			}

			else if (status.getDir() == -1 && getSteps() >= backStep) {

				setSteps(0.0f);
				status.setDir(1);
			}

			else if (inFrustum) {

				xStep = status.getDir() * status.getInitialVelocityX() * scale;
				status.setCurrentMode("walk");
				addSteps(xStep);
			}

			else if (!status.getCurrentMode().equals("attack")) {
				// do something
			}

			// Actor
			Vector2 actorPos = actor.getPosition();

			Vector2 rWeaponMaskPos = new Vector2(actor.getPosition());
			Vector2 lWeaponMaskPos = new Vector2(actor.getPosition());

			rWeaponMaskPos.y -= (this.getTileHeight() - 130.0f) / (2 * World2D.GU);
			lWeaponMaskPos.y -= (this.getTileHeight() - 130.0f) / (2 * World2D.GU);

			actorPos.x += xStep / World2D.GU;

			actor.setTransform(actorPos, 0.0f);

			rWeaponMaskPos.x += weaponXStep / World2D.GU;
			rWeaponMaskPos.y += weaponYStep / World2D.GU;

			lWeaponMaskPos.x -= weaponXStep / World2D.GU;
			lWeaponMaskPos.y += weaponYStep / World2D.GU;

			this.weaponMaskR.setTransform(rWeaponMaskPos, 0.0f);
			this.weaponMaskL.setTransform(lWeaponMaskPos, 0.0f);

			this.actor.applyForceToCenter(0.0f, -9.81f, true); // Y-axis

		}
	}

	/**
	 * @return the steps
	 */
	public float getSteps() {
		return Math.abs(steps);
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(float steps) {
		this.steps = steps;
	}

	/**
	 * @param steps the steps to add
	 */
	public void addSteps(float steps) {
		this.steps += steps;
	}

}
