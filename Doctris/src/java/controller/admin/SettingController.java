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
        SettingDAO settingdao = new SettingDAO();
        try {
            if (action == null) {
                List<Setting> setting = settingdao.getAllSetting();
                List<SettingDetails> settingdetailslist = settingdao.getAll();
                int page, numperpage = 6;
                int type = 0;
                int size = settingdetailslist.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<SettingDetails> settingdetails = settingdao.getListByPage(settingdetailslist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("setting", setting);
                request.setAttribute("settingdetails", settingdetails);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (action.equals("User")) {
                List<Setting> setting = settingdao.getAllSetting();
                List<SettingDetails> settingdetailslist = settingdao.getBySetting("role");
                int page, numperpage = 6;
                int type = 1;
                int size = settingdetailslist.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<SettingDetails> settingdetails = settingdao.getListByPage(settingdetailslist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("setting", setting);
                request.setAttribute("settingdetails", settingdetails);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (action.equals("Category_blog")) {
                List<Setting> setting = settingdao.getAllSetting();
                List<SettingDetails> settingdetailslist = settingdao.getBySetting("category_blog");
                int page, numperpage = 6;
                int type = 6;
                int size = settingdetailslist.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<SettingDetails> settingdetails = settingdao.getListByPage(settingdetailslist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("setting", setting);
                request.setAttribute("settingdetails", settingdetails);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (action.equals("Category_service")) {
                List<Setting> setting = settingdao.getAllSetting();
                List<SettingDetails> settingdetailslist = settingdao.getBySetting("category_service");
                int page, numperpage = 6;
                int type = 6;
                int size = settingdetailslist.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<SettingDetails> settingdetails = settingdao.getListByPage(settingdetailslist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("setting", setting);
                request.setAttribute("settingdetails", settingdetails);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (action.equals("update")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String status = request.getParameter("status");
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
                if (status.equals("true")) {
                    status.equals("1");
                } else {
                    status.equals("0");
                }
                int sstatus = Integer.parseInt(status);
                settingdao.SettingUpdate(table, id, name, sstatus, setting_id);
                response.sendRedirect("setting");
            }
            if (action.equals("delete")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                int id = Integer.parseInt(request.getParameter("id"));
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
                settingdao.SettingDelete(table, id);
                response.sendRedirect("setting");
            }
            if (action.equals("addnew")) {
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                String name = request.getParameter("name");
                String status = request.getParameter("status");
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
                if (status.equals("true")) {
                    status.equals("1");
                } else {
                    status.equals("0");
                }
                int sstatus = Integer.parseInt(status);
                settingdao.SettingADD(table, name, sstatus, setting_id);
                response.sendRedirect("setting");
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
