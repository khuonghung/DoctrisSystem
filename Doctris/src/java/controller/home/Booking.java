/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import dal.*;
import java.sql.SQLException;

/**
 *
 * @author Khuong Hung
 */
public class Booking extends HttpServlet {

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
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        DoctorDAO ddao = new DoctorDAO();
        PatientDao pdao = new PatientDao();
        UserDAO udao = new UserDAO();
        ServiceDAO sdao = new ServiceDAO();
        ReservationDAO rdao = new ReservationDAO();
        AppointmentDAO adao = new AppointmentDAO();
        Account user = (Account) session.getAttribute("user");
        Doctor d = (Doctor) session.getAttribute("doctor");
        Service s = (Service) session.getAttribute("service");
        String type = request.getParameter("type");
        try {
            if (type.equals("appointment")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Doctor doctor = ddao.getDetail(id);
                int star = ddao.getStars(doctor.getDoctor_id());
                int feedback = ddao.CountFeedback(doctor.getDoctor_id());
                request.setAttribute("star", star);
                request.setAttribute("feedback", feedback);
                session.setAttribute("doctor", doctor);
                session.setAttribute("type", type);
            }
            if (type.equals("reservation")) {
                String id = request.getParameter("id");
                Service service = sdao.getServiceById(id);
                session.setAttribute("service", service);
                session.setAttribute("type", type);
            }
            if (type.equals("checkout")) {

                String date = request.getParameter("date");
                String time = request.getParameter("time");
                String description = request.getParameter("description");
                String payment = request.getParameter("payment");
                if (payment.equals("default")) {
                    if (session.getAttribute("type").equals("appointment")) {
                        adao.Booking(d.getDoctor_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", d.getFee(), "Pending");
                    }
                    if (session.getAttribute("type").equals("reservation")) {
                        rdao.Booking(s.getService_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", "Pending");
                    }
                }
                response.sendRedirect("home");
                return;
            }
            request.getRequestDispatcher("booking.jsp").forward(request, response);
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
