<%-- 
    Document   : page_setting
    Created on : Oct 15, 2023, 12:22:30 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Setting</title>
        <style>
            .user-setting-container {
                margin: 0 auto;
                width: 80%;
                padding: 80px 0;
            }
            .account-heading {
                text-align: center;
                font-size: 24px;
                margin-bottom: 40px;
            }

            .account-table {
                width: 100%;
                border-collapse: collapse;
            }

            .account-table td {
                padding: 8px;
                border: 1px solid #ccc;
            }

            .account-table a {
                text-decoration: none;
                color: #007bff;
            }

            .account-status-none {
                color: red;
            }

            .account-status-premium {
                color: green;
            }

            .account-status-gold {
                color: goldenrod;
            }

            .account-status-diamond {
                color: blue;
            }

        </style>
    </head>
    <body>
        <%@include file="./components/nav.jsp" %>
        <div class="user-setting-container">
            <h1 class="account-heading">Account Setting</h1>
            <table class="account-table">
                <tr>
                    <td>Customer Id</td>
                    <td>${sessionScope.user.cid}</td>

                </tr>
                <tr>
                    <td>Email</td>
                    <td>${sessionScope.user.email}</td>
                </tr>
                <tr>
                    <td>Customer Name</td>
                    <td>${sessionScope.user.cname}</td>
                    <td><a href="DispatcherServlet?action=editpage&target=cname">Edit</a></td>
                </tr>
                <tr>
                    <td>Pasword</td>
                    <td>${sessionScope.userpass}</td>
                    <td><a href="DispatcherServlet?action=editpage&target=password">Edit</a></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${sessionScope.user.address}</td>
                    <td><a href="DispatcherServlet?action=editpage&target=address">Edit</a></td>
                </tr>
                <tr>
                    <td>Account Premium</td>
                    <td>
                        <c:catch>
                            <c:when test="${sessionScope.user.cstatus == 'N'}">
                                NONE
                            </c:when>
                            <c:when test="${sessionScope.user.cstatus == 'P'}">
                                PREMIUM
                            </c:when>
                            <c:when test="${sessionScope.user.cstatus == 'G'}">
                                GOLD
                            </c:when>
                            <c:when test="${sessionScope.user.cstatus == 'D'}">
                                DIAMOND
                            </c:when>
                        </c:catch>

                    </td>
                </tr>
            </table>
        </div>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
