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
import model.Account;
import dal.UserDAO;
import configs.*;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Khuong Hung
 */
public class UserController extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserDAO userdao = new UserDAO();
        String action = request.getParameter("action");
        try {
            if (action.equals("login")) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            if (action.equals("checklogin")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String remember = request.getParameter("remember");
                String enpassword = EncodeData.enCode(password);
                if (Validate.checkEmail(email) == false) {
                    request.setAttribute("error", "Email không hợp lệ !");
                    request.getRequestDispatcher("user?action=login").forward(request, response);
                } else {
                    Account account = userdao.login(email, enpassword);
                    if (account == null) {
                        request.setAttribute("error", "Tài khoản không tồn tại !");
                        request.getRequestDispatcher("user?action=login").forward(request, response);
                    } else {
                        session.setAttribute("user", account);
                        Cookie cemail = new Cookie("email", email);
                        Cookie cpass = new Cookie("pass", password);
                        Cookie rem = new Cookie("remember", remember);
                        if (remember != null) {
                            cemail.setMaxAge(60 * 60 * 24 * 30);
                            cpass.setMaxAge(60 * 60 * 24 * 3);
                            rem.setMaxAge(60 * 60 * 24 * 30);
                        } else {
                            cemail.setMaxAge(0);
                            cpass.setMaxAge(0);
                            rem.setMaxAge(0);
                        }
                        response.addCookie(cemail);
                        response.addCookie(cpass);
                        response.addCookie(rem);
                        response.sendRedirect("home");
                    }
                }
            }

            if (action.equals("logout")) {
                session.removeAttribute("user");
                response.sendRedirect("home");
            }
            if (action.equals("register")) {
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if (action.equals("checkregister")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String username = request.getParameter("username");
                String name = request.getParameter("name");
                String rgender = request.getParameter("gender");
                String rphone = request.getParameter("phone");
                int role_id = 1;
                String img = "assets/images/avata.png";
                boolean status = true;
                if (Validate.checkUsername(username) == false) {
                    request.setAttribute("error", "Tên người dùng không hợp lệ !");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else if (Validate.checkFullName(name) == false) {
                    request.setAttribute("error", "Thông tin Họ Tên không hợp lệ !");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else if (Validate.checkPhone(rphone) == false) {
                    request.setAttribute("error", "Số điện thoại không hợp lệ !");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else if (Validate.checkEmail(email) == false) {
                    request.setAttribute("error", "Email không hợp lệ !");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else if (Validate.checkPassword(password) == false) {
                    request.setAttribute("error", "Mật khẩu không hợp lệ (Cần có ít nhất 8 ký tự bao gồm viết hoa và ký tự đặc biệt) !");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else {
                    if (!password.equals(repassword)) {
                        request.setAttribute("error", "Mật khẩu không trùng khớp. Hãy nhập lại...");
                        request.getRequestDispatcher("user?action=register").forward(request, response);
                    } else {
                        String enpassword = EncodeData.enCode(password);
                        boolean gender = Boolean.parseBoolean(rgender);
                        int phone = Integer.parseInt(rphone);
                        String fullname = Validate.capitalizeFirstLetter(name);
                        Account account = userdao.checkAcc(email, username);
                        if (account != null) {
                            request.setAttribute("error", "Email hoặc username đã tồn tại trên hệ thống!");
                            request.getRequestDispatcher("user?action=register").forward(request, response);
                        } else {
                            Account a = new Account(username, role_id, enpassword, fullname, gender, phone, email,img,status);
                            session.setAttribute("register", a);
                            response.sendRedirect("user?action=generalcapcha");
                        }
                    }
                }
            }
            if (action.equals("recover")) {
               request.getRequestDispatcher("recover.jsp").forward(request, response);
            }
            if (action.equals("checkemail")) {
                String email = request.getParameter("email");
                if (Validate.checkEmail(email) == false) {
                    request.setAttribute("error", "Email không hợp lệ !");
                    request.getRequestDispatcher("user?action=recover").forward(request, response);
                } else {
                    Account account = userdao.checkAccByEmail(email);
                    if (account == null) {
                        request.setAttribute("error", "Email không tồn tại !");
                        request.getRequestDispatcher("user?action=recover").forward(request, response);
                    }else{
                        String newpass = Password.getPassword(8);
                        SendMail.setContentRecover(account.getUsername(), newpass, email);
                        userdao.Recover(account.getUsername(), EncodeData.enCode(newpass));
                        request.setAttribute("success", "Mật khẩu mới đã đươc gửi vào email của bạn.Nhấn đăng nhập để truy cập hệ thống.");
                        request.getRequestDispatcher("user?action=recover").forward(request, response);
                    }
                }

            }
            
            if (action.equals("profile")) {
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            
            
            if (action.equals("capcha")) {
                Account a = (Account) session.getAttribute("register");
                String email = a.getEmail();
                request.setAttribute("email", email);
                request.getRequestDispatcher("capcha.jsp").forward(request, response);
            }
            if (action.equals("generalcapcha")) {
                String capcha = Capcha.getCapcha();
                Account a = (Account) session.getAttribute("register");
                String email = a.getEmail();
                String username = a.getUsername();
                SendMail.setContent(username, capcha, email);
                session.setAttribute("capcha", capcha);
                request.getRequestDispatcher("user?action=capcha").forward(request, response);
            }
            if (action.equals("checkcapcha")) {
                String capcha = request.getParameter("capcha");
                String scapcha = (String) session.getAttribute("capcha");
                if (capcha.equals(scapcha)) {
                    Account a = (Account) session.getAttribute("register");
                    String email = a.getEmail();
                    String password = a.getPassword();
                    String username = a.getUsername();
                    String name = a.getName();
                    int phone = a.getPhone();
                    boolean gender = a.isGender();
                    int role_id = a.getRole_id();
                    String img = a.getImg();
                    boolean status = a.isStatus();
                    userdao.Register(email, password, username, role_id, name, phone, gender,img,status);
                    session.removeAttribute("register");
                    request.setAttribute("error", "Đăng ký thành công...");
                    request.getRequestDispatcher("user?action=login").forward(request, response);
                } else {
                    request.setAttribute("error", "Xác thực không thành công, Hãy thử lại mã xác nhận!");
                    request.getRequestDispatcher("user?action=capcha").forward(request, response);
                }
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
