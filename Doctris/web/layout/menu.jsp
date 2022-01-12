<%-- 
    Document   : menu
    Created on : Jan 6, 2022, 1:14:27 AM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <header id="topnav" class="navigation sticky">
        <div class="container">
            
            <div>
                <a class="logo" href="index.html">
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
                        <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/doctors/01.jpg" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                        <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                            <a class="dropdown-item d-flex align-items-center text-" href="doctor-profile.html">
                                <img src="assets/images/doctors/01.jpg" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                <div class="flex-1 ms-2">
                                    <span class="d-block mb-1">Calvin Carlo</span>
                                    <small class="text-muted">Orthopedic</small>
                                </div>
                            </a>
                            <a class="dropdown-item text-" href="doctor-dashboard.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-dashboard align-middle h6"></i></span> Dashboard</a>
                            <a class="dropdown-item text-" href="doctor-profile-setting.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Profile Settings</a>
                            <div class="dropdown-divider border-top"></div>
                            <a class="dropdown-item text-" href="login.html"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                        </div>
                    </div>
                </li>
                
            </ul>

            <div id="navigation">
                
                <ul class="navigation-menu nav-left nav-light">
                    <li><a href="index.html" class="sub-menu-item">Trang chủ</a></li>
                    <li><a href="index.html" class="sub-menu-item">Bác sĩ</a></li>
                    <li><a href="index.html" class="sub-menu-item">Dịch vụ</a></li>
                    <li><a href="index.html" class="sub-menu-item">Chúng tôi</a></li>
                    <li><a href="index.html" class="sub-menu-item">Liên hệ</a></li>
                    <li><a href="index.html" class="sub-menu-item">Tin tức & chủ đề</a></li>
                </ul>
                
            </div>
        </div>
    </header>
</html>
