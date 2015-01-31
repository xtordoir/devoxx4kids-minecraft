package org.devoxx4kids

import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler

import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor

import org.bukkit.Location
import org.bukkit.entity.Entity

case class CommandHandler(name: String, handler: (CommandSender, Command, String, Array[String]) => Boolean)

trait BukkitImplicits {

  implicit def toBlockPlace(handler: BlockPlaceEvent => Unit): Listener = {
    new Listener {
      @EventHandler
      def onBlockPlace(event: BlockPlaceEvent) {
        handler(event)
      }
    }
  }

  implicit def toProjectileHit(handler: ProjectileHitEvent => Unit): Listener = {
    new Listener {
      @EventHandler
      def onProjectileHit(event: ProjectileHitEvent) {
        handler(event)
      }
    }
  }

  implicit def toProjectileLaunch(handler: ProjectileLaunchEvent => Unit): Listener = {
    new Listener {
      @EventHandler
      def onProjectileLaunch(event: ProjectileLaunchEvent) {
        handler(event)
      }
    }
  }


  implicit def toCommand(handler: (CommandSender, Command, String, Array[String]) => Boolean): CommandExecutor = {
    new CommandExecutor {
      @Override
      def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
        // implementation exactly as before...
        handler(sender, cmd, label, args)
     }
    }
  }

  implicit def makeCommand( cmdHandler: CommandHandler ): CommandExecutor = {
    new CommandExecutor {
      @Override
      def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
        if (cmd.getName == cmdHandler.name) {
           cmdHandler.handler(sender, cmd, label, args)
        } else {
          false
        }
      }
    }
  }
}

import org.bukkit.plugin.java.JavaPlugin

class ScalaPlugin extends JavaPlugin with BukkitImplicits

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TestPlugin extends ScalaPlugin with Devoxx4kidsCommands with Devoxx4kidsEvents {

  implicit val plugin = this


  override def onEnable() {
    getServer.getPluginManager.registerEvents(handleProjectileLaunch, this)
    getServer.getPluginManager.registerEvents(handleProjectileHit, this)

  }
}
