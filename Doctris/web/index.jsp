<%-- 
    Document   : index.jsp
    Created on : Jan 5, 2022, 5:51:49 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/preloader.jsp"/>

        <jsp:include page="layout/menu.jsp"/>
        
        <section class="bg-half-260 d-table w-100" style="background: url('assets/images/bg/banner.jpg') center;">
            <div class="bg-overlay bg-overlay-"></div>
            <div class="container">
                <div class="row mt-5 mt-lg-0">
                    <div class="col-12">
                        <div class="heading-title">
                            <img src="assets/images/logo-icon.png" height="50" alt="">
                            <h4 class="display-4 fw-bold text-white title- mt-3 mb-4">Nền Tảng <br> Hỗ Trợ Y Tế Trực Tuyến</h4>
                            <p class="para-desc text-white-50 mb-0">Nếu bạn hay người thân cần sự trợ giúp ngay lập tức, điều trị khẩn cấp trong một cuộc tư vấn đơn giản.</p>

                            <div class="mt-4 pt-2">
                                <a href="booking-appointment.html" class="btn btn-primary">Đặt lịch hẹn ngay tại đây ...</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-10">
                        <div class="features-absolute bg-white shadow rounded overflow-hidden card-group">
                            <div class="card border-0 bg-light p-4">
                                <i class="ri-heart-pulse-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Dịch vụ chức khỏe</h5>
                                <p class="text-muted mt-2">Chúng tôi đem đến cho bạn những dịch vụ chất lượng tốt nhất trên thị trường.</p>
                                <a href="departments.html" class="text-primary">Tìm hiểu ngay <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>

                            <div class="card border-0 p-4">
                                <i class="ri-dossier-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Đặt lịch trực tuyến</h5>
                                <p class="text-muted mt-2">Đội ngũ bác sĩ luôn sẵn sàng tư vấn và thăm khám dành riêng cho bạn.</p>
                                <br>
                                <a href="departments.html" class="text-primary">Tìm hiểu ngay <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>

                            <div class="card border-0 bg-light p-4">
                                <i class="ri-time-fill text-primary h2 mb-0"></i>
                                <h5 class="mt-1">Thời gian hoạt động</h5>
                                <ul class="list-unstyled mt-2">
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Thứ 2 - Thứ 6</p>
                                        <p class="text-primary mb-0">8.00 - 20.00</p>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Thứ 7</p>
                                        <p class="text-primary mb-0">8.00 - 18.00</p>
                                    </li>
                                    <li class="d-flex justify-content-between">
                                        <p class="text-muted mb-0">Chủ nhật</p>
                                        <p class="text-primary mb-0">8.00 - 14.00</p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container mt-100 mt-60">
                <div class="row align-items-center">
                    <div class="col-lg-5 col-md-6">
                        <div class="position-relative">
                            <img src="assets/images/about/about.jpg" class="img-fluid" alt="">
                            <div class="play-icon">
                                <a href="https://youtu.be/QIvIN8M91x4" data-bs-toggle="modal" data-bs-target="#watchvideomodal" class="play-btn video-play-icon">
                                    <i class="mdi mdi-play text-primary rounded-circle bg-white title-bg- shadow"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-7 col-md-6 mt-4 mt-lg-0 pt- pt-lg-0">
                        <div class="ms-lg-4">
                            <div class="section-title">
                                <h4 class="title mb-4">Vì sao nên chọn Doctris?</h4>
                                <p class="text-muted para-desc">Chúng tôi quy tụ đội ngũ chuyên gia, bác sĩ, dược sĩ và điều dưỡng có trình độ chuyên môn cao, tay nghề giỏi, tận tâm và chuyên nghiệp</p>
                                <p class="text-muted para-desc mb-0">Luôn đặt người bệnh làm trung tâm, Doctris cam kết đem đến dịch vụ chăm sóc sức khỏe tốt nhất cho khách hàng.</p>
                            </div>

                            <div class="mt-4">
                                <a href="aboutus.html" class="btn btn-primary">Tìm hiểu nhiều hơn.. <i class="ri-arrow-right-line align-middle"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center mb-4 pb-2">
                            <span class="badge badge-pill badge-soft-primary mb-3">Dịch vụ chăm sóc sức khỏe</span>
                            <h4 class="title mb-4">Những dịch vụ nổi bật của chúng tôi</h4>
                            <p class="text-muted mx-auto para-desc mb-0">Đội ngũ Doctris luôn đem đến cho bạn những dịch vụ chăm sóc sức khỏe hiện đại và tốt nhất trên thị trường với những bác sĩ chuyên sâu trong các lĩnh vực .</p>
                        </div>
                    </div> 
                </div>

                <div class="row align-items-center">
                    <!-- List ra top 10 service -->
                    <div class="col-xl-3 col-lg-3 col-md-6 mt-4 pt-2">
                        <div class="card team border-0 rounded shadow overflow-hidden">
                            <div class="team-img position-relative">
                                <img src="assets/images/doctors/01.jpg" class="img-fluid" alt="">
                            </div>
                            <div class="card-body content text-center">
                                <a href="doctor-team-one.html" class="title text- h5 d-block mb-0">Calvin Carlo</a>
                                <small class="text-muted speciality">Eye Care</small>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 mt-4 pt-2 text-center">
                        <a href="doctor-team-one.html" class="btn btn-primary">Tìm kiếm nhiều hơn</a>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center mb-4 pb-2">
                            <span class="badge badge-pill badge-soft-primary mb-3">Dịch vụ bác sĩ </span>
                            <h4 class="title mb-4">Đội ngũ chuyên gia</h4>
                            <p class="text-muted mx-auto para-desc mb-0">Doctris quy tụ đội ngũ chuyên gia, bác sĩ, dược sĩ và điều dưỡng được đào tạo bài bản đến chuyên sâu tại Việt nam và nhiều nước có nên y học phát triển như Mỹ, Anh, Pháp... 
                                Luôn lấy người bệnh là trung tâm, Vinmec cam kết mang lại dịch vụ chăm sóc sức khỏe toàn diện và chất lượng cao cho khách hàng.</p>
                        </div>
                    </div> 
                </div>

                <div class="row align-items-center">
                    <!-- List ra top 10 bác sĩ nổi bật bao gồm tên , thông tin xã hội , category phụ trách -->
                    <div class="col-xl-3 col-lg-3 col-md-6 mt-4 pt-2">
                        <div class="card team border-0 rounded shadow overflow-hidden">
                            <div class="team-img position-relative">
                                <img src="assets/images/doctors/01.jpg" class="img-fluid" alt="">
                                <ul class="list-unstyled team-social mb-0">
                                    <li><a href="#" class="btn btn-icon btn-pills btn-soft-primary"><i data-feather="facebook" class="icons"></i></a></li>
                                    <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-primary"><i data-feather="linkedin" class="icons"></i></a></li>
                                    <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-primary"><i data-feather="github" class="icons"></i></a></li>
                                    <li class="mt-2"><a href="#" class="btn btn-icon btn-pills btn-soft-primary"><i data-feather="twitter" class="icons"></i></a></li>
                                </ul>
                            </div>
                            <div class="card-body content text-center">
                                <a href="doctor-team-one.html" class="title text- h5 d-block mb-0">Calvin Carlo</a>
                                <small class="text-muted speciality">Eye Care</small>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 mt-4 pt-2 text-center">
                        <a href="doctor-team-one.html" class="btn btn-primary">Tìm kiếm nhiều hơn</a>
                    </div>
                </div>
            </div>
        </section>

        <section class="section pt-0">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 text-center">
                        <div class="video-solution-cta position-relative" style="z-index: 1;">
                            <div class="position-relative">
                                <img src="assets/images/bg/banner.jpg" class="img-fluid rounded-md shadow-lg" alt="">
                                <div class="play-icon">
                                    <a href="https://youtu.be/QIvIN8M91x4" data-bs-toggle="modal" data-bs-target="#watchvideomodal" class="play-btn video-play-icon">
                                        <i class="mdi mdi-play text-primary rounded-circle bg-white title-bg- shadow-lg"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="content">
                                <div class="row" id="counter">
                                    <div class="col-md-4 mt-4 pt-2">
                                        <div class="counter-box text-center">
                                            <h1 class="mt-3 text-white title-"><span class="counter-value" data-target="99">10</span>%</h1>
                                            <h5 class="counter-head text-white title- mb-1">Positive feedback</h5>
                                            <p class="text-white-50 mb-0">From Doctors</p>
                                        </div>
                                    </div>

                                    <div class="col-md-4 mt-4 pt-2">
                                        <div class="counter-box text-center">
                                            <h1 class="mt-3 text-white title-"><span class="counter-value" data-target="25">2</span>+</h1>
                                            <h5 class="counter-head text-white title- mb-1">Experienced Clinics</h5>
                                            <p class="text-white-50 mb-0">High Qualified</p>
                                        </div>
                                    </div>

                                    <div class="col-md-4 mt-4 pt-2">
                                        <div class="counter-box text-center">
                                            <h1 class="mt-3 text-white title-"><span class="counter-value" data-target="1251">95</span>+</h1>
                                            <h5 class="counter-head text-white title- mb-1">Questions & Answers</h5>
                                            <p class="text-white-50 mb-0">Your Questions</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="feature-posts-placeholder bg-primary"></div>
            </div>

        </section>

        <section class="section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center mb-4 pb-2">
                            <h4 class="title mb-4">Patients Says</h4>
                            <p class="text-muted mx-auto para-desc mb-0">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>
                        </div>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <!-- List top đánh giá dịch vụ -->
                    <div class="col-lg-8 mt-4 pt-2 text-center">
                        <div class="client-review-slider">
                            <div class="tiny-slide text-center">
                                <p class="text-muted h6 fw-normal fst-italic">" It seems that only fragments of the original text remain in the Lorem Ipsum texts used today. The most well-known dummy text is the 'Lorem Ipsum', which is said to have originated in the 16th century. "</p>
                                <img src="assets/images/client/01.jpg" class="img-fluid avatar avatar-small rounded-circle mx-auto shadow my-3" alt="">
                                <ul class="list-unstyled mb-0">
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                    <li class="list-inline-item"><i class="mdi mdi-star text-warning"></i></li>
                                </ul>
                                <h6 class="text-primary">- Thomas Israel <small class="text-muted">C.E.O</small></h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container mt-100 mt-60">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center mb-4 pb-2">
                            <span class="badge badge-pill badge-soft-primary mb-3">Read News</span>
                            <h4 class="title mb-4">Latest News & Blogs</h4>
                            <p class="text-muted mx-auto para-desc mb-0">Great doctor if you need your family member to get effective immediate assistance, emergency treatment or a simple consultation.</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <!-- List bài đăng mới nhất -->
                    <div class="col-lg-4 col-md-6 col-12 mt-4 pt-2">
                        <div class="card blog blog-primary border-0 shadow rounded overflow-hidden">
                            <img src="assets/images/blog/01.jpg" class="img-fluid" alt="">
                            <div class="card-body p-4">
                                <ul class="list-unstyled mb-2">
                                    <li class="list-inline-item text-muted small me-3"><i class="uil uil-calendar-alt text- h6 me-1"></i>20th November, 2020</li>
                                    <li class="list-inline-item text-muted small"><i class="uil uil-clock text- h6 me-1"></i>5 min read</li>
                                </ul>
                                <a href="blog-detail.html" class="text- title h5">You can easily connect to doctor and make a treatment</a>
                                <div class="post-meta d-flex justify-content-between mt-3">
                                    <ul class="list-unstyled mb-0">
                                        <li class="list-inline-item me-2 mb-0"><a href="#" class="text-muted like"><i class="mdi mdi-heart-outline me-1"></i>33</a></li>
                                        <li class="list-inline-item"><a href="#" class="text-muted comments"><i class="mdi mdi-comment-outline me-1"></i>08</a></li>
                                    </ul>
                                    <a href="blog-detail.html" class="link">Read More <i class="mdi mdi-chevron-right align-middle"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="layout/footer.jsp"/>

        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>

        <jsp:include page="layout/search.jsp"/>

        <div class="modal fade" id="watchvideomodal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content video-modal rounded overflow-hidden">
                    <div class="ratio ratio-16x9">
                        <iframe src="https://www.youtube.com/embed/QIvIN8M91x4" title="YouTube video" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/tiny-slider.js "></script>
        <script src="assets/js/tiny-slider-init.js "></script>
        <script src="assets/js/counter.init.js "></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>
