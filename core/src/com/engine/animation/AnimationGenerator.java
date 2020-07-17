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

	static protected HashMap<String, HashMap<String, Animation<TextureRegion>>> animationRepo = new HashMap<String, HashMap<String, Animation<TextureRegion>>>();

	static protected float stateTime;

	protected SpriteBatch spriteBatch;

	protected Texture spriteSheet;

	public AnimationGenerator(String path) {

		spriteBatch = new SpriteBatch();
		spriteSheet = new Texture(path);

		stateTime = 0.0f;
	}

	public void addAnimation(String name, int nFrame, float frameDuration, int[] startKeys, int[] endKeys,
			String[] typeKeys) throws OverwriteException {

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
		 * 
		 * @throws HashMapOverwrite if Animation id (key) already exists in the maps
		 *                          repository.
		 */

		if (animationRepo.containsKey(name)) {
			throw new OverwriteException("Name :" + name + " already exist in the animation repository.");
		} else {

			HashMap<String, Animation<TextureRegion>> animator = 
										new HashMap<String, Animation<TextureRegion>>();

			int pixelStep = spriteSheet.getWidth() / nFrame;
			int tileHeight = spriteSheet.getHeight();

			TextureRegion[][] temp = TextureRegion.split(spriteSheet, pixelStep, tileHeight);

			for (int i = 0; i < startKeys.length; i++) {

				int start = startKeys[i];
				int end = endKeys[i] + 1;
				String type = typeKeys[i];

				TextureRegion[] frames = new TextureRegion[end - start];

				// Check if key (name) exist
				if (animator.containsKey(type)) {
					throw new OverwriteException("For : " + name + "animation type " + type
							+ " already exist, check typeKeys, for repeated types.");
				}

				else {

					for (int j = start; j < end; j++) {
						frames[j - start] = temp[0][j];
					}

					Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, frames);

					// Put name inside the repository HashMap<String, Animation<TextureRegion>>.
					animator.put(type, animation);
				}

				animationRepo.put(name, animator);
			}
		}
	}

	public void animate(String name, String mode, float x, float y) {

		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animationRepo.get(name).get(mode).getKeyFrame(stateTime, true);

		spriteBatch.begin();
		spriteBatch.draw(currentFrame, x, y);
		spriteBatch.end();
	}

	/**
	 * Dispose all TiledMaps and OrthogonalTiledMapRenderer objects located in the
	 * maps repository then clear the repository
	 * 
	 * @param id     : String - The id which used to access specific map.
	 * @param envCam : OrthographicCamera - The camera used to render the TiledMap
	 *               with given id.
	 */
	public void dispose() {

		spriteSheet.dispose();
		spriteBatch.dispose();
	}

	static public void disposeAll() {

		animationRepo.clear();
	}

}
