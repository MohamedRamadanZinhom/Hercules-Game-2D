package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.engine.world.World2D;

public class UIResources {

	public static final String pathUI = "./UI/";
	// <<----Game Texture---->>

	public static final String uiSkinPath = pathUI + "uiskin.json";

	public static final Texture MenuBackGround = new Texture(pathUI + "MenuBackGround_2.png");; // image for menu
																								// background
	public static final Texture PlayButton_Active = new Texture(pathUI + "002-rocket-1.png"); // image for play button
																								// when it activated
	public static final Texture SettingButton_Active = new Texture(pathUI + "006-electrical-service.png"); // image for
																											// Setting
																											// button
																											// when it
																											// activated

	public static final Texture PlayButton_UnActive = new Texture(pathUI + "001-rocket.png"); // image for play button
																								// when it un activated
	public static final Texture SettingButton_UnActive = new Texture(pathUI + "005-support.png"); // image for setting
																									// button when it un
																									// activated

	public static final Texture Audio_Activated = new Texture(pathUI + "010-music-player-1.png"); // image for Activated
																									// audio button
	public static final Texture Audio_UnActivated = new Texture(pathUI + "009-music-player.png"); // image for un
																									// Activated audio
																									// button

	public static final Texture Exit_Activated = new Texture(pathUI + "001-power-button.png"); // image for Activated
																								// Exit button
	public static final Texture Exit_UnActivated = new Texture(pathUI + "002-power.png"); // image for un Activated Exit
																							// button

	// <<----End sub ---->>

	// <<----Button Bounds ---->>

	/* << Play Button >> */
	public static final float play_button_width = 50;
	public static final float play_button_height = 50;

	public static final float play_button_X = World2D.SCREEN_WIDTH / 2 - 150.0f;
	public static final float play_button_Y = World2D.SCREEN_HEIGHT / 2;
	/* <<---------------------------------------------->> */

	/* << Setting Button >> */
	public static final float Setting_button_width = 50;
	public static final float Setting_button_height = 50;

	public static final float Setting_button_X = World2D.SCREEN_WIDTH / 2 - 150.0f;
	public static final float Setting_button_Y = World2D.SCREEN_HEIGHT / 2 - 75;
	/* <<---------------------------------------------->> */

	/* << Sounds Button >> */
	public static final float Sounds_button_width = 50;
	public static final float Sounds_button_height = 50;

	public static final float Sounds_button_X = World2D.SCREEN_WIDTH / 2 - 150.0f;
	public static final float Sounds_button_Y = World2D.SCREEN_HEIGHT - 50;
	/* <<---------------------------------------------->> */

	/* << Exit Button >> */
	public static final float Exit_button_width = 50;
	public static final float Exit_button_height = 50;

	public static final float Exit_button_X = World2D.SCREEN_WIDTH / 2 - 150.0f;
	public static final float Exit_button_Y = World2D.SCREEN_HEIGHT / 2 - 150.0f;
	/* <<---------------------------------------------->> */

	// <<----End sub---->>

	// <<----Sounds Files---->>
	public static int Volume = 100;
	public static final GameMusic sound = new GameMusic(
			Gdx.files.internal(pathUI + "Victory - AShamaluevMusic.mp3").toString());

	// <<---- End Sub ---->>

}
