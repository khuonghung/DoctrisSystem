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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class DoctorDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Doctor> getRandomTop6Doctor() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id,"
                + " cs.name, cs.setting_id ,cs.status,"
                + "d.doctor_id,d.role_id,d.doctor_name,d.username,"
                + "d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id ORDER BY RAND() LIMIT 8";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> getAllDoctor() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id, cs.name, "
                + "cs.setting_id ,cs.status,d.doctor_id,d.role_id,d.doctor_name,"
                + "d.username,d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> getAllDoctorByGender(String gender) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id, cs.name, "
                + "cs.setting_id ,cs.status,d.doctor_id,d.role_id,d.doctor_name,"
                + "d.username,d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id where d.gender = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, Boolean.parseBoolean(gender));
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> getAllDoctorByFilter(String gender, String speciality) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id, cs.name, "
                + "cs.setting_id ,cs.status,d.doctor_id,d.role_id,d.doctor_name,"
                + "d.username,d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id where d.gender = ? and d.category_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, Boolean.parseBoolean(gender));
            ps.setInt(2, Integer.parseInt(speciality));
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> getAllDoctorBySpeciality(String speciality) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id, cs.name, "
                + "cs.setting_id ,cs.status,d.doctor_id,d.role_id,d.doctor_name,"
                + "d.username,d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id where d.category_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(speciality));
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> Search(String text) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,d.category_id)id, cs.name, "
                + "cs.setting_id ,cs.status,d.doctor_id,d.role_id,d.doctor_name,"
                + "d.username,d.gender,d.DOB,d.phone,d.description,d.status,d.img "
                + "from doctris_system.doctor d "
                + "inner join doctris_system.category_service cs "
                + "on d.category_id = cs.id where d.doctor_name LIKE ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(8));
                SettingDetails s = new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Doctor(s, rs.getInt(5), rs.getInt(6), rs.getString(7), a, rs.getBoolean(9), rs.getDate(10), rs.getInt(11), rs.getString(12), rs.getBoolean(13), rs.getString(14)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<SettingDetails> getSpeciality() throws SQLException {
        List<SettingDetails> list = new ArrayList<>();
        String sql = "select * from doctris_system.category_service";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SettingDetails(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Doctor> getListByPage(List<Doctor> list,
            int start, int end) {
        ArrayList<Doctor> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
}
