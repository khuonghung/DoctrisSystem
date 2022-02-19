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
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-5 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Account</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="account?action=search" method="POST" id="searchform" class="searchform">
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
                                <form action="account?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Quyền</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="role_id" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${role_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${role}" var="r">
                                                        <option <c:if test="${role_id == r.role_id}">selected</c:if> value="${r.role_id}">${r.name}</option>
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
                                                    <option <c:if test="${status == '1'}">selected</c:if> value="1">Active</option>
                                                    <option <c:if test="${status == '0'}">selected</c:if> value="0">Disable</option>
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
                                                        <a href="account?action=detail&username=${a.username}" type="button"class="btn btn-info">Chi tiết</a>
                                                    </td>
                                                    <td class="text-end p-3">
                                                        <a href="#" type="button"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit${a.username}">Chỉnh sửa</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
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
                </div>

                <c:forEach items="${account}" var="a">
                    <div class="modal fade" id="edit${a.username}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header border-bottom p-3">
                                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body p-3 pt-4">
                                    <form action="account?action=update" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Tên người dùng</label>
                                                    <input value="${a.username}" readonly name="username" id="name" type="text" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Quyền <span class="text-danger">*</span></label>
                                                    <select name="role_id" class="form-select" aria-label="Default select example">
                                                        <c:forEach items="${role}" var="r">
                                                            <option <c:if test="${a.role.name == r.name}">selected</c:if> value="${r.role_id}">${r.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                                <select name="status" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${a.status == true}">selected</c:if> value="true">Active</option>
                                                    <option <c:if test="${a.status == false}">selected</c:if> value="false">Disable</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="d-grid">
                                                        <button type="submit" id="submit" class="btn btn-primary">Chỉnh sửa</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                </c:forEach>


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
