<%-- 
    Document   : capcha
    Created on : Jan 13, 2022, 11:28:19 PM
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

        <section class="bg-home d-flex bg-light align-items-center" style="background: url('../assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Xác thực tài khoản</h4>  
                                <form action="user?action=checkcapcha" method="POST" class="login-form mt-4">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p class="text-muted">Chúng tôi đã gửi mã xác thực vào email bạn đã đăng ký.Hãy kiểm tra và nhập vào đây</p>
                                            <p style="color: red; align-content: center;">
                                                ${requestScope.error}
                                            </p>
                                            <div class="mb-3">
                                                <label class="form-label">Mã xác thực <span class="text-danger">*</span></label>
                                                <input type="number" class="form-control" name="capcha" required="">
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="d-grid">
                                                <button class="btn btn-primary">Xác nhận</button>
                                            </div>
                                        </div>
                                        <div class="mx-auto">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Vẫn chưa nhận được mã ?</small> <a href="user?action=generalcapcha" class="text-dark h6 mb-0">Nhấn vào đây</a></p>
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
