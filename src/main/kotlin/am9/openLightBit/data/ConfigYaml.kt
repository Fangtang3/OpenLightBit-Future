package am9.openLightBit.data

import am9.olbcore.Core
import am9.openLightBit.Main
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream
import java.io.StringWriter
import kotlin.test.assertEquals
import java.io.IOException

class ConfigYaml {
    companion object {
        lateinit var cloudConfig: MutableMap<String, Any>
        lateinit var configYaml: MutableMap<String, Any>
        private val snakeYaml: Yaml = Yaml()

        private fun loadConfig(path: String, configFile: File): MutableMap<String, Any> {
            var inputStream: InputStream? = null
            try {
                inputStream = this.javaClass.classLoader.getResourceAsStream(path)
                if (inputStream == null) {
                    val defaultConfigYaml = configFile.readText()
                    configFile.writeText(defaultConfigYaml)
                    Main.sendMessage("请编辑配置文件！")
                    //throw IOException("配置文件不存在，已创建默认配置文件。")
                }
                return snakeYaml.load(inputStream) as MutableMap<String, Any>
            } catch (e: Exception) {
                Main.sendMessage("配置文件加载失败: ${e.message}")
            } finally {
                inputStream?.close()
            }
        }

        fun cloudConfig(path: String) {
            val cloudConfigFile = File("$path/cloud.yaml")
            cloudConfig = loadConfig("$path/cloud.yaml", cloudConfigFile)
        }

        fun mainConfig(path: String) {
            val configFileName = if (Core.platform == "Minecraft-Bukkit") "config.yml" else "config.yaml"
            val configFile = File("$path/$configFileName")
            configYaml = loadConfig("$path/$configFileName", configFile)

            if (configFile.exists()) {
                Main.newbie = true
            }
        }
    }
}
