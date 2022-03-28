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
import configs.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import vnpay.common.config;

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
                session.setAttribute("time", time);
                session.setAttribute("date", date);
                if (payment.equals("default")) {
                    if (session.getAttribute("type").equals("appointment")) {
                        adao.Booking(d.getDoctor_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", d.getFee(), "Pending");
                        int booking_id = adao.getLastBooking(pdao.getPatientIDByUsername(user.getUsername()));
                        int fee = (int) Math.round(d.getFee());
                        SendMail.Booking(booking_id, user.getEmail(), user.getName(), date, time, "Bác sĩ : " + d.getDoctor_name(), fee, "Thanh toán tại phòng khám");
                    }
                    if (session.getAttribute("type").equals("reservation")) {
                        rdao.Booking(s.getService_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", "Pending");
                        int booking_id = rdao.getLastBooking(pdao.getPatientIDByUsername(user.getUsername()));
                        int fee = (int) Math.round(s.getFee());
                        SendMail.Booking(booking_id, user.getEmail(), user.getName(), date, time, "Dịch vụ : " + s.getTitle(), fee, "Thanh toán tại phòng khám");
                    }
                }
                if (payment.equals("vnpay")) {
                    int booking_id = 0;
                    String vnp_Version = "2.0.0";
                    String vnp_Command = "pay";
                    String vnp_OrderInfo = null;
                    String vnp_TxnRef = null;
                    int amount = 0;
                    if (session.getAttribute("type").equals("appointment")) {
                        adao.Booking(d.getDoctor_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", d.getFee(), "Pending");
                        booking_id = adao.getLastBooking(pdao.getPatientIDByUsername(user.getUsername()));
                        vnp_OrderInfo = "appointment : " + booking_id;
                        amount = (int) Math.round(d.getFee()) * 100;
                        vnp_TxnRef = "A" + booking_id + "";
                    }
                    if (session.getAttribute("type").equals("reservation")) {
                        rdao.Booking(s.getService_id(), pdao.getPatientIDByUsername(user.getUsername()), udao.getRandomStaff(), date, time, description, "Assigned", "Pending");
                        booking_id = rdao.getLastBooking(pdao.getPatientIDByUsername(user.getUsername()));
                        vnp_OrderInfo = "reservation : " + booking_id;
                        amount = (int) Math.round(s.getFee()) * 100;
                        vnp_TxnRef = "R" + booking_id + "";
                    }

                    String orderType = "billpayment";
                    String vnp_IpAddr = config.getIpAddress(request);
                    String vnp_TmnCode = config.vnp_TmnCode;
                    Map<String, String> vnp_Params = new HashMap<>();
                    vnp_Params.put("vnp_Version", vnp_Version);
                    vnp_Params.put("vnp_Command", vnp_Command);
                    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                    vnp_Params.put("vnp_Amount", String.valueOf(amount));
                    vnp_Params.put("vnp_CurrCode", "VND");
                    String bank_code = "";
                    if (bank_code != null && bank_code.isEmpty()) {
                        vnp_Params.put("vnp_BankCode", bank_code);
                    }
                    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                    vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                    vnp_Params.put("vnp_OrderType", orderType);

                    String locate = "vi";
                    if (locate != null && !locate.isEmpty()) {
                        vnp_Params.put("vnp_Locale", locate);
                    } else {
                        vnp_Params.put("vnp_Locale", "vn");
                    }
                    vnp_Params.put("vnp_ReturnUrl", config.vnp_Returnurl);
                    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                    Date dt = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String dateString = formatter.format(dt);
                    String vnp_CreateDate = dateString;
                    String vnp_TransDate = vnp_CreateDate;
                    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                    //Build data to hash and querystring
                    List fieldNames = new ArrayList(vnp_Params.keySet());
                    Collections.sort(fieldNames);
                    StringBuilder hashData = new StringBuilder();
                    StringBuilder query = new StringBuilder();
                    Iterator itr = fieldNames.iterator();
                    while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                            //Build hash data
                            hashData.append(fieldName);
                            hashData.append('=');
                            hashData.append(fieldValue);
                            //Build query
                            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                            query.append('=');
                            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            if (itr.hasNext()) {
                                query.append('&');
                                hashData.append('&');
                            }
                        }
                    }
                    String queryUrl = query.toString();
                    String vnp_SecureHash = config.Sha256(config.vnp_HashSecret + hashData.toString());
                    queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
                    String paymentUrl = config.vnp_PayUrl + "?" + queryUrl;
                    request.setAttribute("code", "00");
                    request.setAttribute("message", "success");
                    request.setAttribute("data", paymentUrl);
                    response.sendRedirect(paymentUrl);
                    return;
                }
                response.sendRedirect("booking_success");
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
