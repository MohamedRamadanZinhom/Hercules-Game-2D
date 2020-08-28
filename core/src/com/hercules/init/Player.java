/** @author Z. Mohamed Osama */

package com.hercules.init;

import java.security.KeyException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.engine.world.Body2D;
import com.engine.world.World2D;
import com.hercules.game.GameAdapter;
import com.hercules.game.GameLevel;
import com.hercules.game.PlayScreen;

public class Player extends Character {

	private static final Resource res = ResourceManager.getPlayerResources_2();

	/**
	 * @param spritesDirname: String [][] - 2d array, each dir specifies, array of
	 *                        sprite sheets to create an animation key from it
	 * 
	 * @param name:           String - Player Id
	 * @param posX:           float - Player initial position - X
	 * @param posY:           float - Player initial position - Y
	 * @param speed:          float - Player initial Speed
	 * @param FPS_SCALE:      float - SCALE Animtion Frames
	 * @param frameDuration:  float
	 * 
	 * @param defaultdir      : int - Initial sprite sheet dir (ex. left, right)
	 *                        {@link #path}
	 * 
	 * @param currentMode     : String - Initial animation key
	 */
	public Player(String name, CharacterStatus status) {

		super("player", "sword", res.spritesDirname, res.FPS_SCALE, res.frameDuration, status, false);

	}

