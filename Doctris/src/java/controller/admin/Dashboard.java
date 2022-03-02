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
import dal.*;
import java.util.List;
import model.Appointment;
import model.Reservation;

/**
 *
 * @author Khuong Hung
 */
public class Dashboard extends HttpServlet {

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
        AppointmentDAO appointmentdao = new AppointmentDAO();
        ReservationDAO reservationdao = new ReservationDAO();
        PatientDao patientdao = new PatientDao();
        DoctorDAO doctordao = new DoctorDAO();
        try {
            List<Appointment> appointmentlist = appointmentdao.getAppointmentListInDay();
            List<Reservation> reservationlist = reservationdao.getReservationListInDay();
            int patient = patientdao.CountPatient();
            int doctor = doctordao.CountDoctor();
            int reservation = reservationdao.CountReservation();
            int appointment = appointmentdao.CountAppointment();
            int Revenue = reservationdao.SumFee() + appointmentdao.SumFee();
            request.setAttribute("appointment", appointment);
            request.setAttribute("reservation", reservation);
            request.setAttribute("patient", patient);
            request.setAttribute("doctor", doctor);
            request.setAttribute("Revenue", Revenue);
            request.setAttribute("appointmentlist", appointmentlist);
            request.setAttribute("reservationlist", reservationlist);
            request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
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
