<%-- 
    Document   : doctor
    Created on : Feb 8, 2022, 12:13:02 AM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
                                    <h5 class="mb-0">Doctor</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="doctormanage?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="txt" id="s" placeholder="Tìm kiếm tài khoản...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-7">
                                <form action="doctormanage?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Giới tính</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="gender" class="form-select">
                                                    <option <c:if test="${gender == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${gender == 'true'}"> selected </c:if> value="true">Nam</option>
                                                    <option <c:if test="${gender == 'false'}"> selected </c:if> value="false">Nữ</option>
                                                </select>  
                                            </div>
                                        </div>
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-4">
                                                <label class="form-label">Chuyên môn</label>
                                            </div>
                                            <div class="col-md-8">
                                                <select name="speciality" class="form-select">
                                                    <option <c:if test="${speciality == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${speciality}" var="s">
                                                        <option <c:if test="${speciality1 == s.id}"> selected </c:if> value="${s.id}">${s.name}</option>
                                                    </c:forEach>
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
                                                <th class="border-bottom p-3" >Họ tên</th>
                                                <th class="border-bottom p-3" >Giới tính</th>
                                                <th class="border-bottom p-3" >Chuyên môn</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                                <th class="border-bottom p-3" ></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${doctor}" var="d">
                                                <tr>
                                                    <th class="p-3">${d.doctor_id}</th>
                                                    <td class="p-3">${d.doctor_name}</td>
                                                    <c:if test="${d.gender == true}">
                                                        <td class="p-3">Nam</td>
                                                    </c:if>
                                                    <c:if test="${d.gender == false}">
                                                        <td class="p-3">Nữ</td>
                                                    </c:if>
                                                    <td class="p-3">${d.setting.name}</td>
                                                    <c:if test="${d.status == true}">
                                                        <td class="p-3">Active</td>
                                                    </c:if>
                                                    <c:if test="${d.status == false}">
                                                        <td class="p-3">Disable</td>
                                                    </c:if>
                                                    <td class="text-end p-3">
                                                        <a href="doctormanage?action=detail&id=${d.doctor_id}" type="button"class="btn btn-info">Chi tiết</a>
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
