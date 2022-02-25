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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.Part;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class ServiceDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Service> getRandomTop6Service() throws SQLException {
        List<Service> list = new ArrayList<>();
        String sql = "select concat_ws(cs.id,s.category_id)id ,cs.name,cs.setting_id,"
                + "cs.status,s.service_id,s.title,s.fee,s.description,s.img "
                + "from doctris_system.service s "
                + "inner join doctris_system.category_service cs "
                + "on s.category_id = cs.id ORDER BY RAND() LIMIT 6";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
                list.add(new Service(s, rs.getInt(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Service> getServiceNameAndID() throws SQLException, IOException {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT service_id, title FROM doctris_system.service";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public Service getServiceByID(int service_id) throws SQLException, IOException {
        String sql = "SELECT c.name,s.status,s.service_id,s.title, s.fee,s.description,s.img FROM service s inner join category_service c \n"
                + "on s.category_id = c.id\n"
                + "where s.service_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, service_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(7);
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
                Setting s = new Setting(rs.getString(1));
                return new Service(s, rs.getBoolean(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getString(6), base64Image);
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public List<Setting> getCatetogoryService() throws SQLException {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from doctris_system.category_service";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void UpdateImage(int service_id, Part img) throws SQLException {
        String sql = "UPDATE `doctris_system`.`service`\n"
                + "SET\n"
                + "`img` = ?\n"
                + "WHERE `service_id` = ?;";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            InputStream image = img.getInputStream();
            ps.setBlob(1, image);
            ps.setInt(2, service_id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void ServiceUpdate(int id, String title, double fee, String description, int catetogory, boolean status) throws SQLException {
        String sql1 = "UPDATE `doctris_system`.`service`\n"
                + "SET\n"
                + "`category_id` = ?,\n"
                + "`title` = ?,\n"
                + "`fee` = ?,\n"
                + "`description` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `service_id` = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql1);
            ps.setInt(1, catetogory);
            ps.setString(2, title);
            ps.setDouble(3, fee);
            ps.setString(4, description);
            ps.setBoolean(5, status);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void ServiceADD(String title, double fee, String description, int catetogory, boolean status, Part img) throws SQLException {
        String sql = "INSERT INTO `doctris_system`.`service`\n"
                + "(`category_id`,\n"
                + "`title`,\n"
                + "`fee`,\n"
                + "`description`,\n"
                + "`img`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            InputStream image = img.getInputStream();
            ps.setInt(1, catetogory);
            ps.setString(2, title);
            ps.setDouble(3, fee);
            ps.setString(4, description);
            ps.setBlob(5, image);
            ps.setBoolean(6, status);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<RateStar> getFeedback(int service_id) throws SQLException, IOException {
        List<RateStar> list = new ArrayList<>();
        String sql = "SELECT u.img, u.name, r.star,r.feedback FROM doctris_system.ratestar r "
                + "inner join doctris_system.users u on r.username = u.username where r.service_id = ? ORDER BY r.datetime DESC";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, service_id);
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
                Account s = new Account(base64Image, rs.getString(2), 0, false);
                list.add(new RateStar(s, rs.getInt(3), rs.getString(4)));
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
