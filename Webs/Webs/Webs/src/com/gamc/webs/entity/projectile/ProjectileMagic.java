package com.gamc.webs.entity.projectile;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;

public class ProjectileMagic extends Projectile {

	public static final int rateOfFire = 15;

	public ProjectileMagic(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(50) + 75;
		speed = 3;
		damage = 5;
		sprite = Sprite.redBrick;
		nx = ((random.nextDouble() - 0.5) / 2) + (speed * Math.cos(angle));
		ny = ((random.nextDouble() - 0.5) / 2) + (speed * Math.sin(angle));
	}

	public void update() {
		if (level.tileCollision(x, y, nx, ny, 16))
			remove();
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range)
			remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y)
				* (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, sprite);
	}

}
