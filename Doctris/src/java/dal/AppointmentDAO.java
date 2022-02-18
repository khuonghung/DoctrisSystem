/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
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
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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
                list.add(new Appointment(rs.getInt(1), patient, doctor, formatter.format(rs.getDate(4)), rs.getTime(5), rs.getString(6)));
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
                list.add(new Appointment(rs.getInt(1), patient, doctor, formatter.format(rs.getDate(4)), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
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
