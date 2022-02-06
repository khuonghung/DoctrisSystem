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
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class SettingDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Setting> getAllSetting() throws SQLException {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from setting";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<SettingDetails> getAll() throws SQLException {
        List<SettingDetails> list = new ArrayList<>();
        String sql = "SELECT se.id AS \"ID\", se.name AS \"Name\", se.setting_id AS \"Setting ID\", se.status AS \"Status\"\n"
                + "FROM doctris_system.setting s\n"
                + "inner join doctris_system.category_service se\n"
                + "on se.setting_id = se.setting_id\n"
                + "union\n"
                + "SELECT se.id AS \"ID\", se.name AS \"Name\", se.setting_id AS \"Setting ID\", se.status AS \"Status\"\n"
                + "FROM doctris_system.setting s\n"
                + "inner join doctris_system.role se\n"
                + "on se.setting_id = se.setting_id\n"
                + "union\n"
                + "SELECT se.id AS \"ID\", se.name AS \"Name\", se.setting_id AS \"Setting ID\", se.status AS \"Status\"\n"
                + "FROM doctris_system.setting s\n"
                + "inner join doctris_system.category_blog se\n"
                + "on se.setting_id = se.setting_id";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
            }

        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<SettingDetails> getBySetting(String table) throws SQLException {
        List<SettingDetails> list = new ArrayList<>();
        String sql = "select * from " + table;
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
            }

        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void SettingUpdate(String table, int ID, String name, boolean status, int setting_id) throws SQLException {
        String sql = "UPDATE " + table + " SET name = ?, setting_id = ?, status = ? WHERE (id = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, setting_id);
            ps.setBoolean(3, status);
            ps.setInt(4, ID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void SettingDelete(String table, int ID) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE (id = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void SettingADD(String table, String name, boolean status, int setting_id) throws SQLException {
        String sql = "INSERT INTO " + table + " (`name`, `setting_id`, `status`) VALUES (?, ?, ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, setting_id);
            ps.setBoolean(3, status);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<SettingDetails> getListByPage(List<SettingDetails> list,
            int start, int end) {
        ArrayList<SettingDetails> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
