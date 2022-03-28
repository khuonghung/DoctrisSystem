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

    public List<Reservation> getReservationListByStaff(String staff) throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.staff = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, staff);
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

    public List<Reservation> getFilterByStaff(String service_id, String status, String staff) throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String filterStatus = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.status = ? AND reservations.staff = ?";

        String filterDoctor = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.service_id = ? AND reservations.staff = ?";

        String filter = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.service_id = ? AND reservations.status = ? AND reservations.staff = ?";

        try {
            connection = dbc.getConnection();
            if (service_id.equals("all")) {
                ps = connection.prepareStatement(filterStatus);
                ps.setString(1, status);
                ps.setString(2, staff);
            } else if (status.equals("all")) {
                ps = connection.prepareStatement(filterDoctor);
                ps.setString(1, service_id);
                ps.setString(2, staff);
            } else {
                ps = connection.prepareStatement(filter);
                ps.setString(1, service_id);
                ps.setString(2, status);
                ps.setString(3, staff);
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
                + "reservations.time, reservations.status, staff.name, reservations.description, staff.username \n"
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
                Account account = new Account(patientImage, rs.getString(3), rs.getInt(4), rs.getBoolean(5), null);
                Account staff = new Account(rs.getString(16), rs.getString(14));
                Patient patient = new Patient(account, rs.getDate(6));
                return new Reservation(rs.getInt(1), patient, service, rs.getDate(11), rs.getTime(12), rs.getString(13), staff, rs.getString(15));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void StaffUpdate(int id, String staff, String status) throws SQLException {
        String sql = "UPDATE `reservations` SET `staff` = ?, `status` = ? WHERE (`reservation_id` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, staff);
            ps.setString(2, status);
            ps.setInt(3, id);
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

    public int CountReservation() {
        int count = 0;
        String sql = "select count(*) from reservations where reservations.status = 'Complete' AND month(reservations.date) = month(CURRENT_DATE)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public int SumFee(String type) {
        int sum = 0;
        String month = "select sum(service.fee) from reservations inner join service on reservations.service_id = service.service_id  ";
        String today = "select sum(service.fee) from reservations inner join service on reservations.service_id = service.service_id where reservations.status = 'Complete' AND reservations.date = CURRENT_DATE";
        String day7 = "select sum(service.fee) from reservations inner join service on reservations.service_id = service.service_id where reservations.status = 'Complete' AND reservations.date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 7 DAY) AND CURRENT_DATE";
        String day14 = "select sum(service.fee) from reservations inner join service on reservations.service_id = service.service_id where reservations.status = 'Complete' AND reservations.date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 14 DAY) AND CURRENT_DATE";
        try {
            connection = dbc.getConnection();
            if (type.equals("7day")) {
                ps = connection.prepareStatement(day7);
            }
            if (type.equals("14day")) {
                ps = connection.prepareStatement(day14);
            }
            if (type.equals("today")) {
                ps = connection.prepareStatement(today);
            }
            if (type.equals("month")) {
                ps = connection.prepareStatement(month);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return sum;
    }

    public List<Reservation> getReservationListInDay() throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.date = cast(CURDATE() as Date)";
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

    public List<Statistic> getDataLast7Day(String type) {
        List<Statistic> list = new ArrayList<>();
        String day7 = "Select p.day , coalesce(count(u.reservation_id), 0) as count from (\n"
                + "    Select curdate() as day\n"
                + "          union\n"
                + "    Select date_sub(Curdate(),interval 1 day)\n"
                + "          union\n"
                + "    Select date_sub(Curdate(),interval 2 day)\n"
                + "          union\n"
                + "    Select date_sub(Curdate(),interval 3 day)\n"
                + "          union\n"
                + "    Select date_sub(Curdate(),interval 4 day)\n"
                + "          union\n"
                + "    Select date_sub(Curdate(),interval 5 day)\n"
                + "         union\n"
                + "    Select date_sub(Curdate(),interval 6 day))as p\n"
                + "left join reservations as u on p.day = u.date group by p.day order by p.day asc";

        String day14 = "Select p.day , coalesce(count(u.reservation_id), 0) as count from (\n"
                + "                    Select curdate() as day\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 1 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 2 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 3 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 4 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 5 day)\n"
                + "                         union\n"
                + "                    Select date_sub(Curdate(),interval 6 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 7 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 8 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 9 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 10 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 11 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 12 day)\n"
                + "                    union\n"
                + "                    Select date_sub(Curdate(),interval 13 day)\n"
                + "                    )as p\n"
                + "                    \n"
                + "                left join reservations as u on p.day = u.date group by p.day order by p.day asc";

        String day3 = "Select p.day , coalesce(count(r.reservation_id), 0) as count from (\n"
                + "                    Select curdate() as day\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 1 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 2 day)\n"
                + "                    )as p\n"
                + "                left join reservations as r on p.day = r.date group by p.day order by p.day asc";
        try {
            connection = dbc.getConnection();
            if (type.equals("7day")) {
                ps = connection.prepareStatement(day7);
            }
            if (type.equals("14day")) {
                ps = connection.prepareStatement(day14);
            }
            if (type.equals("3day")) {
                ps = connection.prepareStatement(day3);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Statistic(rs.getDate(1), rs.getInt(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Reservation> getReservationListHistory(int patient_id) throws SQLException, IOException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT reservations.reservation_id, users.name, service.title, "
                + "reservations.date, reservations.time, reservations.status, service.fee,service.service_id FROM reservations \n"
                + "inner join service on reservations.service_id = service.service_id \n"
                + "inner join patient on reservations.patient_id = patient.patient_id \n"
                + "inner join users on patient.username = users.username where reservations.patient_id = ? order by reservations.reservation_id DESC";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service(rs.getInt(8), rs.getString(3), rs.getDouble(7));
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

    public void Booking(int service_id, int patient_id, String staff, String date, String time, String description, String status, String payment) throws SQLException {
        String sql = "INSERT INTO `reservations` (`patient_id`, `service_id`, "
                + "`staff`, `date`, `time`, `status`, `description`, `payment`) VALUES "
                + "(?, ?, ?, STR_TO_DATE(?,'%d/%m/%Y'), ?, ?, ?, ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            ps.setInt(2, service_id);
            ps.setString(3, staff);
            ps.setString(4, date);
            ps.setString(5, time);
            ps.setString(6, status);
            ps.setString(7, description);
            ps.setString(8, payment);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int getLastBooking(int patient_id) {
        int id = 0;
        String sql = "SELECT reservation_id FROM reservations where patient_id = ? order by reservation_id desc limit 1;";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return id;
    }

    public void UpdateStatus(int id, String status) {
        String sql = "UPDATE `reservations` SET `payment` = ? WHERE (`reservation_id` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
