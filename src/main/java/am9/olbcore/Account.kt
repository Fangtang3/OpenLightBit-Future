package am9.olbcore

import java.sql.*
import am9.olbcore.UniversalLogger

class Account {
    companion object {
        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun create(name: String, uid: Int) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("INSERT INTO user (name, id, banned, qid, mcname) VALUES ($name, $uid, 0, null, null)")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun createWithQQ(name: String, uid: Int, qid: Long) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("INSERT INTO user (name, id, banned, qid, mcname) VALUES ($name, $uid, 0, $qid, null)")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun createWithMC(name: String, uid: Int, mcname: String) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("INSERT INTO user (name, id, banned, qid, mcname) VALUES ($name, $uid, 0, null, $mcname)")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun ban(uid: Int) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("UPDATE user SET banned = 1 WHERE id = $uid")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun pardon(uid: Int) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("UPDATE user SET banned = 0 WHERE id = $uid")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun delete(uid: Int) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("DELETE FROM user WHERE id = $uid")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun update(uid: Int, name: String) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("UPDATE user SET name = $name WHERE id = $uid")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun query(uid: Int): String {
            val s = am9.olbcore.Database.c.createStatement()
            s.execute("SELECT name FROM user WHERE id = $uid")
            val result = s.executeQuery()
            return if (result.next()) result.getString(1) else "nothing"
        }

        @Throws(SQLException::class)
        @JvmStatic
        fun queryByName(name: String): Int {
            val s = am9.olbcore.Database.c.createStatement()
            s.execute("SELECT id FROM user WHERE name = $name")
            val result = s.executeQuery()
            return if (result.next()) result.getInt(1) else -1
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun bindQQ(uid: Int, qid: Long) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("UPDATE user SET qid = $qid WHERE id = $uid")
        }

        @Throws(SQLException::class, NullPointerException::class)
        @JvmStatic
        fun bindMC(uid: Int, mcname: String) {
            val s = am9.olbcore.Database.c.createStatement()
            s.executeUpdate("UPDATE user SET mcname = $mcname WHERE id = $uid")
        }
    }
}
