<%-- 
    Document   : dashboard
    Created on : Mar 1, 2022, 10:29:15 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <jsp:include page="../layout/preloader.jsp"/>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>      

            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Dashboard</h5>
                        <div class="row">
                            <div class="col-xl-6 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-bed h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${patient}</h5>
                                            <p class="text-muted mb-0">Bệnh nhân</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-6 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-social-distancing h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${doctor}</h5>
                                            <p class="text-muted mb-0">Bác sĩ</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-file-medical-alt h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0"><fmt:formatNumber pattern="#,###,###,###" value="${Revenue}"/> đ</h5>
                                            <p class="text-muted mb-0">Doanh thu</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-medkit h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${reservation}</h5>
                                            <p class="text-muted mb-0">Reservations</p>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-medkit h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${appointment}</h5>
                                            <p class="text-muted mb-0">Appointments</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xl-8 col-lg-7 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Thống kê số lịch hẹn</h6>
                                        <div class="mb-0 position-relative">
                                            <select onchange="Astatistic(this.value)" class="form-select form-control" id="yearchart">
                                                <option <c:if test="${sessionScope.atype == '3day'}"> selected </c:if> value="3day">3 ngày gần đây</option>
                                                <option <c:if test="${sessionScope.atype == '7day'}"> selected </c:if> value="7day">7 Ngày gần đây</option>
                                                <option <c:if test="${sessionScope.atype == '14day'}"> selected </c:if> value="14day">14 ngày gần đây</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="dashboard" class="apex-chart"></div>
                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-5 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Doanh thu</h6>
                                        <div class="mb-0 position-relative">
                                            <select onchange="Rstatistic(this.value)" class="form-select form-control" id="dailychart">
                                                <option <c:if test="${sessionScope.rtype == 'today'}"> selected </c:if> value="today" >Hôm nay</option>
                                                <option <c:if test="${sessionScope.rtype == '7day'}"> selected </c:if> value="7day">7 ngày gần đây</option>
                                                <option <c:if test="${sessionScope.rtype == '14day'}"> selected </c:if> value="14day">14 ngày gần đây</option>
                                                <option <c:if test="${sessionScope.rtype == 'month'}"> selected </c:if> value="month">Tháng này</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="department" class="apex-chart"></div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xl-6 col-lg-6 mt-4">
                                <div class="card border-0 shadow rounded">
                                    <div class="d-flex justify-content-between align-items-center p-4 border-bottom">
                                        <h6 class="mb-0"><i class="uil uil-calender text-primary me-1 h5"></i>Danh sách lịch hẹn dịch vụ hôm nay</h6>
                                    </div>

                                    <table class="list-unstyled mb-0 p-4">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" >Bệnh nhân</th>
                                                <th class="border-bottom p-3" >Dịch vụ</th>
                                                <th class="border-bottom p-3" >Thời gian</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${reservationlist}" var="r">
                                                <tr>
                                                    <td class="p-3">${r.patient.account.username}</td>
                                                    <td class="p-3">${r.service.title}</td>
                                                    <td class="p-3">${r.time}</td>
                                                    <td class="p-3">${r.status}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 mt-4">
                                <div class="card border-0 shadow rounded">
                                    <div class="d-flex justify-content-between align-items-center p-4 border-bottom">
                                        <h6 class="mb-0"><i class="uil uil-calender text-primary me-1 h5"></i>Danh sách lịch hẹn hôm nay</h6>
                                    </div>

                                    <table class="list-unstyled mb-0 p-4">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" >Bệnh nhân</th>
                                                <th class="border-bottom p-3" >Bác sĩ</th>
                                                <th class="border-bottom p-3" >Thời gian</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${appointmentlist}" var="a">
                                                <tr>
                                                    <td class="p-3">${a.patient.account.username}</td>
                                                    <td class="p-3">${a.doctor.doctor_name}</td>
                                                    <td class="p-3">${a.time}</td>
                                                    <td class="p-3">${a.status}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/simplebar.min.js"></script>
        <script src="assets/js/apexcharts.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
                                                var options1 = {
                                                    series: [{
                                                            name: 'Appointment',
                                                            data: [<c:forEach items="${appointment7day}" var="a">${a.count},</c:forEach>]
                                                        }, {
                                                            name: 'Reservation',
                                                            data: [<c:forEach items="${reservation7day}" var="r">${r.count},</c:forEach>]
                                                        }],
                                                    chart: {
                                                        type: 'bar',
                                                        height: 350,
                                                        stacked: true,
                                                        toolbar: {
                                                            show: true
                                                        },
                                                        zoom: {
                                                            enabled: true
                                                        }
                                                    },
                                                    responsive: [{
                                                            breakpoint: 480,
                                                            options: {
                                                                legend: {
                                                                    position: 'bottom',
                                                                    offsetX: -10,
                                                                    offsetY: 0
                                                                }
                                                            }
                                                        }],
                                                    plotOptions: {
                                                        bar: {
                                                            horizontal: false,
                                                            borderRadius: 10
                                                        },
                                                    },
                                                    xaxis: {
                                                        type: 'text',
                                                                categories: [<c:forEach items="${appointment7day}" var="a">'<fmt:formatDate pattern="dd/MM/yyyy" value="${a.date}"/>',</c:forEach>
                                                                ],
                                                    },
                                                    legend: {
                                                        position: 'right',
                                                        offsetY: 40
                                                    },
                                                    fill: {
                                                        opacity: 1
                                                    }
                                                };
                                                var chart1 = new ApexCharts(document.querySelector("#dashboard"), options1);
                                                chart1.render();
            </script>

        <script type="text/javascript">
                    function Astatistic(type) {
                        window.location.href = "dashboard?action=statistic&atype=" + type + "&rtype=${sessionScope.rtype}";
                    }
        </script>
        
        <script type="text/javascript">
                    function Rstatistic(type) {
                        window.location.href = "dashboard?action=statistic&rtype=" + type + "&atype=${sessionScope.atype}";
                    }
        </script>

        <script>
            var options2 = {
                series: [${Revenueappointment}, ${Revenuereservation}],
                chart: {
                    width: 450,
                    type: 'pie',
                },

                labels: ['Appointment', 'Reservation'],
                responsive: [{
                        breakpoint: 600,
                        options: {
                            chart: {
                                width: 500
                            },
                            legend: {
                                position: 'bottom'
                            },
                        }
                    }]
            };
            var chart2 = new ApexCharts(document.querySelector("#department"), options2);
            chart2.render();
        </script>
    </body>

</html>