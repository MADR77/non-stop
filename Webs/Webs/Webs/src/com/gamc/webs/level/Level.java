package com.gamc.webs.level;

import java.util.ArrayList;
import java.util.List;

import com.gamc.webs.entity.Entity;
import com.gamc.webs.entity.projectile.Projectile;
import com.gamc.webs.graphics.Screen;
import com.gamc.webs.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

	public static Level spawnLevel = new SpawnLevel("/levels/spawnLevel.png");
	public static Level menuLevel = spawnLevel;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {

	}

	protected void loadLevel(String path) {

	}

	public boolean tileCollision(double x, double y, double nx, double ny,
			int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int) x + (int) nx) + c % 2 * size / 2 - 5) / 16;
			int yt = (((int) y + (int) ny) + c / 2 * size / 2 + 5) / 16;
			if (getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < getProjectiles().size(); i++) {
			Projectile p = getProjectiles().get(i);
			if (p.isRemoved())
				getProjectiles().remove(i);
		}
		for (int i = 0; i < getEntities().size(); i++) {
			Entity e = getEntities().get(i);
			if (e.isRemoved())
				getEntities().remove(i);
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	@SuppressWarnings("unused")
	private void time() {

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void addProjectile(Projectile projectile) {
		if (projectile.level == null)
			projectile.init(this);
		projectiles.add(projectile);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		switch (tiles[x + y * width]) {
		case 0xFF00FF00:
			return Tile.grass;
		case 0xFFFF0000:
			return Tile.redBrick;
		}
		return Tile.voidTile;
	}

}
