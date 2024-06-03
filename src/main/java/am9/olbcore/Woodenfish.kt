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
            UniversalLogger.error("SQL执行异常")
            throw e
        } catch (e: Exception) {
            UniversalLogger.error("木鱼出现错误")
            throw e
        }

        return rt
    }
}
