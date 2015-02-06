package com.gamc.webs.entity.projectile;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;

public class ProjectileStrongMagic extends Projectile {

	public static final int rateOfFire = 15;

	public ProjectileStrongMagic(int x, int y, double dir) {
		super(x, y, dir);
		range = 50;
		speed = 3;
		damage = 25;
		sprite = Sprite.fireball;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
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
