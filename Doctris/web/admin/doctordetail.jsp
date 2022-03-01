<%-- 
   Document   : doctordetail
   Created on : Feb 10, 2022, 12:04:52 AM
   Author     : Khuong Hung
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

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
                            <div class="col-lg-12">
                                <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link rounded active" id="pills-cloud-tab" data-bs-toggle="pill" href="#info" role="tab" aria-controls="infor" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Thông tin</h4>
                                            </div>
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link rounded" id="pills-smart-tab" data-bs-toggle="pill" href="#edit" role="tab" aria-controls="edit" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Chỉnh sửa</h4>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane show active row" id="info" role="tabpanel" aria-labelledby="info">
                                <div class="col-lg-12 col-md-12 mt-4">
                                    <div class="bg-white rounded shadow overflow-hidden">
                                        <div class="p-4 border-bottom">
                                            <h5 class="mb-0">Thông tin cá nhân</h5>
                                        </div>
                                        <br><br><br><br><br>
                                        <div class="text-center margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                            <c:if test="${doctor.img == 'default'}">
                                                <img src="assets/images/avata.png" class="img-fluid avatar avatar-large rounded" alt="">
                                            </c:if>
                                            <c:if test="${doctor.img != 'default'}">
                                                <img src="data:image/png;base64,${doctor.img}" class="img-fluid avatar avatar-large rounded" alt="">
                                            </c:if>
                                            <h5 class="mt-3 mb-1">${doctor.doctor_name}</h5>
                                            <p class="text-muted mb-0">${doctor.setting.name}</p>
                                        </div>

                                        <div class="p-4">
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-user align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Giới tính</h6>
                                                <c:if test="${doctor.gender == true}">
                                                    <p class="text-muted mb-0 ms-2">Nam</p>
                                                </c:if>
                                                <c:if test="${doctor.gender == false}">
                                                    <p class="text-muted mb-0 ms-2">Nữ</p>
                                                </c:if>     
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-envelope align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Ngày sinh</h6>
                                                <p class="text-muted mb-0 ms-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${doctor.DOB}" /></p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Số điện thoại</h6>
                                                <p class="text-muted mb-0 ms-2">0${doctor.phone}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Email</h6>
                                                <p class="text-muted mb-0 ms-2">${doctor.account.email}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Mô tả</h6>
                                                <p class="text-muted mb-0 ms-2">${doctor.description}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Role</h6>
                                                <p class="text-muted mb-0 ms-2">Doctor</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Trạng thái</h6>
                                                <c:if test="${doctor.status == true}">
                                                    <p class="text-muted mb-0 ms-2">Hoạt động</p>
                                                </c:if>
                                                <c:if test="${doctor.status == false}">
                                                    <p class="text-muted mb-0 ms-2">Khóa</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 mt-4">
                                    <div class="bg-white rounded shadow overflow-hidden">
                                        <div class="p-4 border-bottom">
                                            <h5 class="mb-0">Danh sách cuộc hẹn</h5>
                                        </div>
                                        <table class="table p-4 mb-0 table-center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" >ID</th>
                                                    <th class="border-bottom p-3" >Bệnh nhân</th>
                                                    <th class="border-bottom p-3" >Ngày</th>
                                                    <th class="border-bottom p-3" >Thời gian</th>
                                                    <th class="border-bottom p-3" >Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${appointment}" var="a">
                                                <tr>
                                                    <th class="p-3">${a.id}</th>
                                                    <td class="p-3">${a.patient.account.username}</td>
                                                    <td class="p-3"><fmt:formatDate pattern="dd/MM/yyyy" value="${a.date}" /></td>
                                                    <td class="p-3">${a.time}</td>
                                                    <td class="p-3">${a.status}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 mt-4">
                                    <div class="bg-white rounded shadow overflow-hidden">
                                        <div class="p-4 border-bottom">
                                            <h5 class="mb-0">Đánh giá từ bệnh nhân</h5>
                                        </div>
                                        <ul class="media-list list-unstyled p-4 mb-0">
                                            <c:forEach items="${rate}" var="r">
                                                <li class="mt-4">
                                                    <div class="d-flex justify-content-between">
                                                        <div class="d-flex align-items-center">
                                                            <a class="pe-3" href="#">
                                                                <c:if test="${r.user.img != 'default'}">
                                                                    <img src="data:image/png;base64,${r.user.img}" class="img-fluid avatar avatar-md-sm rounded-circle shadow" alt="img">
                                                                </c:if>
                                                                <c:if test="${r.user.img == 'default'}">
                                                                    <img src="assets/images/avata.png" class="img-fluid avatar avatar-md-sm rounded-circle shadow" alt="img">
                                                                </c:if>

                                                            </a>
                                                            <div class="commentor-detail">
                                                                <h6 class="mb-0"><a href="javascript:void(0)" class="text-dark media-heading">${r.user.username}</a></h6>
                                                                <small class="text-muted"><fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${r.date}"/></small>
                                                            </div>
                                                        </div>
                                                        <ul class="list-unstyled text-warning h5 mb-0">
                                                            <c:if test="${r.star != 0}">
                                                                <c:forEach var = "i" begin = "1" end = "${r.star}">
                                                                    <li class="list-inline-item"><i class="mdi mdi-star"></i></li>
                                                                    </c:forEach>
                                                                </c:if>
                                                        </ul>
                                                    </div>
                                                    <div class="mt-3">
                                                        <p class="text-muted font-italic p-3 bg-light rounded">${r.feedback}</p>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                            <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit">
                                <div class="card border-0 shadow overflow-hidden">
                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <form action="doctormanage?action=update_image&id=${doctor.doctor_id}" method="POST" enctype="multipart/form-data" onSubmit="document.getElementById('submit').disabled = true;">
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

                                        <form action="doctormanage?action=update_info&id=${doctor.doctor_id}" method="POST" class="mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Họ tên</label>
                                                        <input name="name" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" id="name" type="text" class="form-control" value="${doctor.doctor_name}">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Giới tính</label>
                                                        <table>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input id="credit" name="gender" ${doctor.gender==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                               checked required ></td>
                                                                    <td><label class="form-check-label">Nam</label></td>
                                                                    <td></td>
                                                                    <td><input id="debit" name="gender" ${doctor.gender==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                                               required></td>
                                                                    <td><label class="form-check-label">Nữ</label></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Số điện thoại</label>
                                                        <input name="phone" oninvalid="CheckPhone(this);" oninput="CheckPhone(this);" id="number" type="text" class="form-control" value="0${doctor.phone}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Ngày Sinh</label>
                                                        <input name="DOB" id="number" type="date" onkeydown="return false" min="1922-01-01" max="2003-01-01" class="form-control" value="${doctor.DOB}">
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Mô tả</label>
                                                        <input name="description" type="text" class="form-control" value="${doctor.description}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Chuyên môn</label>
                                                        <select name="speciality" class="form-select">
                                                            <c:forEach items="${speciality}" var="s">
                                                                <option <c:if test="${doctor.setting.name == s.name}">selected</c:if> class="form-control" value="${s.id}">${s.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Trạng thái <span class="text-danger"></span></label>
                                                        <table>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input id="credit" name="status" ${doctor.status==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                               checked required ></td>
                                                                    <td><label class="form-check-label">Hoạt động</label></td>
                                                                    <td></td>
                                                                    <td><input id="debit" name="status" ${doctor.status==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                                               required></td>
                                                                    <td><label class="form-check-label">Khóa</label></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
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
