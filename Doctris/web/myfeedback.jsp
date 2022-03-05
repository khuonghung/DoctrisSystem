<%-- 
    Document   : myfeedback
    Created on : Mar 3, 2022, 8:59:48 PM
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
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <jsp:include page="layout/profileMenu.jsp"/>
                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <h3 class="mb-0"></h3>
                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Phản hồi từ bệnh nhân</h5>
                            </div>
                            <div class="p-4">
                                <ul class="media-list list-unstyled mb-0">
                                    <c:forEach items="${ratelist}" var="r">
                                        <li>
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
                                <c:set var="page" value="${page}"/>
                                <div class="row text-center">
                                    <div class="col-12 mt-4">
                                        <div class="d-md-flex align-items-center text-center justify-content-between">
                                            <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                <c:forEach begin="${1}" end="${num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="doctor?action=myfeedback&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>

