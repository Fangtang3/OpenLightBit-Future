package am9.olbcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static java.sql.Connection c;
    public static String dbname;
    public static void connect(Connection c) {
        Database.c = c;
    }
    public static void connect(String url, String user, String pswd) {
        try {
            connect(DriverManager.getConnection(url, user, pswd));
            dbname = url.split("/")[url.split("/").length - 1];
        } catch (SQLException e) {
            UniversalLogger.Companion.error(e.getMessage());
        }
    }
    public static void init() {
        try {
            Statement s = c.createStatement();
            if (dbname != null) {
                s.execute("use " + dbname);
            }
            s.execute("create table if not exists account (" +
                    "name varchar(255)," +
                    "id int," +
                    "banned tinyint)");
            s.execute("create table if not exists bread (" +
                    "group int not null comment '群号'," +
                    "breads int not null default '0' comment '面包数量'," +
                    "exp int not null default '0' comment '经验'," +
                    "unlimited tinyint not null default '0' comment '是否启用无限模式'," +
                    "flour int not null default '0' comment '面粉'," +
                    "egg int not null default '0' comment '鸡蛋'," +
                    "yeast int not null default '0' comment '酵母')");
            s.execute("create table if not exists wooden_fish (" +
                    "id int," +
                    "gongde int not null default '0' comment '功德'," +
                    "level tinyint not null default '0' comment '木鱼等级')");
            s.execute("create table if not exists global_ops (id int not null)");
            s.execute("create table if not exists global_blacklist (id int not null)");
            s.execute("create table if not exists global_ignore (id int not null)");
            s.execute("create table if not exists global_whitelist (id int not null)");
            s.close();
        } catch (SQLException e) {
            UniversalLogger.Companion.error(e.getMessage());
        }
    }
}
