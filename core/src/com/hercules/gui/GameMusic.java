package com.hercules.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameMusic {

	private final String Path;
	private final Music music;

	public GameMusic(String path) {

		this.Path = path;
		this.music = Gdx.audio.newMusic(Gdx.files.internal(this.Path));

	}

	public void Music_Play(float Volume) {
		music.setLooping(true);
		music.setVolume(Volume);
		music.play();
	}

	public void Music_Stop() {
		music.setLooping(false);
		music.stop();
	}

	public void Music_Pause() {
		music.setLooping(false);
		music.pause();
	}

	public Music getsound() {
		return this.music;

	}

}
