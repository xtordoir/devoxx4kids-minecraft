package org.devoxx4kids

import org.bukkit.event.block.BlockPlaceEvent
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

class TestPlugin extends ScalaPlugin with Devoxx4kidsCommands {

  implicit val plugin = this

  override def onCommand(sender: CommandSender, cmd: Command, label: String, args: Array[String]): Boolean = {
  	true
  }

  def handleBlockPlacement = (event: BlockPlaceEvent) => {
    val player = event.getPlayer
    val block = event.getBlockPlaced

    val location = block.getLocation()
    val world = location.getWorld()
    player.sendMessage("You placed a " + block.getType + " at " + "(" + location.getBlockX + "," + location.getBlockY + "," + location.getBlockZ + ")")
    
/*    (1 to 20).foreach { i =>
      val currentBlock = world.getBlockAt(block.getLocation.add(0,i,0))
      // Set the block to type 57 (Diamond block!)
      currentBlock.setType(org.bukkit.Material.DIAMOND_BLOCK)
    }
*/  

  }


  override def onEnable() {
    getServer.getPluginManager.registerEvents(handleBlockPlacement, this)

    this.getCommand("checkpoint").setExecutor(checkpointCommand)
    this.getCommand("goto").setExecutor( CommandHandler("goto", gotoCommand) )
  }
}
