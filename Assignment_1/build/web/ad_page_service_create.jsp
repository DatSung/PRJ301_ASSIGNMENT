<%-- 
    Document   : ad_page_service_create
    Created on : Oct 28, 2023, 8:18:06 AM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Create New Service</title>
        <style>
            .form-container {

                background-color: #f2f2f2;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 50%;
                margin: 20px auto;
            }

            .form-heading {
                font-size: 24px;
                margin-bottom: 10px;
                text-align: center;
            }

            .form-label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
            }

            .form-input {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-select {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
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

            .error-message {
                margin-top: 10px;
            }

        </style>
    </head>
    <body>
        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.staff != null}">
            <form class="form-container" action="AdminDispatcherServlet">
                <h2 class="form-heading">Create New Service</h2>
                <label for="svname" class="form-label">Service Name:</label>
                <input type="text" required="" name="svname" class="form-input" placeholder="${requestScope.svname}">
                <br>
                <label for="svdes" class="form-label">Service Description:</label>
                <input type="text" required="" name="svdes" class="form-input" placeholder="${requestScope.svdes}">
                <br>
                <label for="svcost" class="form-label">Service Cost:</label>
                <input min="1" type="number" required="" name="svcost" class="form-input" placeholder="${requestScope.svcost}">
                <br>
                <label for="svstatus" class="form-label">Service Status:</label>
                <select name="svstatus" class="form-select">
                    <c:choose>
                        <c:when test="${requestScope.svstatus == 'true'}">
                            <option value="true" selected>Working</option>
                            <option value="false">Not Working</option>
                        </c:when>
                        <c:when test="${requestScope.svstatus == 'false'}">
                            <option value="true">Working</option>
                            <option value="false" selected>Not Working</option>
                        </c:when>
                        <c:otherwise>
                            <option value="true" selected>Working</option>
                            <option value="false" >Not Working</option>
                        </c:otherwise>
                    </c:choose>
                </select>
                <br>
                <p class="error-message">${requestScope.createserviceresult}</p>
                <button name="action" value="actioncreateviewservice" class="form-button">Create</button>
                <a href="ad_page_service.jsp" class="form-link">Back</a>

            </form>

        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
