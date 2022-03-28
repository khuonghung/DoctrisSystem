/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khuong Hung
 */
public class RateDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public void insertRate(String type, int id, int rate, String comment, String username) throws SQLException {
        try {
            if (type.equals("doctor")) {
                String sql = "INSERT INTO `ratestar` (`username`, `doctor_id`, `star`, `feedback`, `datetime`) VALUES (?, ?, ?, ?, NOW())";
                connection = dbc.getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, id);
                ps.setInt(3, rate);
                ps.setString(4, comment);
                ps.executeUpdate();
            }

            if (type.equals("service")) {
                String sql = "INSERT INTO `ratestar` (`username`, `service_id`, `star`, `feedback`, `datetime`) VALUES (?, ?, ?, ?, NOW())";
                connection = dbc.getConnection();
                ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, id);
                ps.setInt(3, rate);
                ps.setString(4, comment);
                ps.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
