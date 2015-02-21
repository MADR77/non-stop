package com.gamc.webs.entity.projectile;

import java.util.Random;

import com.gamc.webs.entity.Entity;
import com.gamc.webs.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected final Random random = new Random();
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double speed, range, damage;

	public Projectile(int x, int y, double dir) {
		this.xOrigin = x;
		this.yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

}
