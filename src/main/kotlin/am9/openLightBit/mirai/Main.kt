package am9.openLightBit.mirai

import am9.olbcore.Core
import am9.olbcore.UniversalLogger
import am9.openLightBit.Main
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel.subscribeAlways
import net.mamoe.mirai.event.events.GroupMessageEvent
import org.apache.logging.log4j.LogManager


class Main(description: JvmPluginDescription) : KotlinPlugin(description) {
    fun main() {
        JvmPluginDescriptionBuilder("am9.openLightBit", "9.0.0")
            .name("OpenLightBit")
            .author("Emerald-AM9")
            .build()
    }

    override fun onEnable() {
        JvmPluginDescriptionBuilder("am9.openLightBit", "9.0.0")
            .name("OpenLightBit")
            .author("Emerald-AM9")
            .build()
        super.onEnable()
        Core.platform = "QQ-Mirai"
        LogManager.getLogger().info("OpenLightBit " + Main.version + "\n\n" +
        "Welcome!")
        Main.mainProgress("./lightbit.db", ".")
        subscribeAlways<GroupMessageEvent> {
            if (it.message.contentToString().startsWith("/olb")) {
                try {
                    am9.openLightBit.mirai.Commands().commandGroup(sender.id, group, message.contentToString().split(" "))
                } catch (e: NotImplementedError) {
                    group.sendMessage("未实现")
                } catch (e: Exception) {
                    UniversalLogger.error("出错了！" + e.message)
                }
            }
        }
    }
}