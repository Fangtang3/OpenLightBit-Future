package am9.olbcore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Woodenfish {
    public static ArrayList<Integer> query(Integer id) {
        ArrayList<Integer> rt = new ArrayList<>();
        try {
            Statement s = Database.c.createStatement();
            if (s.executeQuery("select id from wooden_fish").toString().contains(id.toString())) {
                ResultSet r = s.executeQuery("select * from wooden_fish where id = " + id);
                rt.add(r.getInt("gongde"));
                rt.add(r.getInt("level"));
                r.close();
                s.close();
            } else {
                throw new NullPointerException("面包厂未创建！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rt;
    }
}