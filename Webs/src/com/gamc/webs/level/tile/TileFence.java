package com.gamc.webs.level.tile;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;

public class TileFence extends Tile {
	
	private final Sprite sprite2;
	private final boolean isSolid;
	
	public TileFence(Sprite bottomLayer, Sprite topLayer, boolean isSolid) {
		super(bottomLayer);
		this.sprite2 = topLayer;
		this.isSolid = isSolid;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
		screen.renderTile(x << 4, (y << 4) - 7, sprite2);
	}
	
	public boolean solid(){
		return isSolid;
	}

}
