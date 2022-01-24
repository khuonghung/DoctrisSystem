/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import context.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;
/**
 *
 * @author Khuong Hung
 */
public class UserDAO {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;
    
    /**
     *
     * @param email
     * @param password 
     * @return
     * @throws java.sql.SQLException
     */
    public Account login(String email, String password) throws SQLException {
        String sql = " select * from users where email=? and password=?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt(2));
                return new Account(rs.getString(1),r,rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    
    public Account checkAcc(String email, String username) throws SQLException {
        String sql = "select * from users where email=? or username=?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt(2));
                return new Account(rs.getString(1),r,rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9));
            }
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void Register(String email, String password, String username, int role_id,String name,int phone, boolean gender,String img,boolean status) throws SQLException {
        String sql = "INSERT INTO `doctris_system`.`users` (`username`, `role_id`, `password`, `name`, `gender`, `phone`, `email`,`img`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2,role_id);
            ps.setString(3,password);
            ps.setString(4, name);
            ps.setBoolean(5, gender);
            ps.setInt(6, phone);
            ps.setString(7, email);
            ps.setString(8, img);
            ps.setBoolean(9, status);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public void Recover(String username, String password) throws SQLException {
        String sql = "UPDATE `doctris_system`.`users` SET `password` = ? WHERE (`username` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(2, username);
            ps.setString(1,password);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    public Account checkAccByEmail(String email) throws SQLException {
        String sql = "select * from users where email=?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getInt(2));
                return new Account(rs.getString(1),r,rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9));
            }
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    
    public List<Account> getAllAccount() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT u.username,u.name,u.gender,u.email,u.phone,r.name,u.status,u.img "
                + "FROM doctris_system.users u "
                + "inner join doctris_system.role r "
                + "on u.role_id = r.id";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role(rs.getString(6));
                list.add(new Account(rs.getString(1),r,rs.getString(2),rs.getBoolean(3),rs.getInt(5),rs.getString(4),rs.getString(8),rs.getBoolean(7)));
                
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
