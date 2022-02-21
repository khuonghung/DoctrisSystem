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
                Service service = new Service(0,rs.getString(3));
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
                Service service = new Service(0,rs.getString(3));
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
    
    public List<Reservation> getListByPage(List<Reservation> list,
            int start, int end) {
        ArrayList<Reservation> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
