/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.ServiceDAO;
import dal.DoctorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.RateStar;
import model.Service;
import model.Setting;

/**
 *
 * @author Khuong Hung
 */
@MultipartConfig(maxFileSize = 16177216)
public class ServiceManage extends HttpServlet {

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
        ServiceDAO servicedao = new ServiceDAO();
        String action = request.getParameter("action");
        String url = null;
        String alert = null;
        String message = null;
        List<Service> servicelist = null;
        List<Setting> catetogory_name = null;
        try {
            catetogory_name = servicedao.getCatetogoryService();
            if (action.equals("all")) {
                servicelist = servicedao.getAllServices();
                url = "servicemanage?action=all";
            }
            
            if (action.equals("filter")) {
                String category = request.getParameter("category");
                if (category.equals("all")) {
                    response.sendRedirect("servicemanage?action=all");
                } else {
                    servicelist = servicedao.getFilter(category);
                }
                request.setAttribute("category1", category);
                url = "servicemanage?action=filter&category=" + category;
            }
            
            if (action.equals("search")) {
                String txt = request.getParameter("txt");
                servicelist = servicedao.getSearch(txt);
                url = "servicemanage?action=search&txt=" + txt;
            }
            
            if (action.equals("update_status")){
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                int service_id = Integer.parseInt(request.getParameter("id"));
                servicedao.UpdateStatus(status, service_id);
                response.sendRedirect("servicemanage?action=all");
            }
            
            if (action.equals("detail")) {
                int service_id = Integer.parseInt(request.getParameter("id"));
                Service service = new Service();
                service = servicedao.getServiceByID(service_id);
                request.setAttribute("catetogory", catetogory_name);
                request.setAttribute("service", service);
                request.getRequestDispatcher("admin/servicedetail.jsp").forward(request, response);
            }
            
            if (action.equals("update_image")) {
                int service_id = Integer.parseInt(request.getParameter("id"));
                Part image = request.getPart("image");
                if (image != null) {
                    try {
                        servicedao.UpdateImage(service_id, image);
                    } catch (Exception e) {
                    }
                }
                alert = "success";
                message = "Cập nhật ảnh thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("servicemanage?action=detail&id=" + service_id).forward(request, response);
            }
            
            if (action.equals("update_info")) {
                int service_id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                double fee = Double.parseDouble(request.getParameter("fee"));
                String description = request.getParameter("description");
                int catetogory = Integer.parseInt(request.getParameter("catetogory"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                servicedao.ServiceUpdate(service_id, title, fee, description, catetogory, status);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("servicemanage?action=detail&id=" + service_id).forward(request, response);
            }
            
            if (action.equals("add")) {
                request.setAttribute("catetogory", catetogory_name);
                request.getRequestDispatcher("admin/addservice.jsp").forward(request, response);
            }
            
            if (action.equals("addnew")) {
                String title = request.getParameter("title");
                double fee = Double.parseDouble(request.getParameter("fee"));
                String description = request.getParameter("description");
                int catetogory = Integer.parseInt(request.getParameter("catetogory"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                Part image = request.getPart("image");
                servicedao.ServiceADD(title, fee, description, catetogory, status, image);
                alert = "success";
                message = "Thêm mới thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("servicemanage?action=all").forward(request, response);
            }
            
            if (action.equals("viewfeedback")) {
                int service_id = Integer.parseInt(request.getParameter("id"));
                List<RateStar> ratestars = servicedao.getFeedback(service_id);
                request.setAttribute("ratestars", ratestars);
                request.getRequestDispatcher("admin/servicefeedback.jsp").forward(request, response);
            }
            
            if (servicelist != null) {
                int page, numperpage = 8;
                int size = servicelist.size();
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
                List<Service> service = servicedao.getListByPage(servicelist, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("service", service);
                request.setAttribute("category", catetogory_name);
                request.getRequestDispatcher("admin/service.jsp").forward(request, response);
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
