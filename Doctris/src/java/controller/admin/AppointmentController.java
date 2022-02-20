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
import model.Appointment;
import model.Doctor;
import model.Account;
import dal.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Khuong Hung
 */
public class AppointmentController extends HttpServlet {

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
        AppointmentDAO appointmentdao = new AppointmentDAO();
        DoctorDAO doctordao = new DoctorDAO();
        UserDAO userdao = new UserDAO();
        String action = request.getParameter("action");
        List<Appointment> appointmentlist = null;
        String url = null;
        String alert = null;
        String message = null;
        try {
            List<Doctor> doctorlist = doctordao.getDoctorNameAndID();
            if (action.equals("all")) {
                appointmentlist = appointmentdao.getAppointmentList();
                url = "appointmentmanage?action=all";
            }
            if (action.equals("filter")) {
                String doctor_id = request.getParameter("doctor_id");
                String status = request.getParameter("status");
                request.setAttribute("doctor_id", doctor_id);
                request.setAttribute("status", status);
                if (doctor_id.equals("all") && status.equals("all")) {
                    response.sendRedirect("appointmentmanage?action=all");
                } else {
                    appointmentlist = appointmentdao.getFilter(doctor_id, status);
                }
                url = "appointmentmanage?action=filter&doctor_id=" + doctor_id + "&status=" + status;
            }
            if(action.equals("detail")){
                List<Account> stafflist = userdao.getAllStaff();
                int id = Integer.parseInt(request.getParameter("id"));
                Appointment appointment = appointmentdao.getAppointmentByID(id);
                request.setAttribute("staff", stafflist);
                request.setAttribute("appointment", appointment);
                request.getRequestDispatcher("admin/appointmentdetail.jsp").forward(request, response);
            }
            if(action.equals("update")){
                double fee = Double.parseDouble(request.getParameter("fee"));
                int id = Integer.parseInt(request.getParameter("id"));
                String staff = request.getParameter("staff");
                String status = request.getParameter("status");
                appointmentdao.DoctorUpdate(id, staff, status, fee);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("appointmentmanage?action=detail&id=" + id).forward(request, response);
            }
            if (appointmentlist != null) {
                int page, numperpage = 8;
                int type = 0;
                int size = appointmentlist.size();
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
                List<Appointment> appointment = appointmentdao.getListByPage(appointmentlist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("appointment", appointment);
                request.setAttribute("doctor", doctorlist);
                request.getRequestDispatcher("admin/appointment.jsp").forward(request, response);
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
