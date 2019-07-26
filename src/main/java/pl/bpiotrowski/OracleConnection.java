package pl.bpiotrowski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleConnection {
    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oraclexe");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from apw_user.DEDP");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
