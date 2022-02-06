<%-- 
    Document   : headmenu
    Created on : Jan 14, 2022, 3:13:04 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="top-header">
    <div class="header-bar d-flex justify-content-between border-bottom">
        <div class="d-flex align-items-center">
            <a href="home" class="logo-icon">
                <img src="assets/images/logo-icon.png" height="30" class="small" alt="">
                <span class="big">
                    <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                    <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                </span>
            </a>
            <a id="close-sidebar" class="btn btn-icon btn-pills btn-soft-primary ms-2" href="#">
                <i class="uil uil-bars"></i>
            </a>
            <div class="search-bar p-0 d-none d-lg-block ms-2">
                <div id="search" class="menu-search mb-0">
                    <form role="search" method="get" id="searchform" class="searchform">
                        <div>
                            <input type="text" class="form-control border rounded-pill" name="s" id="s" placeholder="Search Keywords...">
                            <input type="submit" id="searchsubmit" value="Search">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <ul class="list-unstyled mb-0">
            <li class="list-inline-item mb-0 ms-1">
                <div class="dropdown dropdown-primary">
                    <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="./${sessionScope.user.img}" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                    <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                        <a class="dropdown-item d-flex align-items-center text-dark" href="user?action=profile">
                            <img src="./${sessionScope.user.img}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                            <div class="flex-1 ms-2">
                                <span class="d-block mb-1">${sessionScope.user.name}</span>
                                <small class="text-muted">${sessionScope.user.username}</small>
                            </div>
                        </a>
                        <div class="dropdown-divider border-top"></div>
                        <a class="dropdown-item text-dark" href="user?action=logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>