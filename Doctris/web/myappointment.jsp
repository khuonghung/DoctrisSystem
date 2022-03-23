<%-- 
    Document   : myfeedback
    Created on : Mar 3, 2022, 8:59:48 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <jsp:include page="layout/profileMenu.jsp"/>
                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <h3 class="mb-0"></h3>
                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Lịch hẹn từ bệnh nhân</h5>
                            </div>
                            <div class="p-4">
                                <table class="table mb-0 table-center">
                                    <thead>
                                        <tr>
                                            <th class="border-bottom p-3" >ID</th>
                                            <th class="border-bottom p-3" >Tên bệnh nhân</th>
                                            <th class="border-bottom p-3" >ID bệnh nhân</th>
                                            <th class="border-bottom p-3" >Ngày đặt lịch</th>
                                            <th class="border-bottom p-3" >Giờ đặt lịch</th>
                                            <th class="border-bottom p-3" >Trạng thái</th>
                                            <th class="border-bottom p-3" ></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${AppointmentList}" var="a">
                                            <tr>

                                                <th class="p-3">${a.id}</th>
                                                <td class="p-3">${a.patient.username}</td>
                                                <td class="p-3">${a.patient.patient_id}</td>
                                                <td class="p-3"><fmt:formatDate type = "date" value = "${a.date}" /></td>
                                                <td class="p-3"><fmt:formatDate type = "time" value = "${a.time}" /></td>
                                                <td class="p-3" >${a.status}</td>
                                                <td class="text-end ">
                                                    <a href="doctor?action=myappointmentdetail&id=${a.patient.patient_id}" type="button"class="btn btn-info">Chi tiết</a>
                                                </td>   
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <c:set var="page" value="${page}"/>
                                <div class="row text-center">
                                    <div class="col-12 mt-4">
                                        <div class="d-md-flex align-items-center text-center justify-content-between">
                                            <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                <c:forEach begin="${1}" end="${num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="doctor?action=myappointment&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>

