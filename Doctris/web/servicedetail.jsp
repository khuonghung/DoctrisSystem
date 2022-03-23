<%-- 
    Document   : servicedetail.jsp
    Created on : Feb 27, 2022, 8:31:51 PM
    Author     : Dell
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
                            <h3 class="sub-title mb-4">Dịch vụ</h3>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <p class="para-desc mx-auto text-muted">Nếu bạn,người nhà của mình cần nhận được sự trợ giúp ngay lập tức, điều trị khẩn cấp hãy đặt lịch hẹn.</p>
                                    <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                        <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                        <li class="breadcrumb-item"><a href="service?action=all">Danh sách dịch vụ</a></li>
                                        <li class="breadcrumb-item"><a href="#">Chi tiết</a></li>
                                    </nav>
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
                <div class="row align-items-center">
                    <div class="col-md-5">
                        <div class="slider slider-for">
                            <div><img src="http://phunuvietnam.mediacdn.vn/media/news/3a09a524440d44d7f19870070a5ad42f/Dich-vu-7.jpg" class="img-fluid rounded" alt=""></div>
                        </div>
                    </div>


                    <div class="col-md-7 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="section-title ms-md-4">
                            <h4 class="title">${service.title}</h4>
                            <p class="text-muted">${service.setting.setting_name}</p>
                            <h5 class="text-muted"><fmt:formatNumber pattern="#,###,###,###" value="${service.fee}"/> đ</h5>
                            <ul class="list-unstyled text-warning h5 mb-0">
                                <c:if test="${service.ratestar.star != 0}">
                                    <c:forEach var = "i" begin = "1" end = "${service.ratestar.star}">
                                        <li class="list-inline-item"><i class="mdi mdi-star"></i></li>
                                        </c:forEach>
                                    </c:if>
                                <li class="list-inline-item me-2 h6 text-muted">(${service.ratestar.countfeedback} Đánh giá)</li>
                            </ul>

                            <h5 class="mt-4 py-2">Mô tả :</h5>
                            <p class="text-muted">${service.description}</p>
                            <div class="mt-4 pt-2">
                                <c:if test="${user.getRole().getRole_id() == 2}">
                                    <a href="book?type=reservation&id=${service.service_id}" class="btn btn-primary">Đặt lịch</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container mt-100 mt-60">
                <div class="row">
                    <div class="col-12">
                        <h5 class="mb-0">Đánh giá từ người dùng :</h5>
                    </div>
                </div>

                <div class="row">
                    <ul class="media-list list-unstyled mb-0">
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
        </section>

        <jsp:include page="layout/footer.jsp"/>

        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>
            <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/tiny-slider.js "></script>
        <script src="assets/js/tiny-slider-init.js "></script>
        <script src="assets/js/slick.min.js"></script>
        <script src="assets/js/slick.init.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>