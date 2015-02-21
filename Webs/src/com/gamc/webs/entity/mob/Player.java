package com.gamc.webs.entity.mob;

import com.gamc.webs.Webs;
import com.gamc.webs.entity.projectile.Projectile;
import com.gamc.webs.entity.projectile.ProjectileMagic;
import com.gamc.webs.entity.projectile.ProjectileSpecial;
import com.gamc.webs.graphics.Screen;
import com.gamc.webs.graphics.Sprite;
import com.gamc.webs.input.KeyInput;
import com.gamc.webs.input.Mouse;

public class Player extends Mob {

	private KeyInput input;
	private Sprite sprite;
	private int anim = 0, fired = 0, firedSpecial = 0;;
	private boolean walking = false, canShoot = true, canShootSpecial = true;
	private final String username;

	public Player(int x, int y, KeyInput input, String username) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.username = this.username == null ? "Jeffery" : username;
	}
	
	public String getUsername() {
		return username;
	}

	public void update() {
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
		updateShooting();
	}

	private void updateShooting() {
		if (!canShoot) {
			fired++;
			if (fired >= ProjectileMagic.rateOfFire) {
				canShoot = true;
			}
		} else {
			if (Mouse.getButton() == 1) {
				double dx = Mouse.getX() - Webs.getGame().getWindowWidth() / 2;
				double dy = Mouse.getY() - Webs.getGame().getWindowHeight() / 2;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir);
				canShoot = false;
				fired = 0;
			}
		}
		if (!canShootSpecial) {
			firedSpecial++;
			if (firedSpecial >= ProjectileSpecial.rateOfFire) {
				canShootSpecial = true;
			}
		} else {
			if (input.space) {
				double dx = Mouse.getX() - Webs.getGame().getWindowWidth() / 2;
				double dy = Mouse.getY() - Webs.getGame().getWindowHeight() / 2;
				double dir = Math.atan2(dy, dx);
				shootSpecial(x, y, dir);
				canShootSpecial = false;
				firedSpecial = 0;
			}
		}
	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new ProjectileMagic(x, y, dir);
		level.getProjectiles().add(p);
		level.addProjectile(p);
	}
	
	protected void shootSpecial(int x, int y, double dir) {
		Projectile p = new ProjectileSpecial(x, y, dir);
		level.getProjectiles().add(p);
		level.addProjectile(p);
	}

	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_right_1;
				} else {
					sprite = Sprite.player_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		screen.renderPlayer(x - 8, y - 8, sprite);
	}

}
