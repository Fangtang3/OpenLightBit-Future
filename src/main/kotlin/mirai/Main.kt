package am9.openLightBit.mirai

import am9.openLightBit.Main
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder
import org.apache.logging.log4j.LogManager


class Main(description: JvmPluginDescription) : JavaPlugin(description) {
    fun main() {
        JvmPluginDescriptionBuilder("am9.openLightBit", "9")
            .name("OpenLightBit")
            .author("Emerald-AM9")
            .build()
    }

    override fun onEnable() {
        super.onEnable()
        Main.platform = "QQ-Mirai"
        LogManager.getLogger().info("OpenLightBit " + am9.openLightBit.Main.version + "\n\n" +
        "Welcome!")
        Main.mainProgress("./lightbit.db", ".")
    }
}