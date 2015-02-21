package com.gamc.webs.sound;

public enum SoundType {

	MUSIC_THEME("/sounds/music/main_menu.wav"),
	MUSIC_CASTLE("/sounds/music/castle.wav"),
	MUSIC_JUNGLE("/sounds/music/jungle.wav"),
	
	SOUND_FIREBALL_CAST("/sounds/player/fireball_cast.wav"),
	SOUND_FIREBALL_EXPLODE("/sounds/player/fireball_explode.wav");
	
	private final String soundFile;
	
	private SoundType(String soundFile) {
		this.soundFile = soundFile;
	}
	
	public String getSoundFile(){
		return soundFile;
	}
	
}
