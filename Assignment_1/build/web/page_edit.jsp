<%-- 
    Document   : page_edit
    Created on : Oct 16, 2023, 10:16:30 AM
    Author     : 84859
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Edit Account</title>
        <script src="script.js"></script>
        <style>
            .form-container {
                text-align: center;
                background-color: #f2f2f2;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 50%;
                margin: 20px auto;
            }

            .form-label {
                font-weight: bold;
                display: block;
                margin-top: 10px;
            }

            .form-input {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-p {
                color: red;
                margin: 10px 0;
            }

            .form-button {
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                margin: 10px;
                cursor: pointer;
            }

            .form-link {
                text-decoration: none;
                color: #007bff;
            }

        </style>
    </head>
    <body>
        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.user != null}">
            <c:set var="name" value="${param.target}"></c:set>

                <div class="form-container">
                    <form action="DispatcherServlet">
                    <c:choose>
                        <c:when test="${name == 'cname'}">
                            <c:set var="label" value="Name"></c:set>
                                <label for="old">Old Name</label>
                                <input class="form-input"  id="old" type="text" value="${sessionScope.user.cname}" readonly>
                        </c:when>
                        <c:when test="${name == 'password'}">
                            <c:set var="label" value="Password"></c:set>
                                <label for="old">Old Password</label>
                                <input class="form-input"  id="old" type="text" value="${sessionScope.userpass}" readonly>
                        </c:when>
                        <c:otherwise>
                            <c:set var="label" value="Address"></c:set>
                                <label for="old">Old Address</label>
                                <input class="form-input" id="old" type="text" value="${sessionScope.user.address}" readonly>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="target" value="${name}">
                    <label for="new" class="form-label">New ${label}</label>
                    <input id="new" type="text" name="check" required class="form-input"><br/>
                    <label for="confirm" class="form-label">${label} Confirm</label>
                    <input id="confirm" type="text" name="recheck" required class="form-input"><br/>
                    <p class="form-p">${requestScope.msg}</p>
                    <button type="submit" name="action" value="edit" class="form-button">Update</button>
                    <a href="DispatcherServlet?action=settingpage" class="form-link">Back</a>
                </form>
            </div>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
