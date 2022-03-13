<%-- 
    Document   : profileMenu
    Created on : Mar 3, 2022, 8:58:33 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xl-4 col-lg-4 col-md-5 col-12">
    <div class="rounded shadow overflow-hidden sticky-bar">
        <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
            <br><br><br><br>
            <c:if test="${sessionScope.user.img != 'default'}">
                <img src="data:image/png;base64,${sessionScope.user.img}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
            </c:if>
            <c:if test="${sessionScope.user.img == 'default'}">
                <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
            </c:if>

            <h5 class="mt-3 mb-1">${sessionScope.user.name}</h5>
            <p class="text-muted mb-0">${sessionScope.user.username}</p>
        </div>

        <ul class="list-unstyled sidebar-nav mb-0">
                <li class="navbar-item"><a href="user?action=profile" class="navbar-link"><i class="ri-airplay-line align-middle navbar-icon"></i> Dashboard</a></li>
                <c:if test="${sessionScope.user.role.role_id == 3}">
                <li class="navbar-item"><a href="doctor?action=myfeedback" class="navbar-link"><i class="ri-chat-1-line align-middle navbar-icon"></i> Phản hồi</a></li>
                <li class="navbar-item"><a href="doctor?action=mypatient" class="navbar-link"><i class="ri-empathize-line align-middle navbar-icon"></i> Bệnh nhân của tôi</a></li>
                <li class="navbar-item"><a href="doctor?action=myappointment" class="navbar-link"><i class="ri-calendar-check-line align-middle navbar-icon"></i> Lịch hẹn</a></li>
                </c:if>
                <c:if test="${sessionScope.user.role.role_id == 2}">
                <li class="navbar-item"><a href="user?action=history&type=appointment" class="navbar-link"><i class="ri-calendar-check-line align-middle navbar-icon"></i> Lịch sử đặt lịch</a></li>
                <li class="navbar-item"><a href="user?action=history&type=reservation" class="navbar-link"><i class="ri-calendar-check-line align-middle navbar-icon"></i> Lịch sử dịch vụ</a></li>
                </c:if>
        </ul>
    </div>
</div>