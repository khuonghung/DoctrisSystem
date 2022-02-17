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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.*;

/**
 *
 * @author Khuong Hung
 */
@MultipartConfig(maxFileSize = 16177216)
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
        List<Account> accountlist = null;
        String url = null;
        String alert = null;
        String message = null;
        String action = request.getParameter("action");
        try {
            List<Role> rolelist = roledao.getRole();
            if (action.equals("all")) {
                url = "account?action=all";
                accountlist = userdao.getAllAccount();
            }
            if (action.equals("update")) {
                String username = request.getParameter("username");
                int role_id = Integer.parseInt(request.getParameter("role_id"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                userdao.UpdateRoleStatus(username, role_id, status);
                response.sendRedirect("account?action=all");
            }

            if (action.equals("detail")) {
                String username = request.getParameter("username");
                Account account = new Account();
                account = userdao.getAccountByUsername(username);
                request.setAttribute("role", rolelist);
                request.setAttribute("account", account);
                request.getRequestDispatcher("admin/accountdetail.jsp").forward(request, response);
            }

            if (action.equals("update_image")) {
                String username = request.getParameter("username");
                Part image = request.getPart("image");
                if (image != null) {
                    try {
                        userdao.UpdateImage(username, image);
                    } catch (Exception e) {
                    }
                }
                alert = "success";
                message = "Cập nhật ảnh thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("account?action=detail&username=" + username).forward(request, response);
            }

            if (action.equals("update_account")) {
                String username = request.getParameter("username");
                String name = request.getParameter("name");
                int phone = Integer.parseInt(request.getParameter("phone"));
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                int role_id = Integer.parseInt(request.getParameter("role_id"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                userdao.UpdateAccount(username, name, phone, gender, role_id, status);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("account?action=detail&username=" + username).forward(request, response);
            }

            if (action.equals("filter")) {
                String role_id = request.getParameter("role_id");
                String status = request.getParameter("status");
                request.setAttribute("role_id", role_id);
                request.setAttribute("status", status);
                if (role_id.equals("all") && status.equals("all")) {
                    response.sendRedirect("account?action=all");
                } else if (role_id.equals("all")) {
                    url = "account?action=filter&role_id=" + role_id + "&status=" + status;
                    accountlist = userdao.getFilterByStatus(status);
                } else if (status.equals("all")) {
                    url = "account?action=filter&status=" + status + "&role_id=" + role_id;
                    accountlist = userdao.getFilterByRole(role_id);
                } else {
                    url = "account?action=filter&status=" + status + "&role_id=" + role_id;
                    accountlist = userdao.getFilter(status, role_id);
                }
            }

            if (action.equals("search")) {
                String text = request.getParameter("txt");
                text = text.replaceFirst("^0+(?!$)", "");
                url = "account?action=search&txt=" + text;
                accountlist = userdao.SearchALl(text);
            }

            if (accountlist != null) {
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
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("account", account);
                request.setAttribute("role", rolelist);
                request.getRequestDispatcher("admin/account.jsp").forward(request, response);
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
