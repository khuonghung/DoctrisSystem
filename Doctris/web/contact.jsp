<%-- 
    Document   : contact
    Created on : Jan 16, 2022, 9:57:48 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu.jsp"/>
        <!-- Start Hero -->
        <section class="bg-half-170 d-table w-100" style="background: url('assets/images/bg/banner.jpg');">
            <div class="bg-overlay bg-overlay-dark"></div>
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4 text-white title-dark">Liên hệ</h3>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-light rounded mb-0 py-1 px-2">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Liên hệ</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="position-relative">
            <div class="shape overflow-hidden text-white">
                <svg viewBox="0 0 2880 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 48H1437.5H2880V0H2160C1442.5 52 720 0 720 0H0V48Z" fill="currentColor"></path>
                </svg>
            </div>
        </div>

        <section class="mt-100 mt-60">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="card features feature-primary text-center border-0">
                            <div class="icon text-center rounded-md mx-auto">
                                <i class="uil uil-phone h3 mb-0"></i>
                            </div>
                            <div class="card-body p-0 mt-3">
                                <h5>Đường dây nóng</h5>
                                <a href="tel:888195313" class="link">0888195313</a>
                            </div>
                        </div>
                    </div><!--end col-->

                    <div class="col-lg-4 col-md-6 mt-4 mt-sm-0 pt-2 pt-sm-0">
                        <div class="card features feature-primary text-center border-0">
                            <div class="icon text-center rounded-md mx-auto">
                                <i class="uil uil-envelope h3 mb-0"></i>
                            </div>
                            <div class="card-body p-0 mt-3">
                                <h5>Email</h5>
                                <a href="mailto:support@doctriscare.ml" class="link">support@doctriscare.ml</a>
                            </div>
                        </div>
                    </div><!--end col-->

                    <div class="col-lg-4 col-md-6 mt-4 mt-lg-0 pt-2 pt-lg-0">
                        <div class="card features feature-primary text-center border-0">
                            <div class="icon text-center rounded-md mx-auto">
                                <i class="uil uil-map-marker h3 mb-0"></i>
                            </div>
                            <div class="card-body p-0 mt-3">
                                <h5>Địa chỉ</h5>
                                <a href="https://goo.gl/maps/G8Dyw5XUAiFcHrss5" class="link">Lê Thánh Tông, Tp. Ninh Bình, Ninh Bình</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container mt-100 mt-60">
                <div class="row align-items-center">
                    <div class="col-lg-5 col-md-6">
                        <div class="me-lg-5">
                            <img src="assets/images/about/about.jpg" class="img-fluid" alt="">
                        </div>
                    </div>

                    <div class="col-lg-7 col-md-6 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <div class="custom-form rounded shadow p-4">
                            <h5 class="mb-4">Gửi thông tin liên hệ</h5>
                            <form method="post" name="myForm" onsubmit="return validateForm()">
                                <p id="error-msg"></p>
                                <div id="simple-msg"></div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Họ Tên <span class="text-danger">*</span></label>
                                            <input name="name" id="name" type="text" class="form-control border rounded">
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Email <span class="text-danger">*</span></label>
                                            <input name="email" id="email" type="email" class="form-control border rounded" >
                                        </div> 
                                    </div>

                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Tiêu đề</label>
                                            <input name="subject" id="subject" class="form-control border rounded" >
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Nội dung <span class="text-danger">*</span></label>
                                            <textarea name="comments" id="comments" rows="4" class="form-control border rounded"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="submit" id="submit" name="send" class="btn btn-primary">Gửi tin nhắn</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid mt-100 mt-60">
                <div class="row">
                    <div class="col-12 p-0">
                        <div class="card map border-0">
                            <div class="card-body p-0">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3743.2335317580655!2d105.95756971491974!3d20.249147286425842!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31367a04e713830d%3A0x730339427ffa7a30!2zTMOqIFRow6FuaCBUw7RuZywgVHAuIE5pbmggQsOsbmgsIE5pbmggQsOsbmg!5e0!3m2!1svi!2s!4v1642345356126!5m2!1svi!2s" style="border:0" allowfullscreen></iframe>
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