	@Override
	public void initActor(World2D world) {

		initCharacter(res.FRAME_ROWS, res.FRAME_COLS, res.startKeys, res.endKeys, res.typeKeys, res.keysOrder);

		// Player Actor
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(40.0f / World2D.GU, 75.0f / World2D.GU);

		world.createDynamicBody(shape, 0.0f, 0.0f, 1.0f, World.BIT_PLAYER, World.BIT_ANY, false, typeId);

		float x = status.getStartingPosX() / World2D.GU + getTileWidth() / (2 * World2D.GU);
		float y = status.getStartingPosY() / World2D.GU + getTileHeight() / (2 * World2D.GU);

		actor = Body2D.bodies.get(typeId).get(0);
		actor.setTransform(new Vector2(x, y), 0.0f);

		// Player swordR
		PolygonShape swordShapeR = new PolygonShape();
		swordShapeR.setAsBox(5.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(swordShapeR, 0.0f, 0.0f, 0.0f, World.BIT_PLAYER, World.BIT_ENEMY_T1, true,
				getWeaponId());

		weaponMaskR = Body2D.bodies.get(getWeaponId()).get(0);
		weaponMaskR.setTransform(new Vector2(x + 0.5f, y + 0.5f), 0.0f);

		// Player swordL
		PolygonShape swordShapeL = new PolygonShape();
		swordShapeL.setAsBox(5.0f / World2D.GU, 5.0f / World2D.GU);

		world.createDynamicBody(swordShapeL, 0.0f, 0.0f, 0.0f, World.BIT_PLAYER, World.BIT_ENEMY_T1, true,
				getWeaponId());

		weaponMaskL = Body2D.bodies.get(getWeaponId()).get(1);
		weaponMaskL.setTransform(new Vector2(x - 0.5f, y + 0.5f), 0.0f);

	}

	@Override
	public void animate(float thresholdX, float thresholdY) {

		float damageScale = Gdx.graphics.getDeltaTime() + 1.0f;

		float x = this.getPosX() * World2D.GU - this.getTileWidth() / 2 + thresholdX;
		float y = this.getPosY() * World2D.GU - this.getTileHeight() / 2 + thresholdY;

		this.animator[this.status.isDirRight()].getSpriteBatch().setProjectionMatrix(GameLevel.envCam.combined);

		if (!isDied()) {

			boolean inFrustum = GameLevel.envCam.frustum.boundsInFrustum(x + 256.0f / 2, 0.0f, 0.0f, 0.0f,
					GameAdapter.V_HEIGHT, 0.0f);

			if (!inFrustum) {

				GameLevel.envCam.position.x += GameAdapter.V_WIDTH * status.getDir();
				GameLevel.box2dCam.position.x += (GameAdapter.V_WIDTH * status.getDir()) / World2D.GU;

				GameLevel.envCam.update();
				GameLevel.box2dCam.update();
			}

			if (!World2D.onPhysicsDebugMode) {

				// Animate

				try {

					this.animator[this.status.isDirRight()].animate(status.getCurrentMode(), x, y, res.scale,
							this.FPS_SCALE);

				} catch (KeyException error) {

					error.printStackTrace();
				}

				if (status.isHit() && (status.getCurrentMode().equals("attack")
						|| (FPS_SCALE.containsKey("jump-attack") && status.getCurrentMode().equals("attack")))) {

					for (String key : CharacterStatus.statusRepo.keySet()) {

						if (key == "player") {

							continue;
						}

						CharacterStatus enemyStatus = CharacterStatus.statusRepo.get(key);

						if (enemyStatus.getHealth() - 1.0 * damageScale <= 0.0f) {

							enemyStatus.setHealth(0.0f);

							enemyStatus.setCurrentMode("death");

							enemyStatus.setDied(true);
						}

						if (FPS_SCALE.containsKey("hurt")) {

							enemyStatus.setCurrentMode("hurt");
						}

						enemyStatus.addHealth(-1.0f * damageScale);

					}

				}

				if (status.isOnGround() && !status.getCurrentMode().equals("hurt")) {

					status.setCurrentMode("idle");
				}
			}
		}

		else { // Death

			if (!World2D.onPhysicsDebugMode) {

				float deathDuration = animator[this.status.isDirRight()].getAnimationDuration("death");

				// Animate

				try {

					this.animator[this.status.isDirRight()].animate(status.getCurrentMode(), x, y, res.scale,
							this.FPS_SCALE);

				} catch (KeyException error) {

					error.printStackTrace();
				}

				if (System.currentTimeMillis() - PlayScreen.endGameTime >= deathDuration * 5.0f) {

					Gdx.app.exit();
				}
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

		if (!isDied()) {

			float scale = deltaTimeScale ? Gdx.graphics.getDeltaTime() + 1.0f : 1.0f;

			float xStep = 0.0f; // speed * scale
			float yStep = 0.0f; // 10.0f * scale;

			float weaponXStep = 55.0f;
			float weaponYStep = 50.0f;

			// Actor
			Vector2 actorPos = actor.getPosition();

			// Sword
			Vector2 rWeaponMaskPos = new Vector2(actor.getPosition());
			Vector2 lWeaponMaskPos = new Vector2(actor.getPosition());

			rWeaponMaskPos.y -= (this.getTileHeight() - 130.0f) / (2 * World2D.GU);
			lWeaponMaskPos.y -= (this.getTileHeight() - 130.0f) / (2 * World2D.GU);

			// Previous Position (Actor & Sword)
			Vector2 actorPrevPos = new Vector2(actor.getPosition()); // copy

			Vector2 rWeaponPrevPos = new Vector2(this.weaponMaskR.getPosition()); // copy
			Vector2 lWeaponPrevPos = new Vector2(this.weaponMaskL.getPosition()); // copy

			if (!status.isOnBound()) {

				if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

					xStep = status.getInitialVelocityX() * scale;

					if (status.isOnGround()) {

						status.setCurrentMode("walk");
					}

					status.setDir(1);
				}

				if (Gdx.input.isKeyPressed(Keys.LEFT)) {

					xStep = -status.getInitialVelocityX() * scale;

					if (status.isOnGround()) {

						status.setCurrentMode("walk");
					}

					status.setDir(-1);
				}

				if (Gdx.input.isKeyPressed(Keys.UP) && status.isOnGround()) {

					yStep = status.getJumpScale() * scale;
					status.setCurrentMode("jump");
				}

				if (Gdx.input.isKeyPressed(Keys.SPACE)) {
					// Attack

					status.setCurrentMode("attack");

					// do somthing
				}

				if (Gdx.input.isKeyPressed(Keys.SPACE) && !status.isOnGround()) {

					if (this.FPS_SCALE.containsKey("jump_attack")) {

						status.setCurrentMode("jump_attack");

					} else {

						status.setCurrentMode("attack");

					}
				}

				if (Gdx.input.isKeyPressed(Keys.DOWN) && !status.isOnGround()) {

					// Smashing
					xStep += status.getDir() * status.getSmashingScale() * scale;
					yStep = -status.getSmashingScale() * scale;

					if (this.FPS_SCALE.containsKey("jump_attack")) {

						status.setCurrentMode("jump_attack");

					} else {

						status.setCurrentMode("attack");

					}
				}

				if (Gdx.input.isKeyPressed(Keys.RIGHT)
						&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

					xStep += status.getRunScale();

					status.setCurrentMode("run");
					status.setDir(1);
				}

				if (Gdx.input.isKeyPressed(Keys.LEFT)
						&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))) {

					xStep -= status.getRunScale();

					status.setCurrentMode("run");
					status.setDir(-1);
				}

				if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {

					xStep *= 0.4f;

					status.setCurrentMode("walk");
				}

				actorPos.x += xStep / World2D.GU;

				actor.setTransform(actorPos, 0.0f);

				rWeaponMaskPos.x += weaponXStep / World2D.GU;
				rWeaponMaskPos.y += weaponYStep / World2D.GU;

				lWeaponMaskPos.x -= weaponXStep / World2D.GU;
				lWeaponMaskPos.y += weaponYStep / World2D.GU;

				this.weaponMaskR.setTransform(rWeaponMaskPos, 0.0f);
				this.weaponMaskL.setTransform(lWeaponMaskPos, 0.0f);

				this.actor.applyForceToCenter(0.0f, yStep, true); // Y-axis

			}

			else {

				actorPrevPos.x += -this.status.getDir() * (actorPrevPos.x * 0.1);

				rWeaponPrevPos.x += -this.status.getDir() * (rWeaponPrevPos.x * 0.01);
				lWeaponPrevPos.x += -this.status.getDir() * (rWeaponPrevPos.x * 0.01);

				actor.setTransform(actorPrevPos, 0.0f); // Set - Previous Position

				weaponMaskR.setTransform(rWeaponPrevPos, 0.0f); // Set - Previous Position
				weaponMaskL.setTransform(lWeaponPrevPos, 0.0f); // Set - Previous Position

			}

		}

	}

}
