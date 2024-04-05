package am9.openLightBit.bukkit

import am9.openLightBit.Main
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BukkitMain: JavaPlugin() {
    override fun onEnable() {
        Main.platform = "Minecraft-Bukkit"
        Bukkit.getConsoleSender().sendMessage("Starting OLB")
        Bukkit.getPluginCommand("olb")?.setExecutor(this);
        Main.mainProgress("./plugins/OpenLightBit/lightbit.db", "./plugins/OpenLightBit")
    }
}