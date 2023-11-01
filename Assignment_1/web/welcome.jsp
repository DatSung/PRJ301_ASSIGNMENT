<%-- 
    Document   : welcome
    Created on : Oct 9, 2023, 10:01:09 AM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Telecom</title>
        <link href="asset/css/welcome.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
    </head>
    <body>

        <%@include file="./components/nav.jsp" %>

        <div class="slideshow-container">

            <div class="mySlides fade">
                <img src="asset/img/banner1.jpg" style="width:100%">
                <div class="text"></div>
            </div>

            <div class="mySlides fade">
                <img src="asset/img/banner2.jpg" style="width:100%">
                <div class="text"></div>
            </div>

            <div class="mySlides fade">
                <img src="asset/img/banner3.jpg" style="width:100%">
                <div class="text"></div>
            </div>

        </div>
        
        <div style="display: block; text-align:center">
            <span class="dot"></span> 
            <span class="dot"></span> 
            <span class="dot"></span> 
        </div>
        <script src="asset/js/script.js" type="text/javascript"></script>


        <%@include file="./components/footer.jsp" %>
    </body>
</html>
