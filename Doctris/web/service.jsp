<%-- 
    Document   : service.jsp
    Created on : Feb 27, 2022, 8:28:30 PM
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
        <section class="bg-half-170 bg-light d-table w-100">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Danh sách dịch vụ</h3>
                            <p class="para-desc mx-auto text-muted">Nếu bạn,người nhà của mình cần nhận được sự trợ giúp ngay lập tức, điều trị khẩn cấp hãy đặt lịch hẹn.</p>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#">Danh sách dịch vụ</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="row" style="margin-top: 50px;margin-left: 50px  ">
            <div class="col-md-4 row">
                <div class="col-md-3">

                </div>
                <div class="col-md-9">
                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                        <div id="search" class="menu-search mb-0">
                            <form action="service?action=search" method="POST" id="searchform" class="searchform">
                                <div>
                                    <input type="text" value="${requestScope.viet}"  class="form-control border rounded-pill" name="search" id="s" placeholder="Tìm kiếm tên dịch vụ...">
                                    <input type="submit" id="searchsubmit" value="Search">
                                </div>
                            </form>
                        </div>
                    </div> 
                </div>
            </div>
            <div class="col-md-6">
                <form action="service?action=filter" method="POST" onSubmit="">
                    <div class="justify-content-md-end row">
                        <div class="col-md-6 row align-items-center">
                            <div class="col-md-2">
                                <label class="form-label">Sort</label>
                            </div>
                            <div class="col-md-10">
                                <select name="sort" class="form-select">
                                    <option value="1">Tên từ thấp tới cao</option>
                                    <option value="2" >Tên từ cao tới thấp</option>
                                    <option value="3" >Giá từ thấp tới cao</option>
                                    <option value="4" >Giá từ cao tới thấp</option>

                                </select>  
                            </div>
                        </div>
                        <div class="col-md-5 row align-items-center">
                            <div class="col-md-4">
                                <label class="form-label">Speciality</label>
                            </div>
                            <div class="col-md-8">
                                <select name="Speciality" class="form-select">
                                    <option value="1 or (1=1)">Tất cả</option>
                                    <c:forEach items="${speciality}" var="s">
                                        <option value="${s.img}">${s.description}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-1 md-0">
                            <button type="submit" class="btn btn-primary">Lọc</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <section class="section">
            <div class="container">
                <div class="row align-items-center">
                    <table class="table mb-0 table-center">
                        <thead>
                            <tr>
                                <th class="border-bottom p-3" >Title</th>
                                <th class="border-bottom p-3" >Category name</th>
                                <th class="border-bottom p-3" >Rated star</th>
                                <th class="border-bottom p-3" >Number of feedbacks</th>
                                <th class="border-bottom p-3" >Fee</th>
                                <th class="border-bottom p-3" >Description</th>
                                <th class="border-bottom p-3" ></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${serviceList}" var="d">
                                <tr>
                                    <th class="p-3">${d.title}</th>
                                    <td class="p-3">${d.setting.setting_name}</td>
                                    <td class="p-3" style="width: 130px"><c:if test="${d.ratestar.star != 0}">
                                            <c:forEach var = "i" begin = "1" end = "${d.ratestar.star}">
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                    </c:forEach>
                                    <c:forEach var = "i" begin = "1" end = "${5-d.ratestar.star}">
                                    <li class="list-inline-item"><i class="mdi mdi-star"></i></li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${d.ratestar.star == 0}">
                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                                <li class="list-inline-item"><i class="mdi mdi-star"></i></li> 
                            </c:if></td>
                            <td class="p-3">${d.ratestar.countfeedback} feedbacks</td>
                            <td class="p-3"><fmt:formatNumber pattern="#,###,###,###" value="${d.fee}"/>đ</td>
                            <td class="p-3" style="width: 250px">${d.description}</td>
                            <td class="text-end p-3">
                                <a href="service?action=detail&id=${d.service_id}" type="button"class="btn btn-info" style="">Chi tiết</a>
                                <a href="book?type=reservation&id=${d.service_id}" type="button"class="btn btn-info" style="">Đặt lịch</a>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

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
