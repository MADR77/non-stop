package com.gamc.webs.level.tile;

import com.gamc.webs.graphics.Sprite;

public class TileSolid extends Tile {

	public TileSolid(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}

}
