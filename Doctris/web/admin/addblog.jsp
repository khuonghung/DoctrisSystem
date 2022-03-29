<%-- 
    Document   : mkt_blogdetail
    Created on : Feb 23, 2022, 2:51:36 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <div class="card border-0 shadow overflow-hidden">
                                <div class="tab-content p-4" id="pills-tabContent">
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
                                                                            <img src="" id="output" width="200" alt=""/>
                                                                        </c:if>
                                                                    </label>
                                                                    <br><br>
                                                                    <input id="file" type="file" onchange="loadFile(event)" name="image"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Thông Tin Tóm Tắt<span class="text-danger">*</span></label>
                                                        <textarea rows="" cols="" id="brief" name="brief" style="width: 720px; height:50px" ></textarea>
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
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/simplebar.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>

                                                                        function readURL(input, thumbimage) {
                                                                            if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
                                                                                var reader = new FileReader();
                                                                                reader.onload = function (e) {
                                                                                    $("#thumbimage").attr('src', e.target.result);
                                                                                }
                                                                                reader.readAsDataURL(input.files[0]);
                                                                            } else { // Sử dụng cho IE
                                                                                $("#thumbimage").attr('src', input.value);

                                                                            }
                                                                            $("#thumbimage").show();
                                                                            $('.filename').text($("#uploadfile").val());
                                                                            $(".Choicefile").hide();
                                                                            $(".Update").show();
                                                                            $(".removeimg").show();
                                                                        }
                                                                        var editor = '';
                                                                        $(document).ready(function () {
                                                                            editor = CKEDITOR.replace('brief');
                                                                            editor = CKEDITOR.replace('describe');
                                                                            $(".Choicefile").bind('click', function () {
                                                                                $("#uploadfile").click();

                                                                            });
                                                                            $(".removeimg").click(function () {
                                                                                $("#thumbimage").attr('src', '').hide();
                                                                                $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                                                                                $(".removeimg").hide();
                                                                                $(".Choicefile").show();
                                                                                $(".Update").hide();
                                                                                $(".filename").text("");
                                                                            });

                                                                        })

        </script>
    </body>

</html>

