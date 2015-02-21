package com.gamc.webs.entity.projectile;

import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;
import com.gamc.webs.input.Mouse;
import com.gamc.webs.sound.Clips;
import com.gamc.webs.sound.SoundManager;

public class ProjectileSpecial extends Projectile {

	public static final int rateOfFire = 60;
	protected final double mouseX, mouseY;

	public ProjectileSpecial(int x, int y, double dir) {
		super(x, y, dir);
		range = 100;
		speed = 1;
		damage = 15;
		sprite = Sprite.fireball;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		SoundManager.playSound(Clips.fireball_cast);
	}

	public void update() {
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range)
			explode();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y)
				* (yOrigin - y)));
		return dist;
	}

	public void explode() {
		SoundManager.playSound(Clips.fireball_explode);
		remove();
		for (int i = 0; i < 30; i++) {
			Projectile projectile = new ProjectileStrongMagic((int) x, (int) y, random.nextInt(10) + i);
			level.addProjectile(projectile);
		}
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, sprite);
	}

}
