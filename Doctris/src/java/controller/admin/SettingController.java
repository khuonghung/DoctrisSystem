/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dal.SettingDAO;
import java.sql.SQLException;
import model.*;
import java.util.List;

/**
 *
 * @author Khuong Hung
 */
public class SettingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        String url = null;
        String alert = null;
        String message = null;
        List<Setting> setting = null;
        List<Setting> settingdetailslist = null;
        SettingDAO settingdao = new SettingDAO();
        try {
            if (action.equals("all")) {
                url = "setting?action=all";
                setting = settingdao.getAllSetting();
                settingdetailslist = settingdao.getAll();
            }
            if (action.equals("User")) {
                url = "setting?action=User";
                setting = settingdao.getAllSetting();
                settingdetailslist = settingdao.getBySetting("role");
            }
            if (action.equals("Blog")) {
                url = "setting?action=Blog";
                setting = settingdao.getAllSetting();
                settingdetailslist = settingdao.getBySetting("category_blog");
            }
            if (action.equals("Service")) {
                url = "setting?action=Service";
                setting = settingdao.getAllSetting();
                settingdetailslist = settingdao.getBySetting("category_service");
            }
            if (action.equals("update")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                int id = Integer.parseInt(request.getParameter("id"));
                String value = request.getParameter("value");
                int order = Integer.parseInt(request.getParameter("order"));
                String note = request.getParameter("note");
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                String table = null;
                if (setting_id == 1) {
                    table = "role";
                }
                if (setting_id == 2) {
                    table = "category_blog";
                }
                if (setting_id == 3) {
                    table = "category_service";
                }
                settingdao.SettingUpdate(table, id, value, status, setting_id, note, order);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("setting?action=all").forward(request, response);
            }
            if (action.equals("addnew")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                String value = request.getParameter("value");
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                String note = request.getParameter("note");
                int order = Integer.parseInt(request.getParameter("order"));
                String table = null;
                if (setting_id == 1) {
                    table = "role";
                }
                if (setting_id == 2) {
                    table = "category_blog";
                }
                if (setting_id == 3) {
                    table = "category_service";
                }
                settingdao.SettingADD(table, value, status, setting_id, note, order);
                alert = "success";
                message = "Thêm mới thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("setting?action=all").forward(request, response);
            }
            if (action.equals("search")){
                url = "setting?action=search";
                String search = request.getParameter("search");
                setting = settingdao.getAllSetting();
                settingdetailslist = settingdao.Search(search);
            }

            if (setting != null && settingdetailslist != null) {
                int page, numperpage = 8;
                int size = settingdetailslist.size();
                int num = (size % 8 == 0 ? (size / 8) : ((size / 8)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<Setting> settingdetails = settingdao.getListByPage(settingdetailslist, start, end);
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("setting", setting);
                request.setAttribute("settingdetails", settingdetails);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            System.out.println(e);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
