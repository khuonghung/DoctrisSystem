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
import model.Role;
import model.Appointment;
import model.Reservation;
import dal.*;
import configs.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Khuong Hung
 */
@MultipartConfig(maxFileSize = 16177216)
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
        String alert = null;
        String message = null;
        Account user = (Account) session.getAttribute("user");
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
                Account account = userdao.login(email, enpassword);
                if (account == null) {
                    request.setAttribute("error", "Email hoặc mật khẩu không chính xác!");
                    request.getRequestDispatcher("user?action=login").forward(request, response);
                } else if (account.isStatus() == false) {
                    request.setAttribute("error", "Tài khoản đã bị khóa !");
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
                    if (account.getRole().getRole_id() == 1) {
                        response.sendRedirect("dashboard?action=default");
                    } else {
                        response.sendRedirect("home");
                    }
                }
            }

            if (action.equals("logout")) {
                session.invalidate();
                response.sendRedirect("home");
                return;
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
                int role_id = 2;
                String img = "default";
                boolean status = true;
                String enpassword = EncodeData.enCode(password);
                boolean gender = Boolean.parseBoolean(rgender);
                int phone = Integer.parseInt(rphone);
                String fullname = Validate.capitalizeFirstLetter(name);
                Account account = userdao.checkAcc(email, username);
                if (account != null) {
                    request.setAttribute("email", email);
                    request.setAttribute("password", password);
                    request.setAttribute("repassword", repassword);
                    request.setAttribute("username", username);
                    request.setAttribute("name", name);
                    request.setAttribute("gender", rgender.equals("true"));
                    request.setAttribute("phone", rphone);
                    request.setAttribute("error", "Email hoặc username đã tồn tại trên hệ thống!");
                    request.getRequestDispatcher("user?action=register").forward(request, response);
                } else {
                    Role r = new Role(role_id);
                    Account a = new Account(username, r, enpassword, fullname, gender, phone, email, img, status);
                    session.setAttribute("register", a);
                    request.getRequestDispatcher("user?action=generalcaptcha").forward(request, response);
                }
            }
            if (action.equals("recover")) {
                request.getRequestDispatcher("recover.jsp").forward(request, response);
            }

            if (action.equals("recoverpass")) {
                String type = request.getParameter("type");
                request.setAttribute("type", type);
                request.getRequestDispatcher("recover.jsp").forward(request, response);
            }

            if (action.equals("forgot")) {
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String username = (String) session.getAttribute("username");
                password = EncodeData.enCode(password);
                userdao.Recover(username, password);
                userdao.RemoveCaptcha(username);
                request.setAttribute("success", "Thay đổi mật khẩu thành công!");
                request.getRequestDispatcher("user?action=login").forward(request, response);
            }

            if (action.equals("checkemail")) {
                String email = request.getParameter("email");
                Account account = userdao.checkAccByEmail(email);
                if (account == null) {
                    request.setAttribute("email", email);
                    request.setAttribute("error", "Email không tồn tại!");
                    request.getRequestDispatcher("user?action=recover").forward(request, response);
                } else {
                    String captcha = Captcha.getCaptcha(16);
                    String content = "&username=" + account.getUsername() + "&captcha=" + captcha + "&type=recover";
                    String enContent = EncodeData.enCode(content);
                    SendMail.setContentRecover(account.getUsername(), "https://doctriscare.ml/user?action=verification&id=" + enContent, email);
                    userdao.RemoveCaptcha(account.getUsername());
                    userdao.AddCaptcha(account.getUsername(), captcha);
                    request.setAttribute("error", "Link đặt lại mật khẩu được gửi đến email của bạn!");
                    request.getRequestDispatcher("user?action=recover").forward(request, response);
                }
            }

            if (action.equals("profile")) {
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }

            if (action.equals("updateprofile")) {
                String username = request.getParameter("username");
                String name = request.getParameter("name");
                int phone = Integer.parseInt(request.getParameter("phone"));
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                userdao.UpdateProfile(username, name, phone, gender);
                Account a = new Account(username, user.getRole(), name, gender, phone, user.getEmail(), user.getImg(), user.isStatus());
                session.setAttribute("user", a);
                request.setAttribute("updatesuccess", "Thông tin đã được cập nhật!");
                response.sendRedirect("user?action=profile");
            }

            if (action.equals("update_image")) {
                String username = user.getUsername();
                Part image = request.getPart("image");
                if (image != null) {
                    try {
                        Account acc = userdao.getAccountByUsername(username);
                        userdao.UpdateImage(username, image);
                        session.setAttribute("user", acc);
                    } catch (Exception e) {
                    }
                }
                alert = "success";
                message = "Cập nhật ảnh thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("user?action=profile").forward(request, response);
            }

            if (action.equals("changepassword")) {
                String oldpassword = EncodeData.enCode(request.getParameter("oldpassword"));
                String newpassword = request.getParameter("newpassword");
                String renewpassword = request.getParameter("renewpassword");
                if (!oldpassword.equals(user.getPassword())) {
                    request.setAttribute("oldpassword", EncodeData.deCode(oldpassword));
                    request.setAttribute("newpassword", newpassword);
                    request.setAttribute("renewpassword", renewpassword);
                    request.setAttribute("passerror", "Mật khẩu cũ không đúng!");
                    request.getRequestDispatcher("user?action=profile").forward(request, response);
                } else {
                    newpassword = EncodeData.enCode(newpassword);
                    userdao.Recover(user.getUsername(), newpassword);
                    request.setAttribute("success", "Thay đổi mật khẩu thành công, mời bạn đăng nhập lại!");
                    request.getRequestDispatcher("user?action=login").forward(request, response);
                }
            }

            if (action.equals("generalcaptcha")) {
                String captcha = Captcha.getCaptcha(16);
                Account a = (Account) session.getAttribute("register");
                String email = a.getEmail();
                String password = a.getPassword();
                String username = a.getUsername();
                String name = a.getName();
                int phone = a.getPhone();
                boolean gender = a.isGender();
                int role_id = a.getRole().getRole_id();
                boolean status = a.isStatus();
                String content = "&email=" + email + "&password=" + password
                        + "&username=" + username + "&role_id=" + role_id + ""
                        + "&name=" + name + "&gender=" + gender + "&status=" + status + "&phone=" + phone + "&captcha=" + captcha + "&type=register";
                String enContent = "https://doctriscare.ml/user?action=verification&id=" + EncodeData.enCode(content);
                SendMail.setContent(username, enContent, email);
                userdao.AddCaptcha(username, captcha);
                request.setAttribute("error", "Link xác thực đã được gửi đến bạn");
                request.getRequestDispatcher("user?action=login").forward(request, response);
            }

            if (action.equals("verification")) {
                String id = request.getParameter("id");
                String deID = EncodeData.deCode(id);
                request.getRequestDispatcher("user?action=checkcaptcha" + deID).forward(request, response);
            }
            if (action.equals("checkcaptcha")) {
                String captcha = request.getParameter("captcha");
                String username = request.getParameter("username");
                String type = request.getParameter("type");
                Account acc = userdao.checkCaptcha(captcha, username);
                if (type.equals("register") && acc != null) {
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String name = request.getParameter("name");
                    int phone = Integer.parseInt(request.getParameter("phone"));
                    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                    int role_id = Integer.parseInt(request.getParameter("role_id"));
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    userdao.Register(email, password, username, role_id, name, phone, gender, status);
                    session.removeAttribute("register");
                    userdao.RemoveCaptcha(username);
                    request.setAttribute("success", "Đăng ký thành công...");
                    request.getRequestDispatcher("user?action=login").forward(request, response);
                } else if (type.equals("recover") && acc != null) {
                    session.setAttribute("username", username);
                    request.getRequestDispatcher("user?action=recoverpass&type=recover").forward(request, response);
                } else {
                    request.getRequestDispatcher("404.jsp").forward(request, response);
                }
            }

            if (action.equals("history")) {
                String type = request.getParameter("type");
                PatientDao pdao = new PatientDao();
                AppointmentDAO adao = new AppointmentDAO();
                ReservationDAO rdao = new ReservationDAO();
                List<Appointment> appointmentlist = null;
                List<Reservation> reservationlist = null;
                if (type.equals("appointment")) {
                    appointmentlist = adao.getAppointmentHistory(pdao.getPatientIDByUsername(user.getUsername()));
                } else if (type.equals("reservation")) {
                    reservationlist = rdao.getReservationListHistory(pdao.getPatientIDByUsername(user.getUsername()));
                }
                if (appointmentlist != null || reservationlist != null) {
                    int page, numperpage = 8;
                    int size = 0;
                    if (appointmentlist != null) {
                        size = appointmentlist.size();
                    } else {
                        size = reservationlist.size();
                    }
                    int num = (size % 8 == 0 ? (size / 8) : ((size / 8)) + 1);
                    String xpage = request.getParameter("page");
                    if (xpage == null) {
                        page = 1;
                    } else {
                        page = Integer.parseInt(xpage);
                    }
                    int start, end;
                    String url = null;
                    start = (page - 1) * numperpage;
                    end = Math.min(page * numperpage, size);
                    if (appointmentlist != null) {
                        appointmentlist = adao.getListByPage(appointmentlist, start, end);
                        request.setAttribute("appointmentlist", appointmentlist);
                        url = "user?action=history&type=appointment";
                    } else {
                        reservationlist = rdao.getListByPage(reservationlist, start, end);
                        request.setAttribute("reservationlist", reservationlist);
                        url = "user?action=history&type=reservation";
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("url", url);
                    request.setAttribute("num", num);
                    request.getRequestDispatcher("history.jsp").forward(request, response);
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
