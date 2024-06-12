package am9.olbcore

import groovy.lang.GroovyShell
import java.sql.ResultSet
import java.sql.Statement
import java.io.File

class World {
    companion object {
        @JvmStatic
        fun isBotOp(uid: Int): Boolean {
            val s: Statement = Database.c.createStatement()
            val rs: ResultSet = s.executeQuery("select * from global_ops where id = '$uid';")
            return rs.next()
        }

        @JvmStatic
        fun isBotBlacklist(uid: Int): Boolean {
            val s: Statement = Database.c.createStatement()
            val rs: ResultSet = s.executeQuery("select * from global_blacklist where id = '$uid';")
            return rs.next()
        }

        @JvmStatic
        fun isBotIgnore(uid: Int): Boolean {
            val s: Statement = Database.c.createStatement()
            val rs: ResultSet = s.executeQuery("select * from global_ignore where id = '$uid';")
            return rs.next()
        }

        @JvmStatic
        fun execute(command: String) {
            if (command == "me") {
                UniversalLogger.info("""
                    
                """.trimIndent())
            } else {
                Runner.run(command)
            }
        }

        @JvmStatic
        fun reload(path: String) {
            try {
                val files = File(path).list()
                if (files != null) {
                    val shell = GroovyShell()
                    files.forEach { file ->
                        shell.parse(file).run()
                    }
                }
            } catch (e: Exception) {
                UniversalLogger.error("重载失败: ${e.message}")
            }
        }

        @JvmStatic
        fun log(type: String, message: String) {
            when (type) {
                "info" -> UniversalLogger.info(message)
                "warn" -> UniversalLogger.warn(message)
                "error" -> UniversalLogger.error(message)
                else -> UniversalLogger.info(message)
            }
        }

        @JvmStatic
        fun sendMessage(msg: String) {
            val e = MessageSendRequired(msg)
            log("info", "Sending message")
            throw e
        }
    }
}

class MessageSendRequired(msg: String): Throwable()