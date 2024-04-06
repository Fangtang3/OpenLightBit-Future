package am9.openLightBit.data

import java.sql.*


class Sqlite3 {
    companion object {
        fun connect(directory: String) {
            SQL.c = DriverManager.getConnection("jdbc:sqlite:$directory")
            SQL.s = SQL.c.createStatement()
        }
    }
}