package am9.olbcore;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
    public static void create(String name, Integer uid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("insert into user (name, id, banned) VALUES (" + name + ", " + uid + ", 0)");
        } catch (Exception e) {
            if (am9.olbcore.Database.c == null) {
                throw new NullPointerException("未连接数据库");
            } else {
                e.printStackTrace();
            }
        }
    }
}
