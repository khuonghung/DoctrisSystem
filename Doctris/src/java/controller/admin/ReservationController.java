/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.*;
import model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Khuong Hung
 */
public class ReservationController extends HttpServlet {

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
        String action = request.getParameter("action");
        List<Reservation> reservationlist = null;
        ReservationDAO reservationdao = new ReservationDAO();
        ServiceDAO servicedao = new ServiceDAO();
        UserDAO userdao = new UserDAO();
        String url = null;
        String alert = null;
        String message = null;
        try {
            List<Service> servicelist = servicedao.getServiceNameAndID();
            if(action.equals("all")){
                reservationlist = reservationdao.getReservationList();
                url = "reservationmanage?action=all";
            }
            if (action.equals("filter")) {
                String service_id = request.getParameter("service_id");
                String status = request.getParameter("status");
                request.setAttribute("service_id", service_id);
                request.setAttribute("status", status);
                if (service_id.equals("all") && status.equals("all")) {
                    response.sendRedirect("reservationmanage?action=all");
                } else {
                    reservationlist = reservationdao.getFilter(service_id, status);
                }
                url = "reservationmanage?action=filter&service_id=" + service_id + "&status=" + status;
            }
            if(action.equals("detail")){
                int id = Integer.parseInt(request.getParameter("id"));
                Reservation reservation = reservationdao.getReservationByID(id);
                List<Account> stafflist = userdao.getAllStaff();
                request.setAttribute("staff", stafflist);
                request.setAttribute("reservation", reservation);
                request.getRequestDispatcher("admin/reservationdetail.jsp").forward(request, response);
            }
            if(action.equals("update")){
                int id = Integer.parseInt(request.getParameter("id"));
                String staff = request.getParameter("staff");
                reservationdao.StaffUpdate(id, staff);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("reservationmanage?action=detail&id=" + id).forward(request, response);
            }
            if (reservationlist != null) {
                int page, numperpage = 8;
                int type = 0;
                int size = reservationlist.size();
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
                List<Reservation> reservation = reservationdao.getListByPage(reservationlist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("reservation", reservation);
                request.setAttribute("service", servicelist);
                request.getRequestDispatcher("admin/reservation.jsp").forward(request, response);
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
