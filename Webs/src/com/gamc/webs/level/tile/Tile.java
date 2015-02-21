package com.gamc.webs.level.tile;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new Tile(Sprite.grass);
	public static Tile redBrick = new Tile(Sprite.redBrick);
	public static Tile voidTile = new TileSolid(Sprite.voidSprite);
	public static Tile grassFence = new TileFence(Sprite.grass, Sprite.fence, true);
	public static Tile redBrickFence = new TileFence(Sprite.redBrick, Sprite.fence, true);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}
		
	public boolean solid() {
		return false;
	}
	
}
