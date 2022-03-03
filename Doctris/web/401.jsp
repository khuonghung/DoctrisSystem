<%-- 
    Document   : 401
    Created on : Mar 3, 2022, 2:42:18 PM
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
        <section class="bg-home d-flex bg-light align-items-center" style="background: url('assets/images/bg/bg-lines-one.png') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7 col-md-10 text-center">
                        <img src="assets/images/svg/401.svg" class="img-fluid" alt="">
                        <h3 class="mb-4">Quyền truy cập bị chặn</h3>
                        <p class="text-muted para-desc mx-auto">Có vẻ như bạn không có quyền truy cập. Hãy liên hệ admin về vấn đề này!</p>
                        <a href="home" class="btn btn-primary mt-2">Quay lại trang chủ</a>
                    </div>
                </div>
            </div>
        </section>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        
    </body>

</html>
