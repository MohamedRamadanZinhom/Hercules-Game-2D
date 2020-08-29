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

	public void play(float Volume) {

		music.setLooping(true);
		music.setVolume(Volume);
		music.play();
	}

	public void stop() {
		music.setLooping(false);
		music.stop();
	}

	public void pause() {

		music.setLooping(false);
		music.pause();
	}

	public Music getsound() {

		return music;

	}
}
