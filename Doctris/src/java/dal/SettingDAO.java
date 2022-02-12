/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Khuong Hung
 */
public class SettingDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Setting> getAllSetting() {
        List<Setting> list = new ArrayList<>();
        File file = new File("Setting.xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("setting");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    int id = Integer.valueOf(element.getElementsByTagName("id").item(0).getTextContent());
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    list.add(new Setting(id, name));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Setting> getAll() throws SQLException {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from  doctris_system.role\n"
                + "union\n"
                + "select * from  doctris_system.category_blog\n"
                + "union\n"
                + "select * from  doctris_system.category_service";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(6), rs.getString(5), rs.getInt(4)));
            }

        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Setting> getBySetting(String table) throws SQLException {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from " + table;
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(6), rs.getString(5), rs.getInt(4)));
            }

        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void SettingUpdate(String table, int ID, String value, boolean status, int setting_id, String note, int order) throws SQLException {
        String sql = "UPDATE " + table + " r SET r.name = ?, r.setting_id = ?, r.status = ?, r.note = ?, r.order = ? WHERE (r.id = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            ps.setInt(2, setting_id);
            ps.setBoolean(3, status);
            ps.setInt(6, ID);
            ps.setString(4, note);
            ps.setInt(5, order);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void SettingADD(String table, String value, boolean status, int setting_id, String note, int order) throws SQLException {
        String sql = "INSERT INTO " + table + " (`name`, `setting_id`, `order`,`note`, `status`) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            ps.setInt(2, setting_id);
            ps.setInt(3, order);
            ps.setString(4, note);
            ps.setBoolean(5, status);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Setting> getListByPage(List<Setting> list,
            int start, int end) {
        ArrayList<Setting> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
