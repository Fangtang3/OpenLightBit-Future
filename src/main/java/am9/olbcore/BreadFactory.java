package am9.olbcore;

import java.sql.SQLException;
import java.sql.Statement;
import static am9.olbcore.Bread.query;
import static java.lang.Integer.parseInt;

public class BreadFactory {
    public static Integer giveBread(Integer number, Integer gid) throws BreadException {
        try {
            Statement s = Database.c.createStatement();
            if (query(gid).get(0) + number < 50 * query(gid).get(1)) {
                throw new BreadException("仓库满了！");
            } else if (number < 1) {
                throw new IllegalArgumentException("给你面包 数量不能小于1！");
            } else {
                s.execute("update bread set breads = " +
                        (parseInt(String.valueOf(query(gid).get(0))) + number) +
                        " where group = " + gid);
                return query(gid).get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public static Integer getBread(Integer number, Integer gid) throws BreadException, IllegalArgumentException {
        try {
            Statement s = Database.c.createStatement();
            if (query(gid).get(0) < number) {
                throw new BreadException("没有那么多面包！");
            } else if (number < 1) {
                throw new IllegalArgumentException("来份面包 数量不能小于1！");
            } else {
                s.execute("update bread set breads = " +
                        (parseInt(String.valueOf(query(gid).get(0))) - number) +
                        " where group = " + gid);
                return number;
            }
        } catch (SQLException e) {
            UniversalLogger.Companion.error(e.getMessage());
            return -1;
        }
    }
    public static void buildFactory(Integer gid) {
        try {
            Statement s = am9.olbcore.Database.c.createStatement();
            s.execute("insert into bread (group, breads) VALUES (" + gid + ", 30)");
        } catch (SQLException e) {
            UniversalLogger.Companion.error(e.getMessage());
        }
    }
}
