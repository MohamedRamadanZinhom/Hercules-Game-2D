package com.engine.animation;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engine.exception.OverwriteException;

// AnimationGenerator : handle only horizontal sprite sheets
public class AnimationGenerator {

	static protected float stateTime;

	protected HashMap<String, Animation<TextureRegion>> animationRepo;

	protected SpriteBatch spriteBatch;

	protected Texture[] spriteSheet;

	public AnimationGenerator(String[] path) {

		animationRepo = new HashMap<String, Animation<TextureRegion>>();
		spriteBatch = new SpriteBatch();
		spriteSheet = new Texture[path.length];

		for (int i = 0; i < path.length; i++) {
			spriteSheet[i] = new Texture(path[i]);
		}

		stateTime = 0.0f;
	}

	public void addAnimation(int ith, int FRAME_ROWS, int FRAME_COLS, float frameDuration, int[] startKeys,
			int[] endKeys, String[] typeKeys, String keysOrder) throws OverwriteException {

		/**
		 * Create animation, of given sprite sheet, then put it inside the animation
		 * repository.
		 * 
		 * @param ith           : int - Character sprite index
		 * @param FRAME_ROWS    : int - Sprite chunk step size (width)
		 * @param FRAME_COLS    : int - Sprite chunk step size (height)
		 * @param frameDuration : float
		 * @param nFrame:       int - Number of horizontal frames.
		 * @param startKeys:    [] int - array contains start key frame, for each
		 *                      animation
		 * @param endKeys:      [] int - array contains start key frame, for each
		 *                      animation
		 * @param typeKeys:     String - Animation type at ith key, i.e. idle, walk,
		 *                      running
		 * @param keysOrder:    Order of set keyes, "left", "right"
		 * 
		 * @throws HashMapOverwrite if Animation id (key) already exists in the maps
		 *                          repository.
		 */

		int tileWidth = spriteSheet[ith].getWidth() / FRAME_COLS;
		int tileHeight = spriteSheet[ith].getHeight() / FRAME_ROWS;

		TextureRegion[][] temp = TextureRegion.split(spriteSheet[ith], tileWidth, tileHeight);

		if (keysOrder == "right") {
			for (int i = 0; i < startKeys.length; i++) {

				int start = startKeys[i];
				int end = endKeys[i];
				String type = typeKeys[i];

				TextureRegion[] frames = new TextureRegion[end - start];

				// Check if key (name) exist
				if (animationRepo.containsKey(type)) {
					throw new OverwriteException(
							"Animation mode " + type + " already exist, check typeKeys, for repeated types.");
				}

				else {

					for (int j = start; j < end; j++) {
						frames[j - start] = temp[0][j];
					}

					Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, frames);

					// Put type inside the repository HashMap<String, Animation<TextureRegion>>.
					animationRepo.put(type, animation);
				}
			}
		}

		else if (keysOrder == "left") {

			for (int i = 0; i < startKeys.length; i++) {

				int start = startKeys[i];
				int end = endKeys[i] + 1;
				String type = typeKeys[i];

				TextureRegion[] frames = new TextureRegion[end - start];

				// Check if key (name) exist
				if (animationRepo.containsKey(type)) {
					throw new OverwriteException(
							"Animation mode " + type + " already exist, check typeKeys, for repeated types.");
				}

				else {

					for (int j = start; j < end; j++) {
						frames[j - start] = temp[0][(FRAME_COLS - 1) - j];
					}

					Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, frames);

					// Put type inside the repository HashMap<String, Animation<TextureRegion>>.
					animationRepo.put(type, animation);
				}
			}
		}

		else {
			throw new IllegalArgumentException("Invalid order, order must be, left or right");
		}
	}

	public void animate(String mode, float x, float y, float FPS_SCALE) {

		stateTime += Gdx.graphics.getDeltaTime() * FPS_SCALE;
		TextureRegion currentFrame = animationRepo.get(mode).getKeyFrame(stateTime, true);

		spriteBatch.begin();
		spriteBatch.draw(currentFrame, x, y);
		spriteBatch.end();
	}

	/**
	 * Dispose, SpriteBatch, Texture
	 */
	public void dispose() {

		for (int i = 0; i < spriteSheet.length; i++) {
			spriteSheet[i].dispose();
		}

		spriteBatch.dispose();
		animationRepo.clear();
	}

}
