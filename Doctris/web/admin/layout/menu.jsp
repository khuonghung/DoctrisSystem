<%-- 
    Document   : menu
    Created on : Jan 14, 2022, 3:12:10 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="home">
                <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">
            <li><a href="#"><i class="uil uil-dashboard me-2 d-inline-block"></i>Bảng điều khiển</a></li>
            <li><a href="setting?action=all"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Setting</a></li>
            <li><a href="account?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Tài khoản</a></li>
            <li><a href="doctormanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bác sĩ</a></li>
            <li><a href="patientmanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bệnh nhân</a></li>
        </ul>
    </div>
    <ul class="sidebar-footer list-unstyled mb-0">
        <li class="list-inline-item mb-0 ms-1">
            <a href="#" class="btn btn-icon btn-pills btn-soft-primary">
                <i class="uil uil-comment icons"></i>
            </a>
        </li>
    </ul>
</nav>
