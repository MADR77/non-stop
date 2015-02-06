package com.gamc.webs.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	// Tiles
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite redBrick = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite fence = new Sprite(16, 6, 0, SpriteSheet.tiles);
	// Void sprite
	public static Sprite voidSprite = new Sprite(16, 0x5FE3D1);
	
	// Projectiles
	public static Sprite fireball = new Sprite(16, 0, 0, SpriteSheet.projectiles);
	
	// Players
	public static Sprite player_back = new Sprite(32, 1, 0, SpriteSheet.players);
	public static Sprite player_back_1 = new Sprite(32, 0, 0, SpriteSheet.players);
	public static Sprite player_back_2 = new Sprite(32, 2, 0, SpriteSheet.players);
	public static Sprite player_left = new Sprite(32, 1, 1, SpriteSheet.players);
	public static Sprite player_left_1 = new Sprite(32, 0, 1, SpriteSheet.players);
	public static Sprite player_left_2 = new Sprite(32, 2, 1, SpriteSheet.players);
	public static Sprite player_right = new Sprite(32, 1, 2, SpriteSheet.players);
	public static Sprite player_right_1 = new Sprite(32, 0, 2, SpriteSheet.players);
	public static Sprite player_right_2 = new Sprite(32, 2, 2, SpriteSheet.players);
	public static Sprite player_forward = new Sprite(32, 1, 3, SpriteSheet.players);
	public static Sprite player_forward_1 = new Sprite(32, 0, 3, SpriteSheet.players);
	public static Sprite player_forward_2 = new Sprite(32, 2, 3, SpriteSheet.players);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.SIZE = size;
		this.pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite (int size, int color){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for(int i = 0; i < SIZE * SIZE; i++){
			pixels[i] = color;
		}
	}

	private void load(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
}
