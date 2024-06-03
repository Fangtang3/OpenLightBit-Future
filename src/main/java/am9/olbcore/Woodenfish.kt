package am9.olbcore

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class Woodenfish {

    fun query(id: Int): ArrayList<Int> {
        val rt = ArrayList<Int>()
        val sql = "SELECT * FROM wooden_fish WHERE id = ?"
        try {
            Database.c.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        rt.add(resultSet.getInt("gongde"))
                        rt.add(resultSet.getInt("level"))
                    } else {
                        throw NullPointerException("没有注册！")
                    }
                }
            }
        } catch (e: SQLException) {
            UniversalLogger.error("SQL执行异常" + e.getMessage)
            throw SQLException("数据库操作失败" + e.getMessage)
        } catch (e: Exception) {
            UniversalLogger.error("业务逻辑异常" + e.getMessage)
            throw e
        }

        return rt
    }
}
