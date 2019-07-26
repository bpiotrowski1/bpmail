package pl.bpiotrowski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class OracleConnection {
    void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oraclexe");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT df.tablespace_name \"Tablespace\", df.bytes / (1024 * 1024)" +
                    " \"Size(MB)\", SUM(fs.bytes) / (1024 * 1024) \"Free(MB)\", Nvl(Round(SUM(fs.bytes) * 100 / df.bytes),1)" +
                    " \"% Free\", Round((df.bytes - SUM(fs.bytes)) * 100 / df.bytes) \"% Used\" FROM dba_free_space fs, " +
                    "(SELECT tablespace_name,SUM(bytes) bytes FROM dba_data_files GROUP BY tablespace_name) df " +
                    "WHERE fs.tablespace_name (+)  = df.tablespace_name and df.tablespace_name in ('APWDATA','EDE','KS','USERS','APWIDX') " +
                    "GROUP BY df.tablespace_name,df.bytes");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2) + " " + resultSet.getDouble(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
