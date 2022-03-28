<%-- 
    Document   : history
    Created on : Mar 5, 2022, 1:22:03 AM
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
                                <c:if test="${appointmentlist != null}">
                                    <h5 class="mb-0">Lịch sử đặt lịch</h5>
                                </c:if>
                                <c:if test="${reservationlist != null}">
                                    <h5 class="mb-0">Lịch sử dịch vụ</h5>
                                </c:if>
                            </div>
                            <div class="p-2">
                                <div class="table-responsive bg-white shadow rounded">
                                    <c:if test="${appointmentlist != null}">
                                        <table class="table mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="min-width: 10px;">#</th>
                                                    <th class="border-bottom p-3" style="min-width: 190px;">Bác sĩ</th>
                                                    <th class="border-bottom p-3">Mã bác sĩ</th>
                                                    <th class="border-bottom p-3" style="min-width: 120px;">Ngày</th>
                                                    <th class="border-bottom p-3" style="min-width: 30px;">Thời gian</th>
                                                    <th class="border-bottom p-3">Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${appointmentlist}" var="a">
                                                    <tr>
                                                        <th class="p-3">${a.id}</th>
                                                        <td class="p-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <img src="data:image/png;base64,${a.doctor.img}" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                                    <span class="ms-2">${a.doctor.doctor_name}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3">${a.doctor.doctor_id}</td>
                                                        <td class="p-3">${a.date}</td>
                                                        <td class="p-3">${a.time}</td>
                                                        <td class="p-3">${a.status}</td>
                                                        <c:if test="${a.status == 'Complete'}">
                                                            <td><button style="padding: -40px" class="btn btn-soft-primary" onclick="window.location.href = 'doctor?action=detail&id=${a.doctor.doctor_id}&allow=true'">Đánh giá</button></td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${reservationlist != null}">
                                        <table class="table mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" style="max-width: 5px;">#</th>
                                                    <th class="border-bottom p-3" style="max-width: 80px;">Dịch vụ</th>
                                                    <th class="border-bottom p-3">Ngày</th>
                                                    <th class="border-bottom p-3">Thời gian</th>
                                                    <th class="border-bottom p-3">Phí</th>
                                                    <th class="border-bottom p-3">Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${reservationlist}" var="r">
                                                    <tr>
                                                        <th class="p-3">${r.id}</th>
                                                        <td class="p-3">
                                                            <a href="#" class="text-dark">
                                                                <div class="d-flex align-items-center">
                                                                    <span class="ms-2">${r.service.title}</span>
                                                                </div>
                                                            </a>
                                                        </td>
                                                        <td class="p-3"><fmt:formatDate pattern="dd/MM/yyyy" value="${r.date}"/></td>
                                                        <td class="p-3">${r.time}</td>
                                                        <td class="p-3"><fmt:formatNumber pattern="##########" value="${r.service.fee}"/> đ</td>
                                                        <td class="p-3">${r.status}</td>
                                                        <c:if test="${r.status == 'Complete'}">
                                                            <td><button style="padding: -40px" class="btn btn-soft-primary" onclick="window.location.href = 'service?action=detail&id=${r.service.service_id}&allow=true'">Đánh giá</button></td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                                <c:set var="page" value="${page}"/>
                                <div class="row text-center">
                                    <div class="col-12 mt-4">
                                        <div class="d-md-flex align-items-center text-center justify-content-between">
                                            <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                <c:forEach begin="${1}" end="${num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
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
