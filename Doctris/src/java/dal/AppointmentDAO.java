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
                Account account = new Account(patientImage, rs.getString(7), rs.getInt(8), rs.getBoolean(9), null);
                Account staff = new Account(rs.getString(18), rs.getString(15));
                Patient patient = new Patient(account, rs.getDate(10));
                return new Appointment(rs.getInt(17), patient, doctor, staff, rs.getDate(11), rs.getTime(12), rs.getString(13), rs.getDouble(16), rs.getString(14));
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

    public int CountAppointment() {
        int count = 0;
        String sql = "select count(*) from appointments where status = 'Complete' AND month(appointments.date) = month(CURRENT_DATE)";
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
        String month = "select sum(fee) from appointments where status = 'Complete' AND month(appointments.date) = month(CURRENT_DATE)";
        String today = "select sum(fee) from appointments where status = 'Complete' AND appointments.date = CURRENT_DATE";
        String day7 = "select sum(fee) from appointments where status = 'Complete' AND appointments.date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 7) DAY AND CURRENT_DATE";
        String day14 = "select sum(fee) from appointments where status = 'Complete' AND appointments.date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 14) DAY AND CURRENT_DATE";
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

    public List<Appointment> getAppointmentListInDay() throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "Select appointments.appointment_id , doctor.doctor_name, users.name , \n"
                + "appointments.date ,appointments.time, appointments.status from appointments \n"
                + "inner join doctor on appointments.doctor_id = doctor.doctor_id inner join patient on \n"
                + "appointments.patient_id = patient.patient_id inner join users on patient.username = users.username where appointments.date = cast(CURDATE() as Date);";
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

    public List<Statistic> getDataLast7Day(String type) {
        List<Statistic> list = new ArrayList<>();
        String day7 = "    \n"
                + "    Select p.day , coalesce(count(u.appointment_id), 0) as count from (\n"
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
                + "left join appointments as u on p.day = u.date group by p.day order by p.day asc";

        String day14 = "Select p.day , coalesce(count(u.appointment_id), 0) as count from (\n"
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
                + "                left join appointments as u on p.day = u.date group by p.day order by p.day asc";

        String day3 = "Select p.day , coalesce(count(u.appointment_id), 0) as count from (\n"
                + "                    Select curdate() as day\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 1 day)\n"
                + "                          union\n"
                + "                    Select date_sub(Curdate(),interval 2 day)\n"
                + "                    )as p left join appointments as u on p.day = u.date group by p.day order by p.day asc";
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

    public List<Appointment> getAppointmentHistory(int patient_id) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "Select appointments.appointment_id, doctor.doctor_id, doctor.img, "
                + "doctor.doctor_name, users.name , appointments.date ,appointments.time, "
                + "appointments.status from appointments inner join doctor "
                + "on appointments.doctor_id = doctor.doctor_id inner join patient "
                + "on appointments.patient_id = patient.patient_id inner join users "
                + "on patient.username = users.username where appointments.patient_id = ? order by appointments.appointment_id DESC";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String doctorImage = null;
                Blob doctorBlob = rs.getBlob(3);
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
                Doctor doctor = new Doctor(rs.getInt(2), doctorImage, rs.getString(4));
                Account account = new Account(rs.getString(5));
                Patient patient = new Patient(account);
                list.add(new Appointment(rs.getInt(1), patient, doctor, rs.getDate(6), rs.getTime(7), rs.getString(8)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void Booking(int doctor_id, int patient_id, String staff, String date, String time, String description, String status, double fee, String payment) throws SQLException, IOException {
        String sql = "INSERT INTO `appointments` (`doctor_id`, `patient_id`, `staff`, `date`, `time`, `description`, `status`, `fee`, `payment`) \n"
                + "VALUES (?, ?, ?, STR_TO_DATE(?,'%d/%m/%Y'), ?, ?, ?, ?, ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            ps.setInt(2, patient_id);
            ps.setString(3, staff);
            ps.setString(4, date);
            ps.setString(5, time);
            ps.setString(6, description);
            ps.setString(7, status);
            ps.setDouble(8, fee);
            ps.setString(9, payment);
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
        String sql = "SELECT appointment_id FROM appointments where patient_id = ? order by appointment_id desc limit 1";
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
        String sql = "UPDATE `appointments` SET `payment` = ? WHERE (`appointment_id` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
     public List<Appointment> getAppointmentByPatient(int doctor_id, int patient_id) throws SQLException, IOException {
        List<Appointment> list = new ArrayList<>();
        String sql = "select a.date,a.time,a.status from appointments a inner join patient p\n"
                + "on a.patient_id = p.patient_id \n"
                + "where a.doctor_id = ? and p.patient_id = ? ORDER BY a.date ASC";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            ps.setInt(2, patient_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(rs.getDate(1), rs.getTime(2), rs.getString(3)));
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
