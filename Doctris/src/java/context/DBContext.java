/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Khuong Hung
 */
public class DBContext {
    private final String condition = "allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&requireSSL=false";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String serverName = "localhost";
            String portNumber = "3306";
            String dbName = "DoctrisDB";
            String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName + "?" + condition;

            String username = "root";
            String password = "142378956";

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public static void main(String[] args) {
        DBContext db = new DBContext();
        System.out.println(db.getConnection());
    }
}
