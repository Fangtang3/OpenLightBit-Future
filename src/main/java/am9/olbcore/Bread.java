package am9.olbcore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bread {
    public static ArrayList<Integer> query(Integer gid) {
        ArrayList<Integer> rt = new ArrayList<>();
        try {
            Statement s = Database.c.createStatement();
            if (s.executeQuery("select group from bread").toString().contains(gid.toString())) {
                ResultSet r = s.executeQuery("select * from bread where group = " + gid);
                rt.add(r.getInt("breads"));
                rt.add(r.getInt("exp"));
                rt.add(r.getInt("unlimited"));
                rt.add(r.getInt("flour"));
                rt.add(r.getInt("egg"));
                rt.add(r.getInt("yeast"));
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
