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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Account;
import model.Appointment;
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
                list.add(new Patient(new Account(rs.getString(2), rs.getString(3), rs.getBoolean(4)), rs.getInt(1), rs.getDate(5), rs.getBoolean(6)));
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
                return new Patient(a, rs.getInt(7), rs.getDate(8), rs.getString(9), rs.getBoolean(10));
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

                list.add(new Patient(new Account(rs.getString(2), rs.getString(3), rs.getBoolean(4)), rs.getInt(1), rs.getDate(5), rs.getBoolean(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;

    }
    
        
    public int CountPatient(){
        int count = 0;
        String sql = "select count(*) from patient";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }
    
    public int getPatientIDByUsername(String username){
        int patient_id = 0;
        String sql = "select patient_id from patient  where username = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                patient_id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return patient_id;
    }
    
    public List<Patient> getPatientByDoctor(int doctor_id) throws SQLException, IOException {
        List<Patient> list = new ArrayList<>();
        String sql1 = "select distinct users.img, users.name, users.phone, users.email,patient.DOB,patient.patient_id ,a.pdate as lastbooking from appointments \n"
                + "inner join patient on appointments.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username inner join (\n"
                + "select patient_id as pid , max(date) as pdate from appointments group by patient_id\n"
                + ") as a on a.pid = appointments.patient_id where appointments.doctor_id = ?";

        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql1);
            ps.setInt(1, doctor_id);
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
                Account a = new Account(base64Image, rs.getString(2), rs.getInt(3), false , rs.getString(4));
                Appointment ap = new Appointment(rs.getDate(7), null, null);
                list.add(new Patient(a, rs.getDate(5), rs.getInt(6), ap));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
    
    public Patient getPatientbyid(int patient_id) throws SQLException, IOException {
        String sql = "SELECT u.name,u.email,u.phone,u.gender,p.DOB FROM users u inner join patient p\n"
                + "on u.username = p.username\n"
                + "where p.patient_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(null, rs.getString(1), rs.getInt(3), rs.getBoolean(4), rs.getString(2));
                return new Patient(a, rs.getDate(5));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

}
