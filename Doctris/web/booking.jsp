<%-- 
    Document   : booking
    Created on : Mar 8, 2022, 10:54:32 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-half-170 d-table w-100 bg-light">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Đặt lịch</h3>
                            <p class="para-desc mx-auto text-muted">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0 py-1">
                                    <li class="breadcrumb-item"><a href="index.html">Doctris</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Đặt Lịch</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>

        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7">
                        <div class="card border-0 shadow rounded overflow-hidden">
                            <div class="tab-content p-4" id="pills-tabContent">
                                <form action="book?type=checkout" method="POST">
                                    <div class="row">
                                        <div class="p-6">
                                            <h6 class="mb-0">Thông tin bệnh nhân</h6>
                                        </div>
                                        <br><br>
                                        <div class="col-lg-6">
                                            <div class="mb-3">
                                                <label class="form-label">Tên</label>
                                                <input class="form-control" value="${sessionScope.user.name}" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" class="form-control" value="0${sessionScope.user.phone}" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input type="text" class="form-control" value="${sessionScope.user.email}" readonly="">
                                            </div>
                                        </div>
                                        <br>
                                        <div class="p-6">
                                            <h6 class="mb-0">Thông tin lịch hẹn</h6>
                                        </div>
                                        <br><br>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label"> Ngày : </label>
                                                <input required="" name="date" type="text" class="flatpickr flatpickr-input form-control" id="checkin-date">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Thời gian</label>
                                                <select required="" name="time" class="form-control department-name select2input">
                                                    <option value="7:00">7:00</option>
                                                    <option value="8:00">8:00</option>
                                                    <option value="9:00">9:00</option>
                                                    <option value="10:00">10:00</option>
                                                    <option value="11:00">11:00</option>
                                                    <option value="14:00">14:00</option>
                                                    <option value="15:00">15:00</option>
                                                    <option value="16:00">16:00</option>
                                                    <option value="17:00">17:00</option>
                                                </select>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Thông tin bệnh lý<span class="text-danger">*</span></label>
                                                <textarea required="" name="description" id="comments2" rows="4" class="form-control"></textarea>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-md-12">
                                            <div class="mb-3">
                                                <label class="form-label">Phương thức thanh toán</label>
                                                <select name="payment" oninvalid="Select(this);" oninput="Select(this);" id="mySelect" class="form-control department-name select2input">
                                                    <option selected="">Chọn phương thức thanh toán</option>
                                                    <option value="default">Thanh toán sau khi khám</option>
                                                    <option value="vnpay">Thanh toán thông qua VNPay</option>
                                                </select>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="d-grid">
                                                <button type="submit" style="display: none" id="booking" class="default btn btn-primary">Đặt lịch</button>
                                                <button type="submit"  style="display: none" id="booking" class="vnpay btn btn-primary">Thanh toán</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.type == 'appointment'}">
                        <div class="col-lg-5">
                            <div class="bg-white rounded shadow overflow-hidden">
                                <div class="p-4 border-bottom">
                                    <h6 class="mb-0">Thông tin bác sĩ</h6>
                                </div>
                                <br><br><br><br><br>
                                <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                    <c:if test="${sessionScope.doctor.img == 'default'}">
                                        <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                    </c:if>
                                    <c:if test="${sessionScope.doctor.img != 'default'}">
                                        <img src="data:image/png;base64,${sessionScope.doctor.img}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                    </c:if>
                                    <h5 class="mt-3 mb-1">${sessionScope.doctor.doctor_name}</h5>
                                </div>

                                <div class="list-unstyled p-4">
                                    <div class="d-flex align-items-center mt-3">
                                        <h6 class="mb-0">Phí : </h6>
                                        <p class="text-muted mb-0 ms-2"><fmt:formatNumber pattern="#,###,###,###" value="${sessionScope.doctor.fee}" /> đ</p>
                                    </div>
                                    <div class="d-flex align-items-center mt-3">
                                        <h6 class="mb-0">Đánh giá : </h6>
                                        <c:if test="${star != 0}">
                                            <c:forEach var = "i" begin = "1" end = "${star}">
                                                <li class="list-inline-item mb-0 ms-2"><i class="mdi mdi-star text-warning"></i></li>
                                                </c:forEach>
                                                <c:forEach var = "i" begin = "1" end = "${5-star}">
                                                <li class="list-inline-item mb-0 ms-2"><i class="mdi mdi-star"></i></li>
                                                </c:forEach>
                                            </c:if>
                                        <li class="list-inline-item me-2 h6 text-muted mb-0 ms-2">(${feedback} Đánh giá)</li>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.type == 'reservation'}">
                        <div class="col-lg-5">
                            <div class="bg-white rounded shadow overflow-hidden">
                                <div class="p-4 border-bottom">
                                    <h6 class="mb-0">Thông tin dịch vụ</h6>
                                </div>
                                <br><br><br><br><br>
                                <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                    <c:if test="${sessionScope.service.img == 'default'}">
                                        <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                    </c:if>
                                    <c:if test="${sessionScope.service.img != 'default'}">
                                        <img src="data:image/png;base64,${sessionScope.service.img}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                    </c:if>
                                    <h5 class="mt-3 mb-1">${sessionScope.service.title}</h5>
                                </div>

                                <div class="list-unstyled p-4">
                                    <div class="d-flex align-items-center mt-3">
                                        <h6 class="mb-0">Phí : </h6>
                                        <p class="text-muted mb-0 ms-2"><fmt:formatNumber pattern="#,###,###,###" value="${sessionScope.service.fee}" /> đ</p>
                                    </div>
                                    <div class="d-flex align-items-center mt-3">
                                        <h6 class="mb-0">Đánh giá : </h6>
                                        <c:if test="${sessionScope.service.ratestar.star != 0}">
                                            <c:forEach var = "i" begin = "1" end = "${sessionScope.service.ratestar.star}">
                                                <li class="list-inline-item mb-0 ms-2"><i class="mdi mdi-star text-warning"></i></li>
                                                </c:forEach>
                                                <c:forEach var = "i" begin = "1" end = "${5-sessionScope.service.ratestar.star}">
                                                <li class="list-inline-item mb-0 ms-2"><i class="mdi mdi-star"></i></li>
                                                </c:forEach>
                                            </c:if>
                                        <li class="list-inline-item me-2 h6 text-muted mb-0 ms-2">(${sessionScope.service.ratestar.countfeedback} Đánh giá)</li>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>
            <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/flatpickr.min.js"></script>
        <script src="assets/js/flatpickr.init.js"></script>
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/select2.init.js"></script>
        <script src="assets/js/jquery.timepicker.min.js"></script> 
        <script src="assets/js/timepicker.init.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/vn.js"></script>
        <script src="assets/js/app.js"></script>
        <script src="assets/js/sweetalert.min.js"></script>
        <script>
            $("#checkin-date").flatpickr({
                defaultDate: "today",
                minDate: "today",
                maxDate: new Date().fp_incr(14),
                dateFormat: "d/m/Y",
                locale: "vn"
            });
            function Select(text) {
                if (text.value == "") {
                    $(".default").hide();
                    $(".vnpay").hide();
                } else if (text.value == "default") {
                    $(".default").show();
                    $(".vnpay").hide();
                } else if (text.value == "vnpay") {
                    $(".vnpay").show();
                    $(".default").hide();
                } else {
                    $(".default").hide();
                    $(".vnpay").hide();
                }
            }
            $(document).ready(jQuery(function () {
                jQuery(".default").click(function (e) {
                    e.preventDefault();
                    var form = $(this).parents('form');
                    swal({
                        title: "Xác nhận",
                        text: "Bạn có chắc chắn muốn khởi tạo lịch hẹn này ?",
                        buttons: ["Hủy bỏ", "Đồng ý"],
                    })
                            .then((cofirm) => {
                                if (cofirm) {
                                    form.submit();
                                }
                            })
                });
            }));

            $(document).ready(jQuery(function () {
                jQuery(".vnpay").click(function (e) {
                    e.preventDefault();
                    var form = $(this).parents('form');
                    swal({
                        title: "Xác nhận",
                        text: "Bạn có chắc chắn muốn thanh toán ?",
                        buttons: ["Hủy bỏ", "Đồng ý"],
                    })
                            .then((cofirm) => {
                                if (cofirm) {
                                    form.submit();
                                }
                            })
                });
            }));
        </script>

    </body>

</html>