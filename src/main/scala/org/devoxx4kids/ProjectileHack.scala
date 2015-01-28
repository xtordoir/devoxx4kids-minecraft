package org.devoxx4kids


import org.bukkit.projectiles.ProjectileSource
import org.bukkit.entity.Projectile

case class ProjectileHack(projectile: Projectile) {
	def getTheShooter(): ProjectileSource = GetShooterHack.getShooter(this.projectile)
}


object ProjectileHack {
	implicit def fromProjectile(projectile: Projectile): ProjectileHack = ProjectileHack(projectile)
	implicit def toProjectile(projectileHack: ProjectileHack): Projectile = projectileHack.projectile
}
