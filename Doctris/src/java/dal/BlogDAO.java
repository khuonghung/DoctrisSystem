/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.Category_Blog;
import model.SettingDetails;

/**
 *
 * @author Admin
 */
public class BlogDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public ArrayList<Blog> getBlogs() {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            connection = dbc.getConnection();
            String sql = "SELECT * FROM blog";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt("blog_id"));
                b.setTitle(rs.getString("title"));
                b.setImg(rs.getString("img"));
                b.setDate(rs.getDate("date"));
                b.setDescribe("describe");
                Category_Blog c = new Category_Blog();
                c.setId(rs.getInt("category_id"));
                b.setCategory(c);
                blogs.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    
    public Blog getBlog(int id) {
        try {
            connection = dbc.getConnection();
            String sql = "SELECT * FROM blog WHERE blog_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt("blog_id"));
                b.setTitle(rs.getString("title"));
                b.setImg(rs.getString("img"));
                b.setDate(rs.getDate("date"));
                b.setDescribe(rs.getString("describe"));
                Category_Blog c = new Category_Blog();
                c.setId(rs.getInt("category_id"));
                b.setCategory(c);
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Category_Blog> getCategories(){
        ArrayList<Category_Blog> categories = new ArrayList<>();
        try {
            connection = dbc.getConnection();
            String sql = "SELECT * FROM category_blog;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Category_Blog c = new Category_Blog();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                SettingDetails s = new SettingDetails();
                s.setId(rs.getInt("setting_id"));
                c.setSettingDetails(s);
                c.setStatus(rs.getBoolean("status"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
    public ArrayList<Blog> getBlogsByCategory(int id) {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            connection = dbc.getConnection();
            String sql = "SELECT * FROM blog WHERE category_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt("blog_id"));
                b.setTitle(rs.getString("title"));
                b.setImg(rs.getString("img"));
                b.setDate(rs.getDate("date"));
                b.setDescribe("describe");
                Category_Blog c = new Category_Blog();
                c.setId(rs.getInt("category_id"));
                b.setCategory(c);
                blogs.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    
    public ArrayList<Blog> search(String content) {
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            connection = dbc.getConnection();
            String sql = "SELECT * FROM blog WHERE title LIKE ?";
            String keyword = "%"+ content + "%";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt("blog_id"));
                b.setTitle(rs.getString("title"));
                b.setImg(rs.getString("img"));
                b.setDate(rs.getDate("date"));
                b.setDescribe("describe");
                Category_Blog c = new Category_Blog();
                c.setId(rs.getInt("category_id"));
                b.setCategory(c);
                blogs.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    
    public ArrayList<Blog> getListByPage(ArrayList<Blog> list,
            int start, int end) {
        ArrayList<Blog> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
}
