<%-- 
    Document   : reservation
    Created on : Feb 21, 2022, 3:16:03 PM
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
                                    <h5 class="mb-0">Reservations</h5>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <form action="reservationmanage?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Dịch vụ</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="service_id" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${service_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${service}" var="s">
                                                        <option <c:if test="${service_id == s.service_id}">selected</c:if> value="${s.service_id}">${s.title}</option>
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
                                                    <th class="border-bottom p-3" >Dịch vụ</th>
                                                    <th class="border-bottom p-3" >Ngày</th>
                                                    <th class="border-bottom p-3" >Thời gian</th>
                                                    <th class="border-bottom p-3" >Trạng thái</th>
                                                    <th class="border-bottom p-3" ></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${reservation}" var="r">
                                                <tr>
                                                    <th class="p-3">${r.id}</th>
                                                    <td class="p-3">${r.patient.account.username}</td>
                                                    <td class="p-3">${r.service.title}</td>
                                                    <td class="p-3"><fmt:formatDate pattern="dd/MM/yyyy" value="${r.date}" /></td>
                                                    <td class="p-3">${r.time}</td>
                                                    <td class="p-3">${r.status}</td>
                                                    <td class="text-end p-3">
                                                        <a href="reservationmanage?action=detail&id=${r.id}" type="button"class="btn btn-info">Chi tiết</a>
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
