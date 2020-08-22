package com.hercules.init;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class EnemyStatus extends CharacterStatus {

	public EnemyStatus(float startingPosX, float startingPosY, float velocityX, float runScale, float jumpScale,
			float smashingScale, int dir, String currentMode, float health, boolean died) {

		super(startingPosX, startingPosY, velocityX, runScale, jumpScale, smashingScale, dir, currentMode, health,
				died);
	}

	public EnemyStatus(float startingPosX, float startingPosY, float velocityX, float velocityY, float runScale,
			float jumpScale, float smashingScale, int dir, String currentMode, float health, boolean died) {

		super(startingPosX, startingPosY, velocityX, velocityY, runScale, jumpScale, smashingScale, dir, currentMode,
				health, died);
	}

	public EnemyStatus(Vector2 startingPos, Vector2 initialVelocity, float runScale, float jumpScale,
			float smashingScale, int dir, String currentMode, float health, boolean died) {

		super(startingPos, initialVelocity, runScale, jumpScale, smashingScale, dir, currentMode, health, died);

	}

	public EnemyStatus(Vector2 startingPos, Vector2 initialVelocity, Vector3 movementScale, int dir, String currentMode,
			float health, boolean died) {

		super(startingPos, initialVelocity, movementScale, dir, currentMode, health, died);
	}

	public Vector2 getRandomStart(float boundX, float boundY, StatusConstraint constraints) {

		Vector2 startPos = null;

		return startPos;
	}

	public void setRandomStart(float boundX, float boundY, StatusConstraint constraints) {

		// Overwrite, Character Starting Position
	}

	public CharacterStatus getRandomStatus(float boundX, float boundY, StatusConstraint constraints) {

		CharacterStatus status = null;

		return status;
	}

	public void setRandomStatus(float boundX, float boundY, StatusConstraint constraints) {

		// Overwrite, Character Status

	}

	public CharacterStatus getAttackStatus(float boundX, float boundY, StatusConstraint constraints) {

		CharacterStatus status = null;

		return status;
	}

	public void setAttackStatus(float boundX, float boundY, StatusConstraint constraints) {

		// Overwrite, Character Status

	}

	@SuppressWarnings("unused")
	private Vector2 getRandomVec2(float boundX, float boundY) {

		Vector2 vec2 = null;

		return vec2;
	}
}
