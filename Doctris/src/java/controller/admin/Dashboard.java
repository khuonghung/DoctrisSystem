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
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.Reservation;
import model.Statistic;

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
        HttpSession session = request.getSession();
        try {
            String action = request.getParameter("action");
            List<Appointment> appointmentlist = appointmentdao.getAppointmentListInDay();
            List<Reservation> reservationlist = reservationdao.getReservationListInDay();
            if (action.equals("default")) {
                List<Statistic> appointment7day = appointmentdao.getDataLast7Day("7day");
                List<Statistic> reservation7day = reservationdao.getDataLast7Day("7day");
                request.setAttribute("Revenue", reservationdao.SumFee("month") + appointmentdao.SumFee("month"));
                request.setAttribute("appointment7day", appointment7day);
                request.setAttribute("reservation7day", reservation7day);
                session.setAttribute("atype", "7day");
                session.setAttribute("rtype", "month");
                request.setAttribute("Revenueappointment", appointmentdao.SumFee("month"));
                request.setAttribute("Revenuereservation", reservationdao.SumFee("month"));  
            }
            if (action.contains("statistic")) {
                String atype = request.getParameter("atype");
                String rtype = request.getParameter("rtype");
                List<Statistic> appointment7day = appointmentdao.getDataLast7Day(atype);
                List<Statistic> reservation7day = reservationdao.getDataLast7Day(atype);
                request.setAttribute("Revenue", reservationdao.SumFee(rtype) + appointmentdao.SumFee(rtype));
                request.setAttribute("Revenueappointment", appointmentdao.SumFee(rtype));
                request.setAttribute("Revenuereservation", reservationdao.SumFee(rtype)); 
                session.setAttribute("atype", atype);
                session.setAttribute("rtype", rtype);
                request.setAttribute("appointment7day", appointment7day);
                request.setAttribute("reservation7day", reservation7day);
            }
            request.setAttribute("patient", patientdao.CountPatient());
            request.setAttribute("appointment", appointmentdao.CountAppointment());
            request.setAttribute("reservation", reservationdao.CountReservation());
            request.setAttribute("doctor", doctordao.CountDoctor());
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
