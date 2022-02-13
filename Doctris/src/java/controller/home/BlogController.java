/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.home;

import dal.BlogDAO;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dal.SettingDAO;
import model.*;
import java.util.ArrayList;
import model.Blog;

/**
 *
 * @author Admin
 */
public class BlogController extends HttpServlet {

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
        BlogDAO blogDB = new BlogDAO();
        try {
            if (action == null) {
                ArrayList<Blog> blogs = blogDB.getBlogs();
                
                ArrayList<Category_Blog> categories = blogDB.getCategories();
                
                ArrayList<Blog> featured_blogs = blogDB.getBlogsByFeatured();
                
                //phân trang
                int page, numperpage = 6;
                int type = 0;
                int size = blogs.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                ArrayList<Blog> listblog = blogDB.getListByPage(blogs, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("categories", categories);
                request.setAttribute("blogs", blogs);
                request.setAttribute("featured_blogs", featured_blogs);
                request.setAttribute("listblog", listblog);
                request.getRequestDispatcher("blogList.jsp").forward(request, response);
            }
            
            if (action.equals("detail")) {
                int id = Integer.parseInt(request.getParameter("blog_id"));
                
                Blog blog = blogDB.getBlog(id);
                request.setAttribute("blog", blog);
                
                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);
                
                ArrayList<Blog> featured_blogs = blogDB.getBlogsByFeatured();
                request.setAttribute("featured_blogs", featured_blogs);
                
                request.getRequestDispatcher("blogDetail.jsp").forward(request, response);
            }
            
            if (action.equals("search")) {
                String content = request.getParameter("content");
                
                ArrayList<Blog> blogs = blogDB.search(content);
                request.setAttribute("blogs", blogs);
                ArrayList<Category_Blog> categories = blogDB.getCategories();
                int page, numperpage = 6;
                int type = 0;
                int size = blogs.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                ArrayList<Blog> listblog = blogDB.getListByPage(blogs, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("categories", categories);
                request.setAttribute("blogs", blogs);
                request.setAttribute("listblog", listblog);
                request.getRequestDispatcher("blogList.jsp").forward(request, response);
            }
            
            if (action.equals("category")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                ArrayList<Blog> blogs = blogDB.getBlogsByCategory(id);
                request.setAttribute("blogs", blogs);
                ArrayList<Category_Blog> categories = blogDB.getCategories();
                int page, numperpage = 6;
                int type = 0;
                int size = blogs.size();
                int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                ArrayList<Blog> listblog = blogDB.getListByPage(blogs, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("categories", categories);
                request.setAttribute("blogs", blogs);
                request.setAttribute("listblog", listblog);
                request.getRequestDispatcher("blogList.jsp").forward(request, response);
            }
            
            
            
            
            
            
        } catch (IOException | ServletException e) {
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
