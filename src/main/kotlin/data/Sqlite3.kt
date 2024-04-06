package am9.openLightBit.data

import am9.openLightBit.Main
import java.sql.*


class Sqlite3 {
    companion object {
        fun connect(directory: String) {
            Main.sendMessage("SQLITE3 支持未完成，不建议使用！")
            Main.sendMessage("将在 10 秒后连接数据库（Ctrl+C 取消）")
            Thread.sleep(10000)
            SQL.c = DriverManager.getConnection("jdbc:sqlite:$directory")
        }
    }
}