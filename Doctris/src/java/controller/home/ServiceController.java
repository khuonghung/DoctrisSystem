/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

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
import model.RateStar;
import model.Service;

/**
 *
 * @author Khuong Hung
 */
public class ServiceController extends HttpServlet {

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
        ServiceDAO servicedao = new ServiceDAO();
        String url = null;
        ArrayList<Service> AllService = new ArrayList<>();
        ArrayList<Service> speciality = new ArrayList<>();

        try {
            if (action.equals("all")) {
                url = "service?action=all";
                AllService = servicedao.getAllService();
                speciality = servicedao.getAllSpeciality();
            }
            if (action.equals("search")) {
                String search = request.getParameter("search");
                AllService = servicedao.Search(search);
                url = "service?action=search";
                speciality = servicedao.getAllSpeciality();
            }
            if (action.equals("filter")) {
                String search = request.getParameter("search");
                String Speciality = request.getParameter("Speciality");
                String sort = request.getParameter("sort");
                switch (sort) {
                    case "1":
                        sort = " order by s.title asc";
                        break;
                    case "2":
                        sort = " order by s.title desc";
                        break;
                    case "3":
                        sort = " order by s.fee asc";
                        break;
                    case "4":
                        sort = " order by s.fee desc";
                        break;
                    default:
                        break;
                }
                AllService = servicedao.getServiceFiltered(Speciality, sort);
                url = "service?action=filter";
                speciality = servicedao.getAllSpeciality();
            }
            if (action.equals("detail")) {
                String allow = request.getRequestURI() + "?" + request.getQueryString();
                if (allow.contains("allow=true")) {
                    allow = "true";
                }
                String id = request.getParameter("id");
                Service service = new Service();
                service = servicedao.getServiceById(id);
                List<RateStar> rate = servicedao.getRateService(Integer.parseInt(id));
                request.setAttribute("service", service);
                request.setAttribute("rate", rate);
                request.setAttribute("allow", allow);
                request.getRequestDispatcher("servicedetail.jsp").forward(request, response);
            }
            if (AllService != null) {
                int page, numperpage = 6;
                int size = AllService.size();
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
                ArrayList<Service> serviceList = servicedao.getListByPage(AllService, start, end);
                request.setAttribute("page", page);
                request.setAttribute("speciality", speciality);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("serviceList", serviceList);
                request.getRequestDispatcher("service.jsp").forward(request, response);
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
