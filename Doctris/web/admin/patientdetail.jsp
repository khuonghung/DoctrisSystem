<%-- 
    Document   : patientdetail
    Created on : Feb 7, 2022, 9:30:17 PM
    Author     : Trung
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

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
                                            <h5 class="mb-0">Thông tin Dịch Vụ</h5>
                                        </div>
                                        <br><br><br><br><br>
                                        <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                            <c:if test="${patient.account.img == 'default'}">
                                                <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                            </c:if>
                                            <c:if test="${patient.account.img != 'default'}">
                                                <img src="data:image/png;base64,${patient.account.img}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                            </c:if>
                                            <h5 class="mt-3 mb-1">${patient.account.name}</h5>
                                            <p class="text-muted mb-0">${patient.account.username}</p>
                                        </div>

                                        <div class="list-unstyled p-4">
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Patient_id</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.patient_id}</p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-user align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Giới tính</h6>
                                                <c:if test="${patient.account.gender == true}">
                                                    <p class="text-muted mb-0 ms-2">Nam</p>
                                                </c:if>
                                                <c:if test="${patient.account.gender == false}">
                                                    <p class="text-muted mb-0 ms-2">Nữ</p>
                                                </c:if>     
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Số điện thoại</h6>
                                                <p class="text-muted mb-0 ms-2">0${patient.account.phone}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Email</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.account.email}</p>
                                            </div>


                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Ngày sinh</h6>
                                                <p class="text-muted mb-0 ms-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${patient.DOB}" /></p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Địa chỉ</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.address}</p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Trạng thái</h6>
                                                <c:if test="${patient.status == true}">
                                                    <p class="text-muted mb-0 ms-2">Hoạt động</p>
                                                </c:if>
                                                <c:if test="${patient.status == false}">
                                                    <p class="text-muted mb-0 ms-2">Khóa</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit">
                                <div class="card border-0 shadow overflow-hidden">
                                    <div class="tab-content p-4" id="pills-tabContent">
                                        <form action="patientmanage?action=update_patient&username=${patient.account.username}" method="POST" class="mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Họ tên <span class="text-danger"></span></label>
                                                        <input name="name" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" id="name" type="text" class="form-control" value="${patient.account.name}">
                                                    </div>
                                                </div>
                                                <input hidden name="patient_id" id="name" type="text" class="form-control" value="${patient.patient_id}">
                                                <input hidden name="username" id="name" type="text" class="form-control" value="${patient.account.username}">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Giới tính <span class="text-danger"></span></label>
                                                        <table>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input id="credit" name="gender" ${patient.account.gender==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                               checked required ></td>
                                                                    <td><label class="form-check-label">Nam</label></td>
                                                                    <td></td>
                                                                    <td><input id="debit" name="gender" ${patient.account.gender==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                                               required></td>
                                                                    <td><label class="form-check-label">Nữ</label></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Số điện thoại <span class="text-danger"></span></label>
                                                        <input name="phone" oninvalid="CheckPhone(this);" oninput="CheckPhone(this);" id="name" type="text" class="form-control" value="0${patient.account.phone}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Địa chỉ <span class="text-danger"></span></label>
                                                        <input name="address" id="name" type="text" class="form-control" value="${patient.address}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Ngày sinh <span class="text-danger"></span></label>
                                                        <input name="DOB" id="name" type="date" onkeydown="return false" min="1922-01-01" max="2003-01-01" class="form-control" value="${patient.DOB}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Trạng thái <span class="text-danger"></span></label>
                                                        <table>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input id="credit" name="status" ${patient.status==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                                               checked required ></td>
                                                                    <td><label class="form-check-label">Hoạt động</label></td>
                                                                    <td></td>
                                                                    <td><input id="debit" name="status" ${patient.status==false?"checked":""} value="false" type="radio" class="form-check-input"
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
