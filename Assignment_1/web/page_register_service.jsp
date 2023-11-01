<%-- 
    Document   : registerservice
    Created on : Oct 9, 2023, 9:31:18 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="sd" class="dao.ServiceDAO" scope="request"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Service Registration</title>
            <style>
                h1 {
                    text-align: center;
                    font-size: 24px;
                    margin-top: 20px;
                }

                form {
                    max-width: 400px;
                    margin: 0 auto;
                    padding: 20px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #f9f9f9;
                }

                input[type="text"],
                input[type="number"] {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 3px;
                }

                label {
                    display: block;
                    font-weight: bold;
                }

                p {
                    font-weight: bold;
                    margin: 10px 0;
                }

                button {
                    width: 100%;
                    padding: 10px;
                    background-color: #007bff;
                    color: #fff;
                    border: none;
                    border-radius: 3px;
                    cursor: pointer;
                    margin-top: 10px;
                }

                button:hover {
                    background-color: #0056b3;
                }

            </style>
        </head>
        <body>
        <%@include file="./components/nav.jsp" %>
        <h1>Internet Register Form</h1>

        <c:set var="service" value="${sd.getService(param.serviceNo)}"></c:set>
            <form action="DispatcherServlet">
                <input type="hidden" value="${param.serviceNo}" name="serviceNo"><br/>
            <input type="text" value="${service.sname}" readonly ><br/>
            <input type="text" value="${service.costpermonth}" readonly ><br/>
            <label for="time">Register duration</label><br/>
            <input type="number" name="duration" id="time"><br/>
            <p>${requestScope.msg}</p>
            <button type="submit" name="action" value="registerservice">Register</button>
            <button type="submit" name="action" value="logoutregisterservice">Back</button>
        </form>
        <%@include file="./components/footer.jsp" %>       
    </body>
</html>
