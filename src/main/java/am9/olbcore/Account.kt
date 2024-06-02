package am9.olbcore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import am9.olbcore.UniversalLogger;

public class Account {
    public static void create(String name, Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("insert into user (name, id, banned) VALUES (" + name + ", " + uid + ", 0)");
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());
            }
        }
    }
    public static void ban(Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("update user set banned = 1 where id = " + uid);
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());
            }
        }
    }
    public static void pardon(Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("update user set banned = 0 where id = " + uid);
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());
            }
        }
    }
    public static void delete(Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("delete from user where id = " + uid);
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());
            }
        }
    }
    public static void update(Integer uid, String name) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("update user set name = " + name + " where id = " + uid);
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());

            }
        }
    }
    public static String query(Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("select name from user where id = " + uid);
            ResultSet result = s.getResultSet();
            if (result.next()) {
                return result.getString(1);
            } else {
                return "nothing";
            }
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                UniversalLogger.Companion.error(e.getMessage());
            }
            return "nothing";
        }
    }
    public static Integer query(String name) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("select id from user where name = " + name);
            ResultSet result = s.getResultSet();
            if (result.next()) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }
}
