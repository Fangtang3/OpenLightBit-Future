package am9.openLightBit.bukkit

import am9.olbcore.Core
import am9.openLightBit.Main
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class BukkitMain: JavaPlugin() {
    override fun onEnable() {
        val config = this.config
        Core.platform = "Minecraft-Bukkit"
        Bukkit.getConsoleSender().sendMessage("Starting OLB")
        saveDefaultConfig()
        Bukkit.getPluginCommand("olb")!!.setExecutor(Commands())
        Main.mainProgress("./plugins/OpenLightBit/lightbit.db", "./plugins/OpenLightBit")
        Main.bukkitGroup = config.getInt("bukkit-group-number")
    }
}