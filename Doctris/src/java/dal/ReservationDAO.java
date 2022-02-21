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
import java.util.Base64;
import java.util.List;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class ReservationDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Reservation> getReservationList() throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service(0, rs.getString(3));
                Account account = new Account(rs.getString(2));
                Patient patient = new Patient(account);
                list.add(new Reservation(rs.getInt(1), patient, service, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Reservation> getFilter(String service_id, String status) throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String filterStatus = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.status = ?";

        String filterDoctor = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.service_id = ?";

        String filter = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.service_id = ? AND reservations.status = ?";

        try {
            connection = dbc.getConnection();
            if (service_id.equals("all")) {
                ps = connection.prepareStatement(filterStatus);
                ps.setString(1, status);
            } else if (status.equals("all")) {
                ps = connection.prepareStatement(filterDoctor);
                ps.setString(1, service_id);
            } else {
                ps = connection.prepareStatement(filter);
                ps.setString(1, service_id);
                ps.setString(2, status);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service(0, rs.getString(3));
                Account account = new Account(rs.getString(2));
                Patient patient = new Patient(account);
                list.add(new Reservation(rs.getInt(1), patient, service, rs.getDate(4), rs.getTime(5), rs.getString(6)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public Reservation getReservationByID(int id) throws SQLException, IOException {
        String sql = "SELECT reservations.reservation_id, users.img , users.name, users.phone, users.gender, \n"
                + "patient.DOB, service.img, service.title, category_service.name, service.fee ,reservations.date, \n"
                + "reservations.time, reservations.status, staff.name, reservations.description \n"
                + "FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username\n"
                + "inner join category_service on service.category_id = category_service.id\n"
                + "inner join users staff on reservations.staff = staff.username where reservations.reservation_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String serviceImage = null;
                String patientImage = null;
                Blob serviceBlob = rs.getBlob(7);
                Blob patientBlob = rs.getBlob(2);
                if (serviceBlob != null) {
                    InputStream inputStream = serviceBlob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    serviceImage = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    serviceImage = "default";
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
                Setting setting = new Setting(rs.getString(9));
                Service service = new Service(setting, rs.getString(8), rs.getDouble(10), serviceImage);
                Account account = new Account(patientImage, rs.getString(3), rs.getInt(4), rs.getBoolean(5));
                Account staff = new Account(rs.getString(14));
                Patient patient = new Patient(account, rs.getDate(6));
                return new Reservation(rs.getInt(1), patient, service, rs.getDate(11), rs.getTime(12),rs.getString(13), staff , rs.getString(15));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    
    public void StaffUpdate(int id, String staff) throws SQLException {
        String sql = "UPDATE `reservations` SET `staff` = ? WHERE (`reservation_id` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, staff);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Reservation> getListByPage(List<Reservation> list,
            int start, int end) {
        ArrayList<Reservation> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
