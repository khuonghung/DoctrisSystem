<%-- 
    Document   : recover
    Created on : Jan 12, 2022, 7:28:38 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
                                <h4 class="text-center">Khôi phục mật khẩu</h4>  
                                <p style="color: red; align-content: center;">
                                        ${requestScope.error}
                                </p>
                                <p style="color: blue; align-content: center;">
                                        ${requestScope.success}
                                </p>
                                <form action="user?action=checkemail" method="POST" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled=true;">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p class="text-muted">Hãy nhập email của bạn chúng tôi sẽ gửi mật khẩu mới vào email.</p>
                                            <div class="mb-3">
                                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                                <input type="email" class="form-control" name="email" required="">
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="d-grid">
                                                <button id="submit" class="btn btn-primary">Xác nhận</button>
                                            </div>
                                        </div>
                                        <div class="mx-auto">
                                            <p class="mb-0 mt-3"><a href="user?action=login" class="text-dark h6 mb-0">Đăng nhập</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="../assets/js/bootstrap.bundle.min.js"></script>
        <script src="../assets/js/feather.min.js"></script>
        <script src="../assets/js/app.js"></script>
    </body>

</html>
