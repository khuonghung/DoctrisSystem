/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Account;
import model.Patient;

/**
 *
 * @author Trung
 */
public class PatientDao {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    public List<Patient> getAllPatient() throws SQLException, IOException {
        List<Patient> list = new ArrayList<>();
        String sql = "select p.patient_id,u.username,u.name,u.gender,p.DOB,p.status from doctris_system.patient p\n"
                + "inner join doctris_system.users u\n"
                + "on p.username = u.username";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(new Account(rs.getString(2), rs.getString(3), rs.getBoolean(4)), rs.getInt(1), formatter.format(rs.getDate(5)), rs.getBoolean(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;

    }
    public List<Patient> getListByPage(List<Patient> list,
            int start, int end) {
        ArrayList<Patient> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }


    public Patient getPatientByUsername(String username) throws SQLException, IOException {
        String sql = "select  u.img, u.username,u.name,u.email,u.gender,u.phone,p.patient_id,p.DOB,p.address,p.status from patient p inner join users u \n"
                + "on p.username = u.username\n"
                + "where u.username = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(1);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Account a = new Account(base64Image, rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getInt(6));
                return new Patient(a, rs.getInt(7), formatter.format(rs.getDate(8)), rs.getString(9), rs.getBoolean(10));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void PatientUpdate(String username, String address, Date DOB, int patient_id, String name, boolean status, boolean gender, int phone) throws SQLException {
        String sql = "UPDATE `doctris_system`.`patient`\n"
                + "SET\n"
                + "`address` = ?,\n"
                + "`DOB` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `patient_id` = ?";
        String sql2 = "UPDATE `doctris_system`.`users`\n"
                + "SET\n"
                + "`name` = ?,\n"
                + "`gender` = ?,\n"
                + "`phone` = ?\n"
                + "WHERE `username` = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, address);
            ps.setDate(2, DOB);
            ps.setBoolean(3, status);
            ps.setInt(4, patient_id);
            ps.executeUpdate();
            ps = connection.prepareStatement(sql2);
            ps.setString(1, name);
            ps.setBoolean(2, gender);
            ps.setInt(3, phone);
            ps.setString(4, username);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public List<Patient> getPatientByName(String name) throws SQLException, IOException {
        List<Patient> list = new ArrayList<>();
        String sql = "select p.patient_id,u.username,u.name,u.gender,p.DOB,p.status from doctris_system.patient p\n" +
"                inner join doctris_system.users u\n" +
"                on p.username = u.username\n" +
"                WHERE u.name LIKE N'%"+name+"%'";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Patient(new Account(rs.getString(2), rs.getString(3), rs.getBoolean(4)), rs.getInt(1), formatter.format(rs.getDate(5)), rs.getBoolean(6)));
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
