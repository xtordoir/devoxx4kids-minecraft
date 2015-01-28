package org.devoxx4kids;

import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.entity.Projectile;

public class GetShooterHack {
	public static ProjectileSource getShooter(Projectile projectile) {
		return(projectile.getShooter());
	}
}