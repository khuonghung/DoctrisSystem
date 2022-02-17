<%-- 
    Document   : register
    Created on : Jan 12, 2022, 7:21:48 PM
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

        <section class="bg-half-150 d-table w-100 bg-light">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Đăng ký</h4>  
                                <p style="color: red; align-content: center;">
                                    ${requestScope.error}
                                </p>
                                <form action="user?action=checkregister" method="POST" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled=true;">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Tên tài khoản <span class="text-danger">*</span></label>
                                                <input type="text" oninvalid="CheckUserName(this);" oninput="CheckUserName(this);" class="form-control" name="username" required="">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Họ Tên <span class="text-danger">*</span></label>
                                                <input type="text" oninvalid="CheckFullName(this);" oninput="CheckFullName(this);" class="form-control" name="name" required="">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                                                <div class="my-3">
                                                    <div class="form-check">
                                                        <input id="credit" name="gender" value="true" type="radio" class="form-check-input"
                                                               checked required>
                                                        <label class="form-check-label">Nam</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input id="debit" name="gender" value="false" type="radio" class="form-check-input"
                                                               required>
                                                        <label class="form-check-label">Nữ</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3">                                               
                                            <label class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                                            <input type="text" oninvalid="CheckPhone(this);" oninput="CheckPhone(this);" class="form-control" name="phone" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Email <span class="text-danger">*</span></label>
                                            <input type="email" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" class="form-control" name="email" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                            <input type="password" id="password" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" class="form-control" name="password" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Nhập lại mật khẩu <span class="text-danger">*</span></label>
                                            <input type="password" oninvalid="CheckRePassword(this);" oninput="CheckRePassword(this);" class="form-control" name="repassword" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="d-grid">
                                            <button class="btn btn-primary" id="submit">Đăng ký</button>
                                        </div>
                                    </div>
                                    <div class="mx-auto">
                                        <p class="mb-0 mt-3"><small class="text-dark me-2">Đã có tài khoản ?</small> <a href="user?action=login" class="text-dark fw-bold">Đăng nhập</a></p>
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
