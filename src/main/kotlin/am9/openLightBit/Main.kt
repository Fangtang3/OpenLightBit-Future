package am9.openLightBit

import am9.olbcore.Core
import am9.olbcore.UniversalLogger
import am9.openLightBit.data.ConfigYaml
import am9.openLightBit.data.MySQL
import am9.openLightBit.data.SQL
import am9.openLightBit.data.Sqlite3
import org.apache.logging.log4j.LogManager

class Main {
    fun main() {
        Core.platform = "terminal"
        sendMessage(
            "OpenLightBit" +
            version +
            "\n\n" +
            "Welcome to OpenLightBit!")
    }
    companion object {
        val version = "9"
        var newbie = false
        var bukkitGroup: Int? = null
        fun sendMessage(msg: String) {
            UniversalLogger.info(msg)
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
            if (!newbie) {
                SQL.main()
            } else {
                UniversalLogger.error("请先修改配置文件！")
            }
        }
    }
}