package am9.openLightBit.data

import am9.olbcore.Core
import am9.openLightBit.Main
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.InputStream
import java.io.StringWriter
import kotlin.test.assertEquals

class ConfigYaml {
    companion object {
        lateinit var cloudConfig: MutableMap<String, Any>
        lateinit var configYaml: MutableMap<String, Any>
        private lateinit var inputStream: InputStream
        private val snakeYaml: Yaml = Yaml()
        fun cloudConfig(path: String) {
            //lateinit var cloudConfig: MutableMap<String, Any>
            val cloudConfigFile = File("$path/cloud.yaml")
            inputStream = this.javaClass
                .classLoader
                .getResourceAsStream("$path/cloud.yaml") as InputStream
            val writer = StringWriter()
            try {
                cloudConfig = snakeYaml.load(inputStream)
            } catch (e: java.io.FileNotFoundException) {
                cloudConfig["use-mysql"] = true
                cloudConfig["mysql-host"] = "localhost"
                cloudConfig["mysql-port"] = 3306
                cloudConfig["mysql-user"] = "root"
                cloudConfig["mysql-password"] = "openlightbit"
                cloudConfig["mysql-dbname"] = "mica"
                cloudConfig["valkey-host"] = "localhost"
                cloudConfig["valkey-port"] = 6379
                snakeYaml.dump(cloudConfig, writer)
                val cloudConfigYaml =
                    "use-mysql: true\n" +
                            "mysql-host: localhost\n" +
                            "mysql-port: 3306\n" +
                            "mysql-user: root\n" +
                            "mysql-password: openlightbit\n" +
                            "valkey-host: localhost\n" +
                            "valkey-port: 6379\n"
                assertEquals(cloudConfigYaml, writer.toString())
                cloudConfigFile.writeText(cloudConfigYaml)
                Main.sendMessage("请编辑配置文件！")
            }
        }
        fun mainConfig(path: String) {
            val configYaml: String = if (Core.platform == "Minecraft-Bukkit") {
                "config.yml"
            } else {
                "config.yaml"
            }
            val configFile = File("$path/$configYaml")
            inputStream = this.javaClass
                .classLoader
                .getResourceAsStream("$path/$configYaml") as InputStream
            val writer = StringWriter()
            try {
                cloudConfig = snakeYaml.load(inputStream)
            } catch (e: java.io.FileNotFoundException) {
                cloudConfig["version"] = 1
                cloudConfig["group-if-bukkit"] = 10
                snakeYaml.dump(cloudConfig, writer)
                val configYamlSample =
                    "version: 1" +
                            "group-if-bukkit: 10"
                assertEquals(configYamlSample, writer.toString())
                configFile.writeText(configYamlSample)
                Main.sendMessage("请编辑配置文件！")
                Main.newbie = true
            }
        }
    }
}