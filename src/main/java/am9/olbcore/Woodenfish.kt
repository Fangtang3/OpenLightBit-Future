package am9.olbcore

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class Woodenfish {

    // 假定Database类存在，并且它提供了一个静态的Connection对象c
    fun query(id: Int): ArrayList<Int> {
        val rt = ArrayList<Int>()
        // 使用参数化查询以避免SQL注入
        val sql = "SELECT * FROM wooden_fish WHERE id = ?"

        try {
            Database.c.prepareStatement(sql).use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        rt.add(resultSet.getInt("gongde"))
                        rt.add(resultSet.getInt("level"))
                    } else {
                        // 使用业务异常替代NullPointerException
                        throw NullPointerException("没有注册！")
                    }
                }
            }
        } catch (e: SQLException) {
            // 使用日志框架记录异常信息（这里使用伪代码表示）
            UniversalLogger.error("SQL执行异常", e)
            throw SQLException("数据库操作失败", e)
        } catch (e: Exception) {
            // 可以在这里处理业务逻辑异常，例如记录日志
            UniversalLogger.error("业务逻辑异常", e)
            throw e // 重新抛出业务异常，或按需处理
        }

        return rt
    }
}
