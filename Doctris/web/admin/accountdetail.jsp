<%-- 
    Document   : accountdetail
    Created on : Feb 4, 2022, 9:48:49 PM
    Author     : Khuong Hung
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
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
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 mt-4">
                                <div class="card border-0 shadow overflow-hidden">
                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <form action="account?action=update_image&username=${account.username}" method="POST" enctype="multipart/form-data" onSubmit="document.getElementById('submit').disabled = true;">
                                            <h5 class="mb-0">Chỉnh sửa thông tin :</h5>
                                            <div>
                                                <p class="text-muted">Cập nhật ảnh đại diện.</p>
                                                <div id="myfileupload">
                                                    <input type="file" name="image" id="uploadfile" name="ImageUpload" onchange="readURL(this);" />
                                                </div>
                                                <div id="thumbbox">
                                                    <img class="rounded" height="20%" width="30%" alt="Thumb image" id="thumbimage" style="display: none" />
                                                    <a class="removeimg" href="javascript:"></a>
                                                </div>
                                                <div id="boxchoice">
                                                    <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                                    <p style="clear:both"></p>
                                                    <input type="submit" id="submit" style="display: none" name="send" class="Update btn btn-primary"
                                                           value="Cập nhật">
                                                    <p style="clear:both"></p>
                                                </div> 
                                            </div>
                                        </form>

                                        <form action="account?action=update_account&username=${account.username}" method="POST" class="mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Tên đăng nhập</label>
                                                        <input readonly="" id="name" type="text" class="form-control" value="${account.username}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Email</label>
                                                        <input readonly="" id="name" type="text" class="form-control" value="${account.email}">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Họ tên</label>
                                                        <input name="name" id="name" type="text" class="form-control" value="${account.name}">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Giới tính</label>
                                                        <table>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input id="credit" name="gender" ${account.gender==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                               checked required ></td>
                                                                    <td><label class="form-check-label">Nam</label></td>
                                                                    <td></td>
                                                                    <td><input id="debit" name="gender" ${account.gender==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                                               required></td>
                                                                    <td><label class="form-check-label">Nữ</label></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Quyền <span class="text-danger">*</span></label>
                                                        <select name="role_id" class="form-select" aria-label="Default select example">
                                                            <c:forEach items="${role}" var="r">
                                                                <option <c:if test="${account.role.name == r.name}">selected</c:if> value="${r.role_id}">${r.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                                        <select name="status" class="form-select" aria-label="Default select example">
                                                            <option <c:if test="${account.status == true}">selected</c:if> value="true">Active</option>
                                                            <option <c:if test="${account.status == false}">selected</c:if> value="false">Disable</option>
                                                            </select>
                                                        </div>
                                                    </div>


                                                    <div class="col-lg-12">
                                                        <div class="mb-3">
                                                            <label class="form-label">Số điện thoại</label>
                                                            <input name="phone" id="number" type="text" class="form-control" value="0${account.phone}">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <input type="submit" id="submit" name="send" class="btn btn-primary"
                                                           value="Cập nhật">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

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
                                            $(document).ready(function () {
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