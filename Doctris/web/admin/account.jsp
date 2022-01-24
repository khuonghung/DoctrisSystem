<%-- 
    Document   : account
    Created on : Jan 23, 2022, 10:30:37 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <jsp:include page="../layout/preloader.jsp"/>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-8 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Account</h5>
                                    <h6>${requestScope.success}</h6>
                                </div>
                                <div class="col-md-8">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="#" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="txt" id="s" placeholder="Tìm kiếm setting...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="justify-content-md-end row">
                                    <div class="row justify-content-between align-items-center">
                                        <div class="col-sm-12 col-md-4 mx-auto">
                                            <div class="mb-0 position-relative">
                                                <div class="dropdown">
                                                    <button style="color: #000; background-color: #215AEE ;border:none; font-family: sans-serif; " class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Quyền
                                                    </button>
                                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                        <li><a class="dropdown-item" href="setting">Tất cả</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-md-4 mx-auto">
                                            <div class="mb-0 position-relative">
                                                <div class="dropdown">
                                                    <button style="color: #000; background-color: #215AEE ;border:none; font-family: sans-serif; " class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Trạng thái
                                                    </button>
                                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                        <li><a class="dropdown-item" href="setting">Tất cả</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-md-4 mx-auto">
                                            <div class="mb-0 position-relative">
                                                <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addnew" tabindex="-1" role="button" aria-disabled="true">Thêm mới</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" >Tên tài khoản</th>
                                                <th class="border-bottom p-3" >Họ tên</th>
                                                <th class="border-bottom p-3" >Giới tính</th>
                                                <th class="border-bottom p-3" >Email</th>
                                                <th class="border-bottom p-3" >Số điện thoại</th>
                                                <th class="border-bottom p-3" >Quyền</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                                <th class="border-bottom p-3" ></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${account}" var="a">
                                                <tr>
                                                    <th class="p-3">${a.username}</th>
                                                    <td class="p-3">${a.name}</td>
                                                    <c:if test="${a.gender == true}">
                                                        <td class="p-3">Nam</td>
                                                    </c:if>
                                                    <c:if test="${a.gender == false}">
                                                        <td class="p-3">Nữ</td>
                                                    </c:if>
                                                    <td class="p-3">${a.email}</td>
                                                    <td class="p-3">0${a.phone}</td>
                                                    <td class="p-3">${a.role.name}</td>
                                                    <c:if test="${a.status == true}">
                                                        <td class="p-3">Active</td>
                                                    </c:if>
                                                    <c:if test="${a.status == false}">
                                                        <td class="p-3">Disable</td>
                                                    </c:if>
                                                    <td class="text-end p-3">
                                                        <a href="#" type="button"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit${sd.id}${sd.setting_id}">Chỉnh sửa</a>
                                                        <a href="setting?action=delete&setting_id=${sd.setting_id}&id=${sd.id}" class="btn btn-danger">Xóa bỏ</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <footer class="bg-white shadow py-3">
                        <div class="container-fluid">
                            <div class="row align-items-center">
                                <div class="col">
                                    <div class="text-sm-start text-center">
                                        <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © Doctris.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </footer>
                </div>
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
