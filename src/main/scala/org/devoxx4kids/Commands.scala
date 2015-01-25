package org.devoxx4kids

import scala.util.Try

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler

import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor

import org.bukkit.util.Vector

import org.bukkit.Location
import org.bukkit.entity.Entity

trait Devoxx4kidsCommands {

  val rnd = new scala.util.Random()
  val checkpoints = scala.collection.mutable.Map[String, Location]()

  val chance = 0.5

  def gotoCommand = (sender: CommandSender, cmd: Command, label: String, args: Array[String]) => {
  
      if (rnd.nextDouble < chance) {
        val entity = sender.asInstanceOf[Entity]
        entity.teleport(checkpoints(args(0)))          
      } else {
        val entity =  sender.asInstanceOf[Entity]
        entity.setVelocity(new Vector(0,10,0))
      }
      true
  }

  def checkpointCommand = (sender: CommandSender, cmd: Command, label: String, args: Array[String]) => {

      sender.sendMessage(s"Setting checkpoint ${args(0)}")
      Try{ 
        checkpoints += (args(0) -> sender.asInstanceOf[Entity].getLocation())
      }
      true
  }
}