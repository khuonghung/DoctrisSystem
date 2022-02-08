<%-- 
    Document   : patientdetail
    Created on : Feb 7, 2022, 9:30:17 PM
    Author     : Trung
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            <div class="col-lg-4 col-md-5 mt-4">
                                <div class="bg-white rounded shadow overflow-hidden">
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
                                            <p class="text-muted mb-0 ms-2">${patient.DOB}</p>
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
                                        <!--edit-->
                        </div>
                    </div>
                </div>

                <footer class="bg-white shadow py-3">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-sm-start text-center">
                                    <p class="mb-0 text-muted">
                                        <script>document.write(new Date().getFullYear())</script>
                                        © Doctris.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </footer>
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
