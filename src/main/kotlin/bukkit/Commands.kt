package am9.openLightBit.bukkit

import am9.openLightBit.Main
import am9.openLightBit.core.Menu
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Commands: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val sender = p0 as Player
        if (p3[1] == "menu") {
            sender.sendMessage(Menu.getMenu())
        }
        if (p3[1] == "version") {
            sender.sendMessage("OpenLightBit 9 " + Main.version)
        }
        if (p3[1] == "announce") {
            Bukkit.broadcastMessage(p3[2])
        }
        if (p3[1] == "echo") {
            sender.sendMessage(p3[2])
        }
        if (p3[1] == "mute") {
            sender.sendMessage("该平台下不能使用禁言！")
            return false
        }
        if (p3[1] == "call") {
            Bukkit.broadcastMessage(p3[2] + " 机器人正在召唤你")
        }
        return true
    }
}