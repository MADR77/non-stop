package com.gamc.webs.util;


public class MenuSelection {

	public static SelectedMenuOption selection = SelectedMenuOption.SINGLEPLAYER;

	public enum SelectedMenuOption {
		SINGLEPLAYER, MULTIPLAYER, OPTIONS, QUIT;
	}

}