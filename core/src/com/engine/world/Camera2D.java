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

	private void debugMode() {

		float width = this.viewportWidth;
		float height = this.viewportHeight;

		float x = this.position.x - width / 2;
		float y = this.position.y - height / 2;

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setProjectionMatrix(this.combined);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
	}

	@Override
	public void update() {
		update(true);

		if (World2D.onDebugMode) {
			this.debugMode();
		}
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
