package am9.openLightBit.mirai

import am9.olbcore.BreadException
import am9.olbcore.Info
import am9.olbcore.Menu
import am9.openLightBit.Main
import net.mamoe.mirai.Mirai
import net.mamoe.mirai.contact.Group
class Commands {
    //简单移植
    fun commandGroup(qq: Long, sender: Group, p3: List<String>) {
        if (p3[0] == "/olb") {
            if (p3[1] == "menu" || p3[1] == "help" || p3[1] == "") {
                sender.sendMessage(Menu.getMenu())
            }
            if (p3[1] == "version") {
                sender.sendMessage("OpenLightBit 9 " + Main.version)
            }
            if (p3[1] == "announce") {
                TODO();
            }
            if (p3[1] == "echo") {
                sender.sendMessage(p3[2])
            }
            if (p3[1] == "mute") {
                sender.sendMessage("该平台下不能使用禁言！")
            }
            if (p3[1] == "call") {
                sender.sendMessage(" 机器人正在呼唤你")
            }
            if (p3[1] == "info") {
                sender.sendMessage(Info.getInfo())
            }
            if (p3[1] == "register") {
                if (p3.size < 3) {
                    sender.sendMessage("无效参数！")
                }
                if (Account.queryByName(p3[2]) == -1) {
                    var id = Random.nextInt(900000) + 100000
                    while (Account.query(id) != "nothing") {
                        id = Random.nextInt(900000) + 100000
                    }
                    Account.createWithQQ(p3[2], id, qq)
                    sender.sendMessage("注册成功！")
                } else {
                    sender.sendMessage("你已经注册过了！")
                }
            }
            if (p3[1] == "bread") {
                if (p3[2] == "give") {
                    try {
                        val currentBread = am9.olbcore.BreadFactory.giveBread(Integer.parseInt(p3[3]), Main.bukkitGroup)
                        sender.sendMessage("给予了" + p3[3] + "个面包，仓库现在有" + currentBread + "个面包")
                    } catch (e: BreadException) {
                        sender.sendMessage("面包太多了！")
                    } catch (e: IllegalArgumentException) {
                        sender.sendMessage("面包数量不能为0！")
                    }
                }
                if (p3[2] == "get") {
                    try {
                        val getBread = am9.olbcore.BreadFactory.getBread(Integer.parseInt(p3[3]), Main.bukkitGroup)
                        sender.sendMessage("面包 x$getBread")
                    } catch (e: BreadException) {
                        sender.sendMessage("面包不够！")
                    } catch (e: IllegalArgumentException) {
                        sender.sendMessage("面包数量不能为0！")
                    }
                }
                if (p3[2] == "build") {
                    am9.olbcore.BreadFactory.buildFactory(Main.bukkitGroup)
                    sender.sendMessage("已在本群建造面包厂！")
                }
            }
            if (p3[1] == "woodenfish") {
                TODO()
            }
        }
    }
}