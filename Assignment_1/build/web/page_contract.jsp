<%-- 
    Document   : viewcontract
    Created on : Oct 12, 2023, 10:55:19 PM
    Author     : 84859
--%>

<%@page import="dto.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cdv" class="dao.ContractDAO" scope="request" ></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Contracts</title>
            <style>
                h2 {
                    font-size: 24px;
                    margin: 20px 0;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }

                tr:hover {
                    background-color: #ddd;
                }

                th, td {
                    border: 1px solid #ddd;
                    padding: 8px;
                    text-align: left;
                }

                tr:first-child {
                    background-color: #007bff;
                    color: white;
                }

                b {
                    font-weight: bold;
                }

                p {
                    font-weight: bold;
                    margin: 10px 0;
                }

            </style>
        </head>
        <body>
            <!--
            1. in dòng thông báo nhờ vào param isContractAdd
            2. Contract mới sẽ được in đậm ở đây sau lần đầu tiên add
            -->
            <div>
            <%@include file="./components/nav.jsp" %>
            <h2>Your contracts</h2>
            <table>
                <p>${requestScope.msg}</p>
                <tr>
                    <td>Name</td>
                    <td>Pay Status</td>
                </tr>
                <c:forEach items="${cdv.getContractsByUserId(sessionScope.user.getCid())}" var="c">
                    <tr>
                        <td>${c.sid.sname}</td>
                        <td>
                            <c:choose>
                                <c:when test="${c.status == 1}">
                                    <b>Payed</b>
                                </c:when>
                                <c:when test="${c.status == 0}">
                                    <b>Not payed</b>
                                </c:when>
                                <c:when test="${c.status == -1}">
                                    <b>Cancled</b>
                                </c:when>
                                <c:otherwise>
                                    <b>Undefined</b>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%@include file="./components/footer.jsp" %>
        </div>
    </body>
</html>
