package am9.openLightBit.bukkit

import am9.olbcore.*
import am9.openLightBit.Main
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.lang.IllegalArgumentException
import kotlin.random.Random

class Commands: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        try {
            return executeCommand(p0, p1, p2, p3)
        } catch (e: IllegalArgumentException) {
            UniversalLogger.error("需要玩家操作！")
            return false
        }
    }
    fun executeCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        lateinit var sender: Player
        if (p0 is Player) {
            sender = p0
        } else {
            throw IllegalArgumentException("需要玩家操作！")
        }
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
            if (p3.size < 2) {
                sender.sendMessage("无效参数！")
            }
            if (Account.query(p3[1]) == -1) {
                var id = Random.nextInt(900000) + 100000
                while (Account.query(id) != "nothing") {
                    id = Random.nextInt(900000) + 100000
                }
                Account.createWithMC(p3[1], id, sender.name)
                sender.sendMessage("注册成功！")
            } else {
                sender.sendMessage("你已经注册过了！")
            }
        }
        if (p3[0] == "bread") {
            if (p3[1] == "give") {
                try {
                    val currentBread = am9.olbcore.BreadFactory.giveBread(Integer.parseInt(p3[2]), Main.bukkitGroup)
                    sender.sendMessage("给予了" + p3[2] + "个面包，仓库现在有" + currentBread + "个面包")
                    return true
                } catch (e: BreadException) {
                    sender.sendMessage("面包太多了！")
                    return false
                } catch (e: IllegalArgumentException) {
                    sender.sendMessage("面包数量不能为0！")
                    return false
                }
            }
            if (p3[1] == "get") {
                try {
                    val getBread = am9.olbcore.BreadFactory.getBread(Integer.parseInt(p3[2]), Main.bukkitGroup)
                    sender.inventory.addItem(ItemStack(Material.BREAD))
                    sender.sendMessage("面包 x$getBread")
                    return true
                } catch (e: BreadException) {
                    sender.sendMessage("面包不够！")
                    return false
                } catch (e: IllegalArgumentException) {
                    sender.sendMessage("面包数量不能为0！")
                    return false
                }
            }
            if (p3[1] == "build") {
                am9.olbcore.BreadFactory.buildFactory(Main.bukkitGroup)
                p0.sendMessage("已在本群建造面包厂！")
                return true
            }
        }
        if (p3[0] == "woodenfish") {
            return true
        }
        return true
    }
}