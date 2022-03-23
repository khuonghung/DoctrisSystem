<%-- 
    Document   : appointment
    Created on : Feb 19, 2022, 12:05:27 AM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 


<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-5 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Appointments</h5>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <form action="appointmentmanage?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Bác sĩ</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="doctor_id" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${doctor_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${doctor}" var="d">
                                                        <option <c:if test="${doctor_id == d.doctor_id}">selected</c:if> value="${d.doctor_id}">${d.doctor_name}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-4">
                                                <label class="form-label">Trạng thái</label>
                                            </div>
                                            <div class="col-md-8">
                                                <select name="status" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${status == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${status == 'Complete'}">selected</c:if> value="Complete">Complete</option>
                                                    <option <c:if test="${status == 'Assigned'}">selected</c:if> value="Assigned">Assigned</option>
                                                    <option <c:if test="${status == 'Pending'}">selected</c:if> value="Pending">Pending</option>
                                                    <option <c:if test="${status == 'Cancelled'}">selected</c:if> value="Cancelled">Cancelled</option>
                                                    </select>
                                                </div>  
                                            </div>
                                            <div class="col-md-1 md-0">
                                                <button type="submit" class="btn btn-primary">Lọc</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <table class="table mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" >ID</th>
                                                    <th class="border-bottom p-3" >Bệnh nhân</th>
                                                    <th class="border-bottom p-3" >Bác sĩ</th>
                                                    <th class="border-bottom p-3" >Ngày</th>
                                                    <th class="border-bottom p-3" >Thời gian</th>
                                                    <th class="border-bottom p-3" >Trạng thái</th>
                                                    <th class="border-bottom p-3" ></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${appointment}" var="a">
                                                <tr>
                                                    <th class="p-3">${a.id}</th>
                                                    <td class="p-3">${a.patient.account.username}</td>
                                                    <td class="p-3">${a.doctor.doctor_name}</td>
                                                    <td class="p-3"><fmt:formatDate pattern="dd/MM/yyyy" value="${a.date}" /></td>
                                                    <td class="p-3">${a.time}</td>
                                                    <td class="p-3">${a.status}</td>
                                                    <td class="text-end p-3">
                                                        <a href="appointmentmanage?action=detail&id=${a.id}" type="button"class="btn btn-info">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
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
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/simplebar.min.js"></script>
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/select2.init.js"></script>
        <script src="assets/js/flatpickr.min.js"></script>
        <script src="assets/js/flatpickr.init.js"></script>
        <script src="assets/js/jquery.timepicker.min.js"></script> 
        <script src="assets/js/timepicker.init.js"></script> 
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

    </body>

</html>
