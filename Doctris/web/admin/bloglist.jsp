<%-- 
    Document   : mkt_bloglist
    Created on : Feb 23, 2022, 2:45:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
                                    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addnew">Thêm mới</a>
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

                            <c:forEach items="${listblog}" var="b">
                                <div class="col-lg-4 col-md-6 col-12 mb-4 pb-2">
                                    <div class="card blog blog-primary border-0 shadow rounded overflow-hidden">
                                        <img src="data:image/png;base64,${b.img}" class="img-fluid" alt="">
                                        <div class="card-body p-4">
                                            <ul class="list-unstyled mb-2">
                                                <li class="list-inline-item text-muted small me-3"><i class="uil uil-calendar-alt text-dark h6 me-1"></i>${b.date}</li>
                                            </ul>
                                            <a href="blogmanage?action=detail&blog_id=${b.blog_id}" class="text-dark title h7">${b.title}</a>
                                            <c:set var = "detail" value = "${b.describe}"/>
                                            <c:set var = "brief_info" value = "${fn:substring(detail, 0, 50)}" />
                                            <p style="font-size: 9">${brief_info}[...]</p>
                                            <div class="post-meta d-flex justify-content-between mt-3">
                                                <ul class="list-unstyled mb-0">
                                                    <li class="list-inline-item me-2 mb-0" style="color:gray">${b.category.name}</li>
                                                </ul>

                                            </div>
                                            <p>Status: ${b.status?"Active":"Disable"}</p>
                                            <p>Author: ${b.author}</p>
                                            <a href="blogmanage?action=hide&blog_id=${b.blog_id}&status=${b.status?"active":"disable"}" type="button"class="btn btn-info">Hide</a>
                                            <a href="blogmanage?action=detail&blog_id=${b.blog_id}" type="button"class="btn btn-info">View</a>
                                        </div>
                                    </div>

                                </div>
                            </c:forEach>

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

        <div class="modal fade" id="addnew" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header border-bottom p-3">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm blog</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body p-3 pt-4">
                        <form enctype="multipart/form-data" action="blogmanage?action=addnew" method="POST">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="mb-3">
                                        <label class="form-label">Danh Mục<span class="text-danger">*</span></label>
                                        <select name="category_id" class="form-select" aria-label="Default select example">
                                            <c:forEach items="${categories}" var="c">
                                                <option value="${c.id}">${c.name}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Tiêu đề <span class="text-danger">*</span></label>
                                    <input name="title" id="name" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Ảnh <span class="text-danger">*</span></label>
                                    <div class="form-group">
                                        <div class="col-lg-offset-5 col-lg-15">
                                            <div class="profile-pic">
                                                <label class="-label" for="file">
                                                    <c:if test="${ not empty blog.img}">
                                                        <img src="data:image/jpg;base64,${blog.img}" id="output" width="200" />
                                                    </c:if>
                                                    <c:if test="${empty blog.img}">
                                                        <img src="" id="output" width="200" alt="no image"/>
                                                    </c:if>
                                                </label>
                                                <br><br>
                                                <input id="file" type="file" onchange="loadFile(event)" name="image"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Mô tả<span class="text-danger">*</span></label>

                                    <textarea rows="10" cols="70" id="describe" name="describe" ></textarea>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Nổi bật <span class="text-danger"></span></label>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><input id="credit" name="featured" value="true" type="radio" class="form-check-input"
                                                           checked required ></td>
                                                <td><label class="form-check-label">Nổi bật</label></td>
                                                <td></td>
                                                <td><input id="debit" name="featured" value="false" type="radio" class="form-check-input"
                                                           required></td>
                                                <td><label class="form-check-label">Không nổi bật</label></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Status <span class="text-danger"></span></label>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><input id="credit" name="status" value="true" type="radio" class="form-check-input"
                                                           checked required ></td>
                                                <td><label class="form-check-label">Active</label></td>
                                                <td></td>
                                                <td><input id="debit" name="status" value="false" type="radio" class="form-check-input"
                                                           required></td>
                                                <td><label class="form-check-label">Disable</label></td>
                                            </tr>
                                        </tbody>
                                    </table>
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
