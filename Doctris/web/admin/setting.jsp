<%-- 
    Document   : setting
    Created on : Jan 14, 2022, 2:58:38 PM
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
                            <div class="col-xl-5 col-lg-6 col-md-2">
                                <h5 class="mb-0">Settings</h5>
                                <h6>${requestScope.success}</h6>
                            </div>
                            <div class="col-xl-4 col-lg-6 col-md-2">
                                <div class="search-bar p-0 d-none d-lg-block ms-2">
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
                            <div class="col-xl-3 col-lg-6 col-md-8 mt-4 mt-md-0">
                                <div class="justify-content-md-end">
                                    <form>
                                        <div class="row justify-content-between align-items-center">
                                            <div class="col-sm-12 col-md-5">
                                                <div class="mb-0 position-relative">
                                                    <div class="dropdown">
                                                        <button style="color: #000; background-color: #215AEE ;border:none; font-family: sans-serif; " class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                            Thể loại
                                                        </button>
                                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                            <li><a class="dropdown-item" href="setting">Tất cả</a></li>
                                                                <c:forEach items="${setting}" var="s">
                                                                <li><a class="dropdown-item" href="setting?action=${s.setting_name}">${s.description}</a></li>
                                                                </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-12 col-md-7 mt-4 mt-sm-0">
                                                <div class="d-grid">
                                                    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addnew">Thêm mới</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 50px;">Setting ID</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">ID</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;">Name</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Status</th>
                                                <th class="border-bottom p-3" style="min-width: 150px;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${settingdetails}" var="sd">
                                                <tr>
                                                    <th class="p-3">${sd.setting_id}</th>
                                                    <td class="p-3">${sd.id}</td>
                                                    <td class="p-3">${sd.name}</td>
                                                    <c:if test="${sd.status == true}">
                                                        <td class="p-3">Active</td>
                                                    </c:if>
                                                    <c:if test="${sd.status == false}">
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
                        <c:set var="page" value="${page}"/>
                        <div class="row text-center">
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center justify-content-between">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">

                                        <c:forEach begin="${1}" end="${num}" var="i">
                                            <c:if test="${requestScope.type == 1}">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="setting?action=User&page=${i}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${requestScope.type == 2}">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="setting?action=Category_blog&page=${i}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${requestScope.type == 3}">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="setting?action=Category_service&page=${i}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${requestScope.type == 0}">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="setting?page=${i}">${i}</a></li>
                                                </c:if>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${settingdetails}" var="sd">
                        <div class="modal fade" id="edit${sd.id}${sd.setting_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header border-bottom p-3">
                                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body p-3 pt-4">
                                        <form action="setting?action=update" method="POST">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Setting ID <span class="text-danger">*</span></label>
                                                        <input value="${sd.setting_id}" readonly name="setting_id" id="name" type="text" class="form-control">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">ID <span class="text-danger">*</span></label>
                                                        <input value="${sd.id}" readonly name="id" id="name" type="text" class="form-control">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Tên <span class="text-danger">*</span></label>
                                                        <input value="${sd.name}" name="name" id="name" type="text" class="form-control">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                                        <select name="status" class="form-select" aria-label="Default select example">
                                                            <option <c:if test="${sd.status == true}">selected</c:if> value="1">Active</option>
                                                            <option <c:if test="${sd.status == false}">selected</c:if> value="0">Disable</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="d-grid">
                                                            <button type="submit" class="btn btn-primary">Chỉnh sửa</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </c:forEach>

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

        <div class="modal fade" id="addnew" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm mới setting</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <form action="setting?action=addnew" method="POST">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="mb-3">
                                        <label class="form-label">Thể loại <span class="text-danger">*</span></label>
                                        <select name="setting_id" class="form-select" aria-label="Default select example">
                                            <c:forEach items="${setting}" var="s">
                                                <option value="${s.setting_id}">${s.description}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Tên <span class="text-danger">*</span></label>
                                    <input value="${sd.name}" name="name" id="name" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                    <select name="status" class="form-select" aria-label="Default select example">
                                        <option value="1">Active</option>
                                        <option value="0">Disable</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Thêm</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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