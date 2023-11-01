/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 84859
 */
public class JDBC {

    public static Connection getConnection() throws Exception {
        Connection cn = null;
        String user = "sa";
        String password = "12345";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost\\MSI:1433;databaseName=INTERCOM_PRJ301_ASSWEB;user=" + user + ";password="+ password;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
}
