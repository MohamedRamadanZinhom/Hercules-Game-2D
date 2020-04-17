package com.hercules.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameAdapter extends ApplicationAdapter {
	
	public static final String TITEL = "Hercules"; 
	public static final int V_WIDTH  = 490;
	public static final int V_HEIGHT  = 470;
	public static final int SCALE    = 2;
		
	//////////////////////////////////
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	private OrthographicCamera envCam;
	

		
	@Override
	public void create () {

		//////// Batch ///////////////
		
		spriteBatch = new SpriteBatch();
		
		//////// Camera ////////////
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		envCam = new OrthographicCamera(); 
		envCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		////////////// Map ////////////////

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
		else
		{
			
		}
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		
	}

	
	



}
