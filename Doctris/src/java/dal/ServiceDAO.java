/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import context.DBContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class ServiceDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Service> getRandomTop6Service() throws SQLException {
        List<Service> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,s.category_id)id ,cs.name,cs.setting_id,"
                + "cs.status,s.service_id,s.title,s.fee,s.description,s.img "
                + "from doctris_system.service s "
                + "inner join doctris_system.category_service cs "
                + "on s.category_id = cs.id ORDER BY RAND() LIMIT 6";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Service(s, rs.getInt(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
    
    public List<Service> getServiceNameAndID() throws SQLException, IOException {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT service_id, title FROM doctris_system.service";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
}
