package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.exception.InvalideLoaderType;
import com.engine.loader.ObjectsGenerator;

public class GameLauncher extends ApplicationAdapter {
	
	ObjectsGenerator generator;
	SpriteBatch batch;
	Texture enemiesSheet;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		generator = new ObjectsGenerator();
		
		try {
			enemiesSheet =  generator.load("image", "Enemies.png");
		} catch (InvalideLoaderType error) {
			error.printStackTrace();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(enemiesSheet, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		ObjectsGenerator.objectsRepo.clear();
	}
}
