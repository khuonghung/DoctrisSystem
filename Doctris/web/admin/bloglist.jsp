<%-- 
    Document   : mkt_bloglist
    Created on : Feb 23, 2022, 2:45:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                            <div class="col-md-2 mt-4 mt-sm-0">
                                <div class="d-grid">
                                    <a href="blogmanage?action=addblog" class="btn btn-primary" >Thêm mới</a>
                                </div>
                            </div>
                            <div class="col-md-10 row">
                                <div class="col-md-6">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="blogmanage?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input value="${requestScope.content}" type="text" class="border rounded" name="content" id="s" placeholder="Nhập từ khóa">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                                <div class="col-md-6 row align-items-center">
                                    <div class="col-md-3">
                                        <label class="form-label">Sắp xếp</label>
                                    </div>
                                    <div class="col-md-9">
                                        <form>
                                            <select name="sort" class="form-select" onchange="Sort(this.value)" aria-label="Default select example">
                                                <option <c:if test="${requestScope.sort eq 'b.title'}"> selected="selected"</c:if>  value="b.title">Tiêu đề</option>
                                                <option <c:if test="${requestScope.sort eq 'b.category_id'}"> selected="selected"</c:if> value="b.category_id">Danh Mục</option>
                                                <option <c:if test="${requestScope.sort eq 'author'}"> selected="selected"</c:if> value="author">Tác giả</option>
                                                <option <c:if test="${requestScope.sort eq 'b.featured'}"> selected="selected"</c:if> value="b.featured">Nổi Bật</option>
                                                <option <c:if test="${requestScope.sort eq 'b.status'}"> selected="selected"</c:if> value="b.status">Trạng Thái</option>
                                                </select>
                                            </form>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <br>
                            <div class="row">
                                <form action="blogmanage?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="row">
                                        <div class="col-md-4 row align-items-center">
                                            <div class="col-md-4">
                                                <label class="form-label">Danh Mục</label>
                                            </div>
                                            <div class="col-md-8">
                                                <select name="category_id" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${category_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                <c:forEach items="${categories}" var="c">
                                                    <option <c:if test="${category_id == c.id}">selected checked="checked"</c:if> value="${c.id}">${c.name}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                    </div>
                                    <div class="col-md-4 row align-items-center">
                                        <div class="col-md-3">
                                            <label class="form-label">Tác giả</label>
                                        </div>
                                        <div class="col-md-9">
                                            <select name="author" class="form-select" aria-label="Default select example">
                                                <option <c:if test="${category_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                <c:forEach items="${authors}" var="c">
                                                    <option <c:if test="${author == c.username}">selected checked="checked"</c:if> value="${c.username}">${c.name}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                    </div>
                                    <div class="col-md-3 row align-items-center">
                                        <div class="col-md-5">
                                            <label class="form-label">Status</label>
                                        </div>
                                        <div class="col-md-7">
                                            <select name="status" class="form-select" aria-label="Default select example">
                                                <option <c:if test="${status == 'all'}">selected checked="checked"</c:if> value="all">Tất cả</option>
                                                <option <c:if test="${status == '1'}">selected checked="checked"</c:if> value="1">Active</option>
                                                <option <c:if test="${status == '0'}">selected checked="checked"</c:if> value="0">Disable</option>
                                                </select>
                                            </div>  
                                        </div>
                                        <div class="col-md-1 md-0">
                                            <button type="submit" class="btn btn-primary">Lọc</button>
                                        </div>
                                    </div>
                                </form>


                            </div>
                            <br>


                            <div class="row">
                                <!-- hiển thị blog-->

                                <div class="row">
                                    <div class="col-12 mt-4">
                                        <div class="table-responsive bg-white shadow rounded">
                                            <table class="table mb-0 table-center">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" >ID</th>
                                                        <th class="border-bottom p-3" >Ảnh</th>
                                                        <th class="border-bottom p-3" >Tiêu đề</th>
                                                        <th class="border-bottom p-3" >Loại</th>
                                                        <th class="border-bottom p-3" >Tác giả</th>
                                                        <th class="border-bottom p-3" >Nổi bật</th>
                                                        <th class="border-bottom p-3" >Trạng thái</th>
                                                        <th class="border-bottom p-3 text-center" >Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${listblog}" var="b">
                                                    <tr>
                                                        <td class="p-3">${b.blog_id}</td>
                                                        <td class="p-3"><img src="data:image/png;base64,${b.img}" class="img-fluid" alt=""></td>
                                                        <td class="p-3">${b.title}</td>
                                                        <td class="p-3">${b.category.name}</td>
                                                        <td class="p-3">${b.author}</td>
                                                        <td class="p-3">
                                                            <c:if test="${b.featured == true}">
                                                            <p>Tiêu Điểm</p>
                                                        </c:if>
                                                        <c:if test="${b.featured == false}">
                                                            <p>Bình thường</p>
                                                        </c:if>
                                                        </td>
                                                        <td class=" text-center p-3">
                                                            <c:if test="${b.status == true}">
                                                                <a href="blogmanage?action=hide&blog_id=${b.blog_id}&status=${b.status?"active":"disable"}"style="width: 80px" value="${b.status}" type="button"class="btn btn-info">Ẩn</a>
                                                            </c:if>
                                                            <c:if test="${b.status == false}">
                                                                <a href="blogmanage?action=hide&blog_id=${b.blog_id}&status=${b.status?"active":"disable"}"style="width: 80px" value="${b.status}" type="button"class="btn btn-info">Hiện</a>
                                                            </c:if>
                                                        </td>
                                                        <td class="p-3">
                                                            <a href="blogmanage?action=detail&blog_id=${b.blog_id}" style="width: 80px" type="button"class="btn btn-info">View</a>
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

        <style>
            .Choicefile{
                display: block;
                background: #396CF0;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }
        </style>

        <script>
            function Sort(type) {
                window.location.href = "blogmanage?action=sort&type=" + type;
            }
            var editor = '';
            $(document).ready(function () {
                editor = CKEDITOR.replace('describe');
            });

        </script>
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
