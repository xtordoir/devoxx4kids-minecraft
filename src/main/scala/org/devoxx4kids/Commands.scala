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
import org.bukkit.entity.Player

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

trait Devoxx4kidsCommands {

  def arrowCommand = (sender: CommandSender, cmd: Command, label: String, args: Array[String]) => {
  
      Try {
      	val player = sender.asInstanceOf[Player]
        val inventory = player.getInventory() // The player's inventory
        val itemstack = new ItemStack(Material.ARROW, 64) // A stack of arrows
        // hey what about a BOW...
        inventory.addItem(itemstack)
      }
      true
  }
}