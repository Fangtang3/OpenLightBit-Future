package am9.openLightBit.bukkit

import am9.olbcore.Bread
import am9.olbcore.Info
import am9.olbcore.Menu
import am9.openLightBit.Main
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Commands: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val sender = p0 as Player
        if (p3[0] == "menu" || p3[0] == "help" || p3[0] == "") {
            sender.sendMessage(Menu.getMenu())
        }
        if (p3[0] == "version") {
            sender.sendMessage("OpenLightBit 9 " + Main.version)
        }
        if (p3[0] == "announce") {
            Bukkit.broadcastMessage(p3[2])
        }
        if (p3[0] == "echo") {
            sender.sendMessage(p3[2])
        }
        if (p3[0] == "mute") {
            sender.sendMessage("该平台下不能使用禁言！")
            return false
        }
        if (p3[0] == "call") {
            Bukkit.broadcastMessage(p3[2] + " 机器人正在呼唤你")
        }
        if (p3[0] == "info") {
            sender.sendMessage(Info.getInfo())
        }
        if (p3[0] == "register") {
            am9.olbcore.Account.create(p3[1], 222)
        }
        if (p3[0] == "bread") {
            if (p3[1] == "give") {
                am9.olbcore.BreadFactory.giveBread(Integer.parseInt(p3[0]), Main.bukkitGroup)
            }
            if (p3[1] == "get") {
                am9.olbcore.BreadFactory.getBread(Integer.parseInt(p3[0]), Main.bukkitGroup)
            }
            if (p3[1] == "build") {
                am9.olbcore.BreadFactory.buildFactory(Main.bukkitGroup)
            }
        }
        return true
    }
}