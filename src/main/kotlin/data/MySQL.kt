package am9.openLightBit.data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import javax.naming.InitialContext
import javax.sql.DataSource


class MySQL {
    companion object {
        fun connect(address: String, user: String, password: String) {
            Class.forName("com.mysql.cj.jdbc.Driver")
            SQL.c = DriverManager.getConnection(
                "jdbc:mysql://" + address + "/test_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                user,
                password
            )
        }
    }
}