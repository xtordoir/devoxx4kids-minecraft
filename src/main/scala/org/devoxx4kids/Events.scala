package org.devoxx4kids

import org.devoxx4kids.ProjectileHack._

import scala.util.Try

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler

import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.entity.ProjectileHitEvent

import org.bukkit.util.Vector

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

import org.bukkit.entity.Arrow
import org.bukkit.entity.Projectile
import org.bukkit.projectiles.ProjectileSource

trait Devoxx4kidsEvents {

  val rnd = new scala.util.Random()
  val arrowChance = 0.5
  val followingArrow:Option[Projectile] = None

  def handleProjectileLaunch = (event: ProjectileLaunchEvent) => {

    if (event.getEntityType() == EntityType.ARROW) {  

      val arrow = event.getEntity  
      val location = arrow.getLocation
      
      arrow.getTheShooter match {
        case player: Player if (arrowChance < rnd.nextDouble) => {
          arrow.getWorld.createExplosion(arrow.getLocation, .25f, true)
          player.sendMessage("Explosion!!")
          arrow.remove
        }
        case player: Player => {
          Thread.sleep(100)
          player.setVelocity(arrow.getVelocity)
          player.teleport(arrow.getLocation)
          ()
        }
        case _ => println("hmm who did that?")
      }
    }
       
  }  

  def handleProjectileHit = (event: ProjectileHitEvent) => {

    if (event.getEntityType() == EntityType.ARROW) {  

      val arrow = event.getEntity  
      val location = arrow.getLocation
      
      arrow.getTheShooter match {
        case player: Player => {
          val dist = location.distance(player.getLocation)
          arrow.getWorld.createExplosion(arrow.getLocation, 4.0f, true)
          player.sendMessage("Explosion à :" + dist)
        }
        case _ => println("hmm who did that?")
      }
    }      
  }  
}

