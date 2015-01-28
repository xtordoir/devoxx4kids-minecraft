package org.devoxx4kids

import org.devoxx4kids.ProjectileHack._

import scala.util.Try

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler

import org.bukkit.event.entity.ProjectileHitEvent

import org.bukkit.util.Vector

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

import org.bukkit.entity.Arrow
import org.bukkit.projectiles.ProjectileSource

trait Devoxx4kidsEvents {

  def handleProjectileHit = (event: ProjectileHitEvent) => {

    if (event.getEntityType() == EntityType.ARROW) {  

      val arrow = event.getEntity  
      val location = arrow.getLocation
      
      arrow.getTheShooter match {
        case player: Player => {
          val dist = location.distance(player.getLocation)
          arrow.getWorld.createExplosion(arrow.getLocation, 0.1f)
          player.sendMessage("Explosion à :" + dist)
        }
        case _ => println("hmm who did that?")
      }
    }
       
  }  
}

