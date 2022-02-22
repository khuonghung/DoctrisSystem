<%-- 
    Document   : doctor
    Created on : Feb 22, 2022, 3:02:33 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-half-150 bg-light d-table w-100">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Danh sách bác sĩ</h3>
<p class="para-desc mx-auto text-muted">Nếu bạn,người nhà của mình cần nhận được sự trợ giúp ngay lập tức, điều trị khẩn cấp hãy đặt lịch hẹn.</p>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Doctors</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section">
            <div class="container">
                <div class="row align-items-center">
                    <c:forEach items="${doctor}" var="d">
                        <div class="col-xl-3 col-lg-3 col-md-6 mt-4 mt-lg-0 pt-2 pt-lg-0">
                            <div class="card team border-0 rounded shadow overflow-hidden">
                                <div class="team-person position-relative overflow-hidden">
                                    <img src="data:image/png;base64,${d.img}" style="display: block;margin-left: auto;margin-right: auto;" class="img-fluid" alt="">
                                </div>
                                <div class="card-body">
                                    <a href="#" class="title text-dark h5 d-block mb-0">${d.doctor_name}</a>

                                    <small class="text-muted speciality">${d.setting.name}</small>
                                    <div class="d-flex justify-content-between align-items-center mt-2">
                                        <ul class="list-unstyled mb-0">
                                            <c:if test="${d.rateStar.star != 0}">
                                                <c:forEach var = "i" begin = "1" end = "${d.rateStar.star}">
                                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                                    </c:forEach>
                                                    <c:forEach var = "i" begin = "1" end = "${5-d.rateStar.star}">
                                                    <li class="list-inline-item"><i class="mdi mdi-star"></i></li>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${d.rateStar.star == 0}">
                                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                                </c:if>
                                        </ul>
                                        <p class="text-muted mb-0">${d.rateStar.countfeedback} feedbacks</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
        </section>
        <jsp:include page="layout/footer.jsp"/>

        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>

        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>
