/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.BlogDAO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Account;
import model.Blog;
import model.Category_Blog;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 16177216)
public class BlogManage extends HttpServlet {

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
        BlogDAO blogDB = new BlogDAO();
        ArrayList<Blog> blogs = null;
        String url = null;
        String alert = null;
        String message = null;
        String action = request.getParameter("action");

        try {
            if (action.equals("all")) {

                url = "blogmanage?action=all";

                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);

                ArrayList<Account> authors = blogDB.GetAuthors();
                request.setAttribute("authors", authors);

                blogs = blogDB.getBlogs();
            }

            if (action.equals("search")) {
                String content = request.getParameter("content");
                url = "blogmanage?action=search&txt=" + content;
                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);

                ArrayList<Account> authors = blogDB.GetAuthors();
                request.setAttribute("authors", authors);

                
                request.setAttribute("content", content);
                blogs = blogDB.search(content);
                
            }

            if (action.equals("filter")) {

                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);

                ArrayList<Account> authors = blogDB.GetAuthors();
                request.setAttribute("authors", authors);

                String category_id = request.getParameter("category_id");
                if (category_id.equals("all")) {
                    category_id = "all";
                }
                String author = request.getParameter("author");
                if (author.equals("all")) {
                    author = "all";
                }
                String status = request.getParameter("status");
                if (status.equals("all")) {
                    status = "all";
                }

                request.setAttribute("category_id", category_id);
                request.setAttribute("status", status);
                request.setAttribute("author", author);
                blogs = blogDB.filter(author, status, category_id);
            }

            if (action.equals("hide")) {
                int blog_id = Integer.parseInt(request.getParameter("blog_id"));
                String status = request.getParameter("status");
                if (status.equals("active")) {
                    blogDB.HideBlog(blog_id, 0);
                } else {
                    blogDB.HideBlog(blog_id, 1);
                }
                response.sendRedirect("blogmanage?action=all");
            }

            if (action.equals("sort")) {
                String type = request.getParameter("type");
                request.setAttribute("sort", type);

                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);

                blogs = blogDB.sortBlogs(type);
            }

            if (action.equals("addnew")) {
                Account account = (Account) request.getSession().getAttribute("user");
                String username = account.getUsername();
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                String title = request.getParameter("title");
                String describe = request.getParameter("describe");
                boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                
                Part filePart = request.getPart("image");
                InputStream inputImage = null;
                if (filePart.getSize() != 0) {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                    // Obtains input stream of the upload file
                    inputImage = filePart.getInputStream();
                } else {
                    inputImage = null;
                }
                
                blogDB.AddBlog(category_id, title, inputImage, describe, featured, username, status);

                alert = "success";
                message = "Thêm mới thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("blogmanage?action=all").forward(request, response);
            }

            if (action.equals("detail")) {
                int id = Integer.parseInt(request.getParameter("blog_id"));

                Blog blog = blogDB.getBlog(id);
                request.setAttribute("blog", blog);

                ArrayList<Category_Blog> categories = blogDB.getCategories();
                request.setAttribute("categories", categories);

                request.getRequestDispatcher("admin/blogdetail.jsp").forward(request, response);
            }

            if (action.equals("update")) {
                int blog_id = Integer.parseInt(request.getParameter("blogid"));
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                String title = request.getParameter("title");
                String describe = request.getParameter("describe");
                boolean featured = Boolean.parseBoolean(request.getParameter("featured"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));

                
                blogDB.UpdateBlog(category_id, title, describe, featured, status, blog_id);
                alert = "success";
                message = "Sửa thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("blogmanage?action=all").forward(request, response);

            }
            
            if (action.equals("update_image")) {
                int blog_id = Integer.parseInt(request.getParameter("blog_id"));
                Part image = request.getPart("image");
                if (image != null) {
                    try {
                        blogDB.UpdateImage(blog_id, image);
                    } catch (Exception e) {
                    }
                }
                alert = "success";
                message = "Cập nhật ảnh thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("blogmanage?action=detail&blog_id=" + blog_id).forward(request, response);
            }

            if (blogs != null) {
                int page, numperpage = 6;
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
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("blogs", blogs);
                request.setAttribute("listblog", listblog);
                request.getRequestDispatcher("admin/bloglist.jsp").forward(request, response);
            }
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
