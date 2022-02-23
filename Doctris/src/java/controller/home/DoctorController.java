/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

import dal.DoctorDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Doctor;
import model.RateStar;
import model.Setting;

/**
 *
 * @author Khuong Hung
 */
public class DoctorController extends HttpServlet {

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
        DoctorDAO doctordao = new DoctorDAO();
        String url = null;
        List<Doctor> getdoctor = null;
        ArrayList<Doctor> doctorall = new ArrayList<>();
        try {
            List<Setting> specialitylist = doctordao.getSpeciality();
            if (action.equals("all")) {
                url = "doctor?action=all";
                getdoctor = doctordao.getAllDoctorHome();
            }
            
            if (action.equals("filter")){
                String gender = request.getParameter("gender");
                String speciality = request.getParameter("speciality");
                request.setAttribute("gender",gender);
                request.setAttribute("speciality1",speciality);
                if(gender.equals("all") && speciality.equals("all")){
                    response.sendRedirect("doctor?action=all");
                }else {
                    getdoctor = doctordao.getFilter(speciality, gender);
                }
                url = "doctor?action=filter&gender=" + gender + "&speciality=" + speciality;
            }
            if (getdoctor != null) {
                for (Doctor doctor : getdoctor) {
                    int star = doctordao.getStars(doctor.getDoctor_id());
                    int feedback = doctordao.CountFeedback(doctor.getDoctor_id());
                    RateStar rateStar = new RateStar(star, feedback);
                    Account a = new Account(doctor.getAccount().getUsername());
                    Setting s = new Setting(doctor.getSetting().getId(), doctor.getSetting().getName(), doctor.getSetting().getSetting_id(), doctor.getSetting().isStatus());
                    doctorall.add(new Doctor(s, doctor.getDoctor_id(), doctor.getRole_id(),
                            doctor.getDoctor_name(), a, doctor.isGender(), doctor.getDOB(),
                            doctor.getPhone(), doctor.getDescription(), doctor.isStatus(),
                            doctor.getImg(), rateStar));
                }
                int page, numperpage = 6;
                int size = doctorall.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<Doctor> doctorlist = doctordao.getListByPage(doctorall, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("speciality", specialitylist);
                request.setAttribute("doctor", doctorlist);
                request.getRequestDispatcher("doctor.jsp").forward(request, response);
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
