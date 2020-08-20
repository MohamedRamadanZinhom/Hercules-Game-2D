package com.engine.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Camera2D extends OrthographicCamera {

	private static ShapeRenderer shapeRenderer = new ShapeRenderer();

	public Camera2D() {
		super();
	}

	public Camera2D(float viewportWidth, float viewportHeight) {
		super(viewportWidth, viewportHeight);
	}

	public static void debugMode(Camera2D camera) {

		float width = camera.viewportWidth;
		float height = camera.viewportHeight;

		float x = camera.position.x - width / 2;
		float y = camera.position.y - height / 2;

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
	}

	public static void boundCamera(Camera2D camera, float startX, float startY, float width, float height) {

		Vector3 cameraPos = camera.position;

		if (cameraPos.x < startX) {

			cameraPos.x = startX;
		}

		if (cameraPos.y < startY) {

			cameraPos.y = startY;
		}

		if (cameraPos.x > startX + width) {

			cameraPos.x = startX + width;
		}

		if (cameraPos.y > startY + height) {

			cameraPos.y = startY + height;
		}

		camera.position.set(cameraPos);
		camera.update();
	}
}
