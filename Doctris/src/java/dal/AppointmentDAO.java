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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Base64;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class AppointmentDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Appointment> getAppointmentList() throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,appointments.time, appointments.status from appointments inner join doctor on appointments.doctor_id = doctor.doctor_id inner join patient on appointments.patient_id = patient.patient_id inner join users on patient.username = users.username";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString(2));
                Account account = new Account(rs.getString(3));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
    
    public List<Appointment> getAppointmentListByStaff(String staff) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "Select appointments.appointment_id , doctor.doctor_name, "
                + "users.name , appointments.date ,appointments.time, "
                + "appointments.status from appointments inner join doctor "
                + "on appointments.doctor_id = doctor.doctor_id inner join patient "
                + "on appointments.patient_id = patient.patient_id inner join users "
                + "on patient.username = users.username where appointments.staff = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, staff);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString(2));
                Account account = new Account(rs.getString(3));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
    
     public List<Appointment> getAppointmentByDoctor(int doctor_id) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "Select appointments.appointment_id , doctor.doctor_name, "
                + "users.name , appointments.date ,appointments.time, "
                + "appointments.status from appointments inner join doctor "
                + "on appointments.doctor_id = doctor.doctor_id inner join patient "
                + "on appointments.patient_id = patient.patient_id inner join users "
                + "on patient.username = users.username where doctor.doctor_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString(2));
                Account account = new Account(rs.getString(3));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
     
    public List<Appointment> getFilter(String doctor_id, String status) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String filterStatus = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.status = ?";

        String filterDoctor = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.doctor_id = ?";

        String filter = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.doctor_id = ? AND appointments.status = ?";

        try {
            connection = dbc.getConnection();
            if (doctor_id.equals("all")) {
                ps = connection.prepareStatement(filterStatus);
                ps.setString(1, status);
            } else if (status.equals("all")) {
                ps = connection.prepareStatement(filterDoctor);
                ps.setString(1, doctor_id);
            } else {
                ps = connection.prepareStatement(filter);
                ps.setString(1, doctor_id);
                ps.setString(2, status);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString(2));
                Account account = new Account(rs.getString(3));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
    
    public List<Appointment> getFilterByStaff(String doctor_id, String status, String staff) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String filterStatus = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.status = ? and appointments.staff = ?";

        String filterDoctor = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.doctor_id = ? and appointments.staff = ?";

        String filter = "Select appointments.appointment_id , doctor.doctor_name, users.name , appointments.date ,"
                + "appointments.time, appointments.status from appointments "
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username where appointments.doctor_id = ? AND appointments.status = ? AND appointments.staff = ?";

        try {
            connection = dbc.getConnection();
            if (doctor_id.equals("all")) {
                ps = connection.prepareStatement(filterStatus);
                ps.setString(1, status);
                ps.setString(2, staff);
            } else if (status.equals("all")) {
                ps = connection.prepareStatement(filterDoctor);
                ps.setString(1, doctor_id);
                ps.setString(2, staff);
            } else {
                ps = connection.prepareStatement(filter);
                ps.setString(1, doctor_id);
                ps.setString(2, status);
                ps.setString(3, staff);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getString(2));
                Account account = new Account(rs.getString(3));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public Appointment getAppointmentByID(int id) throws SQLException, IOException {
        String sql = "select doctor.img, doctor.doctor_name, doctor.phone, doctor.gender, "
                + "category_service.name , users.img, users.name, users.phone, "
                + "users.gender, patient.DOB, appointments.date ,appointments.time, "
                + "appointments.status, appointments.description, staff.name, "
                + "appointments.fee, appointments.appointment_id, staff.username from appointments inner join doctor "
                + "on appointments.doctor_id = doctor.doctor_id "
                + "inner join patient on appointments.patient_id = patient.patient_id "
                + "inner join users on patient.username = users.username "
                + "inner join users staff on appointments.staff = staff.username "
                + "inner join category_service on doctor.category_id = category_service.id "
                + "where appointments.appointment_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String doctorImage = null;
                String patientImage = null;
                Blob doctorBlob = rs.getBlob(1);
                Blob patientBlob = rs.getBlob(6);
                if (doctorBlob != null) {
                    InputStream inputStream = doctorBlob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    doctorImage = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    doctorImage = "default";
                }

                if (patientBlob != null) {
                    InputStream inputStream = patientBlob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    patientImage = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    patientImage = "default";
                }
                Doctor doctor = new Doctor(doctorImage, rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getString(5));
                Account account = new Account(patientImage, rs.getString(7), rs.getInt(8), rs.getBoolean(9));
                Account staff = new Account(rs.getString(18),rs.getString(15));
                Patient patient = new Patient(account, rs.getDate(10));
                return new Appointment(rs.getInt(17) ,patient, doctor, staff, rs.getDate(11), rs.getTime(12), rs.getString(13), rs.getDouble(16), rs.getString(14));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    
    public void Update(int id, String staff, String status, double fee) throws SQLException {
        String sql = "UPDATE `doctris_system`.`appointments` SET `staff` = ?, `status` = ?, `fee` = ? WHERE (`appointment_id` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, staff);
            ps.setString(2, status);
            ps.setDouble(3, fee);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Appointment> getListByPage(List<Appointment> list,
            int start, int end) {
        ArrayList<Appointment> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
}
