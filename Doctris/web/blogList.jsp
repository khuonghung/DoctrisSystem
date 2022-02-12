<%-- 
    Document   : blogList
    Created on : Jan 23, 2022, 3:04:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>

        <jsp:include page="layout/menu.jsp"/>




        <!-- Start Hero -->
        <section class="bg-half-170 d-table w-100" style="background: url('assets/images/bg/banner.jpg') center center;">
            <div class="bg-overlay bg-overlay-dark"></div>
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4 text-white title-dark">Blogs & Tin Tức</h3>
                            <p class="para-desc mx-auto text-white-50">Bạn có thể trở thành một bác sĩ bất đắc dĩ trong gia đình với những kiến thức tại doctris blog. Tại đây chúng tôi luôn cập nhập những thông tin bổ ích về sức khỏe, sinh lý, sơ cứu,...</p>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-light rounded mb-0 py-1 px-2">
                                    <li class="breadcrumb-item"><a href="index.html">Doctris</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Blogs</li>
                                </ul>
                            </nav>
                        </div>
                    </div><!--end col-->
                </div><!--end row-->
            </div><!--end container-->
        </section><!--end section-->

        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>
        <!-- End Hero -->

        <!-- Start -->
        <section class="section">
            <div class="container">


                <div class="row">
                    <div class="col-lg-8 col-lg-8 row">
                        <c:forEach items="${listblog}" var="b">
                            <div class="col-lg-4 col-md-6 col-12 mb-4 pb-2">
                                <div class="card blog blog-primary border-0 shadow rounded overflow-hidden">
                                    <img src="${b.img}" class="img-fluid" alt="">
                                    <div class="card-body p-4">
                                        <ul class="list-unstyled mb-2">
                                            <li class="list-inline-item text-muted small me-3"><i class="uil uil-calendar-alt text-dark h6 me-1"></i>${b.date}</li>
                                        </ul>
                                        <a href="blogs?action=detail&blog_id=${b.blog_id}" class="text-dark title h7">${b.title}</a>
                                        <c:set var = "detail" value = "${b.describe}"/>
                                        <c:set var = "brief_info" value = "${fn:substring(detail, 0, 50)}" />
                                        <p style="font-size: 9">${brief_info}[...]</p>
                                        <div class="post-meta d-flex justify-content-between mt-3">
                                            <ul class="list-unstyled mb-0">
                                                <li class="list-inline-item me-2 mb-0" style="color:gray">${b.category.name}</li>
                                            </ul>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </c:forEach>
                    </div><!--end col-->

                    <!-- right sider start --> 
                    <div class="col-lg-4 col-md-4 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="card border-0 sidebar sticky-bar rounded shadow">
                            <div class="card-body">
                                <!-- SEARCH -->
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Tìm Kiếm</h5>
                                    <div id="search2" class="widget-search mt-4 mb-0">
                                        <form action="blogs?action=search" method="POST" id="searchform" class="searchform">
                                            <div>
                                                <input type="text" class="border rounded" name="content" id="s" placeholder="Nhập từ khóa">
                                                <input type="submit" id="searchsubmit" value="Search">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- SEARCH -->

                                <!-- RECENT POST -->
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Bài Đăng Nổi Bật</h5>
                                    <div class="mt-4">
                                        <c:forEach items="${featured_blogs}" var="fb">
                                            <div class="clearfix post-recent">
                                                <div class="post-recent-thumb float-start"> <a href="jvascript:void(0)"> <img alt="img" src="${fb.img}" class="img-fluid rounded"></a></div>
                                                <div class="post-recent-content float-start"><a href="jvascript:void(0)">${fb.title}</a></div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <!-- RECENT POST -->

                                <!-- TAG CLOUDS -->
                                <div class="widget mb-4 pb-2">
                                    <h5 class="widget-title">Danh Mục</h5>
                                    <div class="tagcloud mt-4">
                                        <c:forEach items="${categories}" var='c'>
                                            <a href="blogs?action=category&id=${c.id}" class="rounded">${c.name}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                                <!-- TAG CLOUDS -->

                            </div>
                        </div>
                    </div>
                    <!-- right sider end --> 

                </div>

                <!--end row-->

                <c:set var="page" value="${page}"/>
                <div class="row text-center">
                    <div class="col-12 mt-4">
                        <div class="d-md-flex align-items-center text-center justify-content-between">
                            <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">

                                <c:forEach begin="${1}" end="${num}" var="i">
                                    <c:if test="${requestScope.type == 1}">
                                        <li class="page-item ${i==page?"active":""}"><a class="page-link" href="blogs?action=User&page=${i}">${i}</a></li>
                                        </c:if>
                                        <c:if test="${requestScope.type == 2}">
                                        <li class="page-item ${i==page?"active":""}"><a class="page-link" href="blogs?action=Category_blog&page=${i}">${i}</a></li>
                                        </c:if>
                                        <c:if test="${requestScope.type == 3}">
                                        <li class="page-item ${i==page?"active":""}"><a class="page-link" href="blogs?action=Category_service&page=${i}">${i}</a></li>
                                        </c:if>
                                        <c:if test="${requestScope.type == 0}">
                                        <li class="page-item ${i==page?"active":""}"><a class="page-link" href="blogs?page=${i}">${i}</a></li>
                                        </c:if>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>

            </div><!--end container-->
        </section><!--end section-->
        <!-- End -->

        <!-- Start -->
        <jsp:include page="layout/footer.jsp"/>
        <!-- End -->

        <!-- Back to top -->
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
        <!-- Back to top -->

        <!-- javascript -->
        <script src="../assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="../assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="../assets/js/app.js"></script>

    </body>

</html>
