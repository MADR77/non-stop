package com.gamc.webs.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet players = new SpriteSheet("/textures/players/players.png", 256);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/entities/projectiles/projectiles.png", 256);
	public static SpriteSheet mobs = new SpriteSheet("/textures/entities/mobs/mobs.png", 256);
	public static SpriteSheet particles = new SpriteSheet("/textures/entities/particles/particles.png", 256);
	public static SpriteSheet buildings = new SpriteSheet("/textures/tiles/buildings.png", 256);
	
	
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to load " + path);
		}
	}
	
}
