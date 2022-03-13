<%-- 
    Document   : myfeedback
    Created on : Mar 3, 2022, 8:59:48 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <jsp:include page="layout/profileMenu.jsp"/>
                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0">
                        <h3 class="mb-0"></h3>
                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Lịch hẹn chi tiết</h5>
                            </div>
                            <div class="p-4">
                                <form action="doctor?action=updateappointmentstatus&id=${a.id}" method="POST" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Id cuộc hẹn</label>
                                                <input name="appointment_id" readonly value="${a.id}" type="text" class="form-control">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Tên bệnh nhân</label>
                                                <input readonly value="${a.patient.username}" id="email" type="email" class="form-control">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">ID bệnh nhân</label>
                                                <input  readonly value="${a.patient.patient_id}" id="email" type="email" class="form-control">
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Ngày đặt lịch</label>
                                                <input readonly value="${a.date}" type="text" class="form-control">
                                            </div>                                                                               
                                        </div>
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Giờ đặt lịch</label>
                                                <input readonly   value="${a.time}" type="text" class="form-control">
                                            </div>                                                                               
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Status</label>
                                                <input readonly   value="${a.status}" type="text" class="form-control">

                                            </div>                                                                        
                                        </div>
                                    </div>
                                    ${a.status.equals("Assigned")?"<input type=\"submit\" id=\"submit\" name=\"send\" class=\"btn btn-primary\" value=\"Hoàn thành lịch hẹn\">":""}

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>
