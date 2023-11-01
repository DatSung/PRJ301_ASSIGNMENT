<%-- 
    Document   : welcome
    Created on : Oct 8, 2023, 10:01:04 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style>
            * {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            /* Style for the navigation bar */
            .nav {
                background-color: whitesmoke; /* Set your desired background color */
                padding: 10px;
                width: 100%;
            }

            nav {
                margin: 0 auto;
                width: 95%;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .nav-logo img {
                max-width: 100px; /* Set the maximum width for your logo */
            }

            .nav-list {
                display: flex;
            }

            .nav-list a {
                color: rgb(89, 131, 216);
                border-radius: 10px;
                text-decoration: none;
                padding: 10px 15px;
                transition: 0.5s;
            }

            .nav-list a:hover {
                transition: 0.5s;
                background-color: rgb(89, 131, 216); /* Change the background color on hover (you can adjust this) */
                color: #fff; /* Change the text color on hover */
            }

            /* Optionally, add a border-bottom line under the active link */
            .nav-list a.active {
                border-bottom: 2px solid #ff6600;
            }

            .nav-list a {
                color: rgb(89, 131, 216); /* Set your desired text color for links */
                text-decoration: none;
                margin: 0 10px;
            }

            .nav-user_info {
                display: flex;
                align-items: center;
            }

            .nav-user_info span {
                padding: 10px;
                color: black;
            }


            .nav-user_info a {
                color: rgb(89, 131, 216);
                border-radius: 10px;
                text-decoration: none;
                padding: 10px 15px;
                transition: 0.5s;
            }

            .nav-user_info a:hover {
                transition: 0.5s;
                background-color: rgb(89, 131, 216); 
                color: #fff; 
            }


        </style>
    </head>
    <body>
        <div class="nav">
            <nav>

                <div class="nav-logo">
                    <a href="welcome.jsp">
                        <img src="./asset/img/logo_fpte.png" alt=""/>
                    </a>
                </div>

                <c:if test="${sessionScope.staff == null}">
                    <div class="nav-list">
                        <a href="DispatcherServlet?action=welcomepage">Home</a>
                        <!--trong service có các gói dịch vụ mạng-->
                        <a href="DispatcherServlet?action=servicespage">Service</a>
                        <!--trong contract thông tin gói đã đăng ký, lịch sử thanh toán -->
                        <a href="DispatcherServlet?action=contractpage&urlrewrite=true">Contract</a>
                        <!--xem thông tin cá nhân-->
                        <a href="DispatcherServlet?action=settingpage">Setting</a>
                        <!--gửi yêu cầu cũng như coi lịch sử yêu cầu-->
                        <a href="DispatcherServlet?action=supportpage">Support</a>

                        <c:if test="${sessionScope.user == null}">
                            <a href="DispatcherServlet?action="><b>Sign In</b></a>
                            <a href="DispatcherServlet?action=registerpage"><b>Sign Up</b></a>
                        </c:if>
                    </div>
                </c:if>

                <c:if test="${sessionScope.staff != null}">
                    <div class="nav-list">
                        <c:choose>
                            <c:when test="${sessionScope.staff.srole == 'AD'}">
                                <!--Quan li cac service cua trang-->
                                <a href="AdminDispatcherServlet?action=adpageservice">Service</a>

                                <!--Quan li cac request tu nguoi dung-->
                                <a href="AdminDispatcherServlet?action=adpagerequest">Request</a>

                                <!--Quan li nhan vien-->
                                <a href="AdminDispatcherServlet?action=adpagestaff">Staff</a>

                                <!--Quan li khach hang-->
                                <a href="AdminDispatcherServlet?action=adpagecustomer">Customer</a>

                                <!--Cai dat tai khoan-->
                                <a href="AdminDispatcherServlet?action=adpagesetting">Setting</a>
                            </c:when>

                            <c:when test="${sessionScope.staff.srole == 'TE'}">

                                <a href="#">TE</a>
                                <!--Cai dat tai khoan-->
                                <a href="AdminDispatcherServlet?action=adpagesetting">Setting</a>
                            </c:when>

                            <c:when test="${sessionScope.staff.srole == 'ST'}">

                                <a href="#">ST</a>
                                <!--Cai dat tai khoan-->
                                <a href="AdminDispatcherServlet?action=adpagesetting">Setting</a>
                            </c:when>
                        </c:choose>
                    </div>
                </c:if>


                <c:if test="${sessionScope.user != null}">
                    <div class="nav-user_info">
                        <span>${sessionScope.user.cname}</span>
                        <a href="DispatcherServlet?action=logout">Log out</a>
                    </div>
                </c:if>



                <c:if test="${sessionScope.staff != null}">
                    <div class="nav-user_info">
                        <span>${sessionScope.staff.name}</span>
                        <a href="DispatcherServlet?action=logout">Log out</a>
                    </div>
                </c:if>

            </nav>

        </div>
    </body>
</html>
