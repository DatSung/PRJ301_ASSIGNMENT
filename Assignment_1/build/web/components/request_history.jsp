<%-- 
    Document   : request_history
    Created on : Oct 22, 2023, 9:05:30 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="rd" class="dao.RequestDAO" scope="request"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <style>
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin: 20px 0;
                }

                table, th, td {
                    border: 1px solid #ddd;
                }

                th, td {
                    padding: 10px;
                    text-align: left;
                }

                th {
                    background-color: #f5f5f5;
                    font-weight: bold;
                }

                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }

                a {
                    text-decoration: none;
                    color: #007bff;
                }

                a:hover {
                    text-decoration: underline;
                }

            </style>
        </head>
        <body>
            <h1>Request History</h1>
            <table>
                <tr>
                    <td>Request Type</td>
                    <td>Description</td>
                    <td>Request Date</td>
                    <td>Status</td>
                    <td>Customer</td>
                    <td>Processing Staff</td>
                </tr>
            <c:forEach items="${rd.getRequests(sessionScope.user.cid)}" var="r">
                <tr>
                    <td>${r.rtid.rtname}</td>
                    <td>${r.des}</td>
                    <td>${r.rdate}</td>
                    <td>${r.rstatus}</td>
                    <td>${r.cid.cname}</td>
                    <td>${r.sid.name}</td>
                    <td><a href="DispatcherServlet?action=deleterequest&rid=${r.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
