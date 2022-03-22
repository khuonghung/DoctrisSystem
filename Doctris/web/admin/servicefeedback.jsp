<%-- 
    Document   : servicefeedback
    Created on : Feb 25, 2022, 3:35:48 PM
    Author     : Khuong Hung
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .checked {
            color: orange;
        }
    </style>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="col-12 mt-4">
                            <div class="bg-white rounded shadow overflow-hidden">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Tên</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Số Sao</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Đánh giá</th>
                                                <th class="border-bottom p-3" style="min-width: 180px;">Time</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${ratestars}" var="r">
                                                <tr>
                                                    <td class="p-3"> 
                                                        <div class="d-flex align-items-center">
                                                            <c:if test="${r.user.img == 'default'}">
                                                                <img src="assets/images/avata.png" class="avatar avatar-md-sm rounded-circle shadow" alt="">
                                                            </c:if>
                                                            <c:if test="${r.user.img != 'default'}">
                                                                <img src="data:image/png;base64,${r.user.img}" class="avatar avatar-small rounded-circle shadow" alt="">
                                                            </c:if>
                                                            <span class="ms-2">${r.user.name}</span>
                                                        </div>
                                                    </td>
                                                    <td class="p-3">
                                                        <c:if test="${r.star == 1}">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star "></span>
                                                            <span class="fa fa-star "></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                        </c:if>
                                                        <c:if test="${r.star == 2}">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star "></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                        </c:if>
                                                        <c:if test="${r.star == 3}">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                        </c:if>
                                                        <c:if test="${r.star == 4}">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star"></span>
                                                        </c:if>
                                                        <c:if test="${r.star == 5}">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                        </c:if>
                                                    </td>
                                                    <td class="p-3">${r.feedback}</td>
                                                    <td class="p-3"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${r.date}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>

                                    </table>


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
    </body>
</html>
