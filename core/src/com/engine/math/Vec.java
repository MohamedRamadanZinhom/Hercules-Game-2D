/** @author Z. Mohamed Osama */

package com.engine.math;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Vec {

	public static Vector2 div2D(Vector2 vec, float divisor) {

		vec.x /= divisor;
		vec.y /= divisor;

		return vec;
	}

	public static Vector2 getTransformedCenterForRectangle(Rectangle rectangle, float width, float height,
			float scale) {

		Vector2 center = new Vector2();
		rectangle.getCenter(center);

		center.x /= width * scale;
		center.y /= height * scale;

		return center;
	}
}
