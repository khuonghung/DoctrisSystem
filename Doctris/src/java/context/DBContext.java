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

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://clinicbooking.mysql.database.azure.com:3306/doctris_system";
            String username = "khuonghung1423";
            String password = "clinic.booking1423";
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        DBContext db = new DBContext();
        System.out.println(db.getConnection());
        System.out.println(db.getConnection().getCatalog());
    }
}
