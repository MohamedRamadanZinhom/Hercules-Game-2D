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

	protected Texture spriteSheet;

	public AnimationGenerator(String path) {

		animationRepo = new HashMap<String, Animation<TextureRegion>>();
		spriteBatch = new SpriteBatch();
		spriteSheet = new Texture(path);

		stateTime = 0.0f;
	}

	public void addAnimation(int nFrame, float frameDuration, int[] startKeys, int[] endKeys, String[] typeKeys,
			String keysOrder) throws OverwriteException {

		/**
		 * Create animation, of given sprite sheet, then put it inside the animation
		 * repository.
		 * 
		 * @param name          : String - character name
		 * @param path          : String - The path in which the sprite sheet file (ex.
		 *                      png) located.
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

		int pixelStep = spriteSheet.getWidth() / nFrame;
		int tileHeight = spriteSheet.getHeight();

		TextureRegion[][] temp = TextureRegion.split(spriteSheet, pixelStep, tileHeight);

		if (keysOrder == "right") {
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
						frames[j - start] = temp[0][(nFrame - 1) - j];
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

	public void animate(String mode, float x, float y) {

		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animationRepo.get(mode).getKeyFrame(stateTime, true);

		spriteBatch.begin();
		spriteBatch.draw(currentFrame, x, y);
		spriteBatch.end();
	}

	/**
	 * Dispose, SpriteBatch, Texture
	 */
	public void dispose() {

		spriteSheet.dispose();
		spriteBatch.dispose();

		animationRepo.clear();
	}

}
