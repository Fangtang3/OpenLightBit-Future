package am9.openLightBit.mirai

import am9.openLightBit.Main
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import org.apache.logging.log4j.LogManager


class Main(description: JvmPluginDescription) : KotlinPlugin(description) {
    fun main() {
        JvmPluginDescriptionBuilder("am9.openLightBit", "9.0.0")
            .name("OpenLightBit")
            .author("Emerald-AM9")
            .build()
    }

    override fun onEnable() {
        super.onEnable()
        Main.platform = "QQ-Mirai"
        LogManager.getLogger().info("OpenLightBit " + Main.version + "\n\n" +
        "Welcome!")
        Main.mainProgress("./lightbit.db", ".")
    }
}