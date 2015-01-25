package org.devoxx4kids

import scala.util.Try

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler

import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor

import org.bukkit.Location
import org.bukkit.entity.Entity

trait Devoxx4kidsCommands {

  val checkpoints = scala.collection.mutable.Map[String, Location]()

  def gotoCommand = (sender: CommandSender, cmd: Command, label: String, args: Array[String]) => {
  
      Try {
      	sender.asInstanceOf[Entity].teleport(checkpoints(args(0)))
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