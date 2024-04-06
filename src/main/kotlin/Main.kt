package am9.openLightBit

import am9.openLightBit.data.ConfigYaml
import am9.openLightBit.data.MySQL
import am9.openLightBit.data.SQL
import am9.openLightBit.data.Sqlite3
import org.apache.logging.log4j.LogManager

class Main {
    fun main() {
        platform = "terminal"
        sendMessage(
            "OpenLightBit" +
            version +
            "\n\n" +
            "Welcome to OpenLightBit!")
    }
    companion object {
        val version = "9"
        var newbie = false
        lateinit var platform: String
        private val logger: org.apache.logging.log4j.Logger? = LogManager.getLogger("OpenLightBit")
        fun sendMessage(msg: String) {
            logger?.info(msg)
        }
        fun mainProgress(sqLiteFile: String, yamlLocation: String) {
            //读取配置文件
            ConfigYaml.cloudConfig(yamlLocation)
            ConfigYaml.mainConfig(yamlLocation)
            //链接数据库
            if (ConfigYaml.cloudConfig["use-mysql"] == false) {
                Sqlite3.connect(sqLiteFile)
            } else {
                MySQL.connect(ConfigYaml.cloudConfig["mysql-host"] as String +
                        ":" + ConfigYaml.cloudConfig["mysql-port"],
                    ConfigYaml.cloudConfig["mysql-user"] as String,
                    ConfigYaml.cloudConfig["mysql-password"] as String)
            }
            //执行SQL语句
            SQL.main()
        }
    }
}