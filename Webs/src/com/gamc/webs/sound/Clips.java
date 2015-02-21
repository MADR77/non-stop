package com.gamc.webs.sound;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Clips {

	public static Clip fireball_explode = getClip(SoundType.SOUND_FIREBALL_EXPLODE);
	public static Clip fireball_cast = getClip(SoundType.SOUND_FIREBALL_CAST);
	
	public Clips() throws LineUnavailableException {
		
		fireball_explode.open();
		fireball_cast.open();
		
	}
	
	private static Clip getClip(SoundType type) {
		URL clipURL = Clips.class.getResource(type.getSoundFile());
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(clipURL);
			Clip clip;
			clip = AudioSystem.getClip();
			clip.open(sound);
			return clip;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Sound: Line Unavailable Exception Error: " + e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tried to access file at " + clipURL);
			System.out.println("Failed as this happened: " + e);
		}
		return null;
	}
	
}
