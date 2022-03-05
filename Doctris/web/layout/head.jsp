<%-- 
    Document   : head
    Created on : Jan 12, 2022, 7:23:05 PM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8" />
    <title>Doctris - Tìm bác sĩ chính xác - Đặt lịch khám dễ dàng</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="https://shreethemes.in/doctris/layouts/assets/images/favicon.ico">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
    <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/tiny-slider.css" />
    <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    <script src="assets/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <style>
        @media not all and (min-resolution:.001dpcm)
        { @supports (-webkit-appearance:none) {

              .safari_only { 

                  color:#0000FF; 
                  background-color:#CCCCCC; 

              }
          }}
    </style>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(document).ready(function () {
        <c:if test="${alert != null}">
            toastr.${alert}('${message}.', 'Thông báo', {timeOut: 5000})
        </c:if>
        });
    </script>
</head>
