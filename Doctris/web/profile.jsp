<%-- 
    Document   : profile
    Created on : Jan 20, 2022, 10:49:05 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/preloader.jsp"/>
        <header id="topnav" class="navigation sticky">
            <div class="container">

                <div>
                    <a class="logo" href="home">
                        <img src="assets/images/logo-light.png" height="24" class="logo--mode" alt="">
                    </a>
                </div>

                <div class="menu-extras">
                    <div class="menu-item">
                        <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                            <div class="lines">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </a>
                    </div>
                </div>

                <ul class="dropdowns list-inline mb-0">

                    <li class="list-inline-item mb-0 ms-1">
                        <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-primary" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
                            <i class="uil uil-search"></i>
                        </a>
                    </li>

                    <li class="list-inline-item mb-0 ms-1">
                        <div class="dropdown dropdown-primary">
                            <c:if test="${sessionScope.user != null}">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${sessionScope.user.img}" class="avatar avatar-ex-small rounded-circle" alt=""></button> 
                                </c:if>
                                <c:if test="${sessionScope.user == null}">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/avata.png" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                                </c:if>
                            <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                                <c:if test="${sessionScope.user.username != null}">
                                    <a class="dropdown-item d-flex align-items-center text-" href="#">
                                        <img src="${sessionScope.user.img}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                        <div class="flex-1 ms-2">
                                            <span class="d-block mb-1">${sessionScope.user.username}</span>
                                        </div>                     
                                    </a>
                                </c:if>
                                <div class="dropdown-divider border-top"></div>
                                <c:if test="${sessionScope.user != null}">
                                    <a class="dropdown-item text-" href="user?action=profile"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Tài khoản của tôi</a>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <a class="dropdown-item text-" href="user?action=logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Đăng xuất</a>
                                </c:if>
                                <c:if test="${sessionScope.user == null}">
                                    <a class="dropdown-item text-" href="user?action=login"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Đăng Nhập</a>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <a class="dropdown-item text-" href="setting"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Setting</a>
                                </c:if>
                            </div>
                        </div>
                    </li>

                </ul>

                <div id="navigation">

                    <ul class="navigation-menu nav-left">
                        <li><a href="home" class="sub-menu-item">Trang chủ</a></li>
                        <li><a href="#" class="sub-menu-item">Bác sĩ</a></li>
                        <li><a href="#" class="sub-menu-item">Dịch vụ</a></li>
                        <li><a href="#" class="sub-menu-item">Chúng tôi</a></li>
                        <li><a href="contact" class="sub-menu-item">Liên hệ</a></li>
                        <li><a href="#" class="sub-menu-item">Tin tức & chủ đề</a></li>
                    </ul>

                </div>
            </div>
        </header>
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-4 col-lg-4 col-md-5 col-12">
                        <div class="rounded shadow overflow-hidden sticky-bar">
                            <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                <br><br><br><br>
                                <img src="${sessionScope.user.img}">
                                <h5 class="mt-3 mb-1">${sessionScope.user.name}</h5>
                                <p class="text-muted mb-0">${sessionScope.user.username}</p>
                            </div>

                            <ul class="list-unstyled sidebar-nav mb-0">
                                <li class="navbar-item"><a href="doctor-dashboard.html" class="navbar-link"><i class="ri-airplay-line align-middle navbar-icon"></i> Dashboard</a></li>
                            </ul>
                        </div>
                    </div><!--end col-->

                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <h3 class="mb-0"></h3>
                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Thông tin tài khoản</h5>
                                <p style="color: blue; align-content: center;">
                                    ${requestScope.updatesuccess}
                                </p>
                            </div>
                            <div class="p-4">
                                <form action="user?action=updateprofile" method="POST">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Tên người dùng</label>
                                                <input name="username" readonly value="${sessionScope.user.username}" id="name" type="text" class="form-control">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input name="email" readonly value="${sessionScope.user.email}" id="email" type="email" class="form-control">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Họ tên</label>
                                                <input name="name" readonly value="${sessionScope.user.name}" id="name2" type="text" class="form-control" >
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Số điện thoại</label>
                                                <input name="phone" value="${sessionScope.user.phone}" id="number" type="text" class="form-control">
                                            </div>                                                                               
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Giới tính</label>
                                                <div class="my-3">
                                                    <div class="form-check">
                                                        <input id="credit" name="gender" ${sessionScope.user.gender==true?"checked":""} value="true" type="radio" class="form-check-input"
                                                               checked required >
                                                        <label class="form-check-label">Nam</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input id="debit" name="gender" ${sessionScope.user.gender==false?"checked":""} value="false" type="radio" class="form-check-input"
                                                               required>
                                                        <label class="form-check-label">Nữ</label>
                                                    </div>
                                                </div>
                                            </div>                                                                               
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="submit" id="submit" name="send" class="btn btn-primary" value="Lưu">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Đổi mật khẩu :</h5>
                                <p style="color: red; align-content: center;">
                                    ${requestScope.passerror}
                                </p>
                                <p style="color: blue; align-content: center;">
                                    ${requestScope.passsuccess}
                                </p>
                            </div>

                            <div class="p-4">
                                <form action="user?action=changepassword" method="POST">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu cũ :</label>
                                                <input type="password" name="oldpassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu mới :</label>
                                                <input type="password" name="newpassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Nhập lại mật khẩu :</label>
                                                <input type="password" name="renewpassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12 mt-2 mb-0">
                                            <button class="btn btn-primary">Thay đổi</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
