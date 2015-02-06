package com.gamc.webs.sound;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	public static ArrayList<SoundManager> loopingSounds = new ArrayList<SoundManager>();

	private URL soundURL;
	private Clip clip;

	public SoundManager(SoundType soundFile) {
		soundURL = getClass().getResource(soundFile.getSoundFile());
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL);
			clip = AudioSystem.getClip();
			clip.open(sound);
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
			System.out.println("Tried to access file at " + soundURL);
			System.out.println("Failed as this happened: " + e);
		}
	}

	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}

	public void pause() {
		clip.stop();
	}

	public void unpause() {
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		if (!SoundManager.loopingSounds.contains(this))
			SoundManager.loopingSounds.add(this);
	}

	public void stop() {
		clip.stop();
		clip.flush();
		clip.close();
		if (SoundManager.loopingSounds.contains(this))
			SoundManager.loopingSounds.remove(this);
	}

	public Clip getClip() {
		return clip;
	}

	public static void exitLoops() {
		for (SoundManager sm : SoundManager.loopingSounds) {
			Clip clip = sm.getClip();
			clip.stop();
			clip.flush();
			clip.close();
		}
	}

	public static void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
	}

}