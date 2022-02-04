<%-- 
    Document   : login
    Created on : Jan 12, 2022, 7:11:43 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>

    <body>
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Đăng nhập</h4> 
                                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                                <form action="user?action=checklogin" method="POST" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled=true;">
                                    <p style="color: red; align-content: center;">
                                        ${requestScope.error}
                                    </p>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                                <input type="email" value="${cookie.email.value}" class="form-control" placeholder="Email" name="email" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                                <input type="password" value="${cookie.pass.value}" class="form-control" name="password" placeholder="Password" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="d-flex justify-content-between">
                                                <div class="mb-3">
                                                    <div class="form-check">
                                                        <input ${(cookie.rem.value eq 'ON')?"checked":""} class="form-check-input align-middle" type="checkbox" name="remember" id="remember-check">
                                                        <label class="form-check-label" for="remember-check">Lưu tài khoản</label>
                                                    </div>
                                                </div>
                                                <a href="user?action=recover" class="text-dark h6 mb-0">Quên mật khẩu ?</a>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <button class="btn btn-primary" id="submit">Đăng nhập</button>
                                            </div>
                                        </div>
                                        <div class="col-12 text-center">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Chưa có tài khoản ?</small> <a href="user?action=register" class="text-dark fw-bold">Đăng ký</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </section>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

    </body>

</html>