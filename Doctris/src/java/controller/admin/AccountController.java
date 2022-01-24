/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dal.*;
import java.sql.SQLException;
import java.util.List;
import model.*;

/**
 *
 * @author Khuong Hung
 */
public class AccountController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserDAO userdao = new UserDAO();
        RoleDAO roledao = new RoleDAO();
        String action = request.getParameter("action");
        try {
            if (action == null) {
                List<Account> accountlist = userdao.getAllAccount();
                List<Role> rolelist = roledao.getRole();
                int page, numperpage = 8;
                int type = 0;
                int size = accountlist.size();
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
                List<Account> account = userdao.getListByPage(accountlist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("account", account);
                request.setAttribute("role", rolelist);
                request.getRequestDispatcher("admin/account.jsp").forward(request, response);
            }
            if (action.equals("update")) {
                String username = request.getParameter("username");
                int role_id = Integer.parseInt(request.getParameter("role_id"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                userdao.UpdateAccount(username, role_id, status);
                response.sendRedirect("account");
            }
            if (action.equals("filter")) {
                String role_id = request.getParameter("role_id");
                String status = request.getParameter("status");
                if (role_id.equals("all") && status.equals("all")) {
                    response.sendRedirect("account");
                } else if (role_id.equals("all")) {
                    List<Account> accountlist = userdao.getFilterByStatus(status);
                    List<Role> rolelist = roledao.getRole();
                    int page, numperpage = 8;
                    int type = 0;
                    int size = accountlist.size();
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
                    List<Account> account = userdao.getListByPage(accountlist, start, end);
                    request.setAttribute("type", type);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num);
                    request.setAttribute("account", account);
                    request.setAttribute("role", rolelist);
                    request.getRequestDispatcher("admin/account.jsp").forward(request, response);
                }else if (status.equals("all")) {
                    List<Account> accountlist = userdao.getFilterByRole(role_id);
                    List<Role> rolelist = roledao.getRole();
                    int page, numperpage = 8;
                    int type = 0;
                    int size = accountlist.size();
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
                    List<Account> account = userdao.getListByPage(accountlist, start, end);
                    request.setAttribute("type", type);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num);
                    request.setAttribute("account", account);
                    request.setAttribute("role", rolelist);
                    request.getRequestDispatcher("admin/account.jsp").forward(request, response);
                }else{
                    List<Account> accountlist = userdao.getFilter(status, role_id);
                    List<Role> rolelist = roledao.getRole();
                    int page, numperpage = 8;
                    int type = 0;
                    int size = accountlist.size();
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
                    List<Account> account = userdao.getListByPage(accountlist, start, end);
                    request.setAttribute("type", type);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num);
                    request.setAttribute("account", account);
                    request.setAttribute("role", rolelist);
                    request.getRequestDispatcher("admin/account.jsp").forward(request, response);
                }
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
