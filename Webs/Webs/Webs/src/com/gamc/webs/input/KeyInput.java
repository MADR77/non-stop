package com.gamc.webs.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gamc.webs.Webs;
import com.gamc.webs.Webs.ScreenState;
import com.gamc.webs.util.MenuSelection;
import com.gamc.webs.util.MenuSelection.SelectedMenuOption;
import com.gamc.webs.util.PauseSelection;
import com.gamc.webs.util.PauseSelection.PausedMenuOption;

public class KeyInput implements KeyListener {

	private boolean[] keys = new boolean[300];
	public boolean up, down, left, right, space;
	public boolean fullscreen = false;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];

		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {

			}
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		updateAllTheKeys(e);
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	private void updateAllTheKeys(KeyEvent e) {
		// Main Menu Stuff
		if (Webs.getGame().state == ScreenState.MAIN_MENU) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				switch (MenuSelection.selection) {
				case SINGLEPLAYER:
					Webs.getGame().state = ScreenState.LEVEL;
					break;
				case MULTIPLAYER:
					break;
				case OPTIONS:
					break;
				case QUIT:
					System.exit(0);
					break;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				switch (MenuSelection.selection) {
				case SINGLEPLAYER:
					MenuSelection.selection = SelectedMenuOption.QUIT;
					return;
				case MULTIPLAYER:
					MenuSelection.selection = SelectedMenuOption.SINGLEPLAYER;
					return;
				case OPTIONS:
					MenuSelection.selection = SelectedMenuOption.MULTIPLAYER;
					return;
				case QUIT:
					MenuSelection.selection = SelectedMenuOption.OPTIONS;
					return;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				switch (MenuSelection.selection) {
				case SINGLEPLAYER:
					MenuSelection.selection = SelectedMenuOption.MULTIPLAYER;
					return;
				case MULTIPLAYER:
					MenuSelection.selection = SelectedMenuOption.OPTIONS;
					return;
				case OPTIONS:
					MenuSelection.selection = SelectedMenuOption.QUIT;
					return;
				case QUIT:
					MenuSelection.selection = SelectedMenuOption.SINGLEPLAYER;
					return;
				}
			}
		} else if (Webs.getGame().state == ScreenState.MAIN_MENU) {
			if (MenuSelection.selection == SelectedMenuOption.SINGLEPLAYER)
				Webs.getGame().state = ScreenState.LEVEL;
			if (MenuSelection.selection == SelectedMenuOption.MULTIPLAYER)
				return;
			if (MenuSelection.selection == SelectedMenuOption.OPTIONS)
				return;
			if (MenuSelection.selection == SelectedMenuOption.QUIT)
				System.exit(0);
		}
		// Paused Menu Stuff
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE
				&& Webs.getGame().state == ScreenState.LEVEL) {
			Webs.getGame().state = ScreenState.PAUSED;
			return;
		}
		if (Webs.getGame().state == ScreenState.PAUSED) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (PauseSelection.selection == PausedMenuOption.BACK_TO_GAME)
					Webs.getGame().state = ScreenState.LEVEL;
				if (PauseSelection.selection == PausedMenuOption.MAIN_MENU) {
					Webs.getGame().state = ScreenState.MAIN_MENU;
				}
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W
				|| e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
				&& Webs.getGame().state == ScreenState.PAUSED) {
			switch (PauseSelection.selection) {
			case BACK_TO_GAME:
				PauseSelection.selection = PausedMenuOption.MAIN_MENU;
				return;
			case MAIN_MENU:
				PauseSelection.selection = PausedMenuOption.BACK_TO_GAME;
				return;
			}
		}

	}
}
