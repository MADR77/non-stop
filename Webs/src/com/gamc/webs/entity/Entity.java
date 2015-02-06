package com.gamc.webs.entity;

import java.util.Random;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.level.Level;

public class Entity {

	public int x, y;
	private boolean removed = false;
	public Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void init(Level level){
		this.level = level;
	}
	
}
