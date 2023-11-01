<%-- 
    Document   : page_support
    Created on : Oct 15, 2023, 12:50:16 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="rtd" class="dao.RequestDAO" scope="request"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Support</title>
            <style>
                form {
                    background-color: #f5f5f5;
                    border: 1px solid #ccc;
                    padding: 20px;
                    margin: 20px;
                    text-align: left;
                    max-width: 400px;
                    margin: 0 auto;
                }

                label {
                    font-weight: bold;
                    display: block;
                    margin: 10px 0;
                }

                select {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                button[type="submit"] {
                    padding: 10px 20px;
                    background-color: #007bff;
                    color: #fff;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    margin: 10px 10px 10px 0;
                }

                button[type="submit"]:hover {
                    background-color: #0056b3;
                }

                input[type="hidden"] {
                    display: none;
                }

                div#components-container {
                    background-color: #f5f5f5;
                    border: 1px solid #ccc;
                    padding: 10px;
                    margin-top: 10px;
                    border-radius: 5px;
                }

                textarea {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                button[type="submit"][value="support"] {
                    background-color: #007bff;
                }

                button[type="submit"][value="support"]:hover {
                    background-color: #0056b3;
                }

                button[type="submit"][value=""] {
                    background-color: #ccc;
                    color: #000;
                }

                button[type="submit"][value=""]:hover {
                    background-color: #999;
                }

            </style>
        </head>
        <body>
        <%@include file="./components/nav.jsp" %>
        <form action="DispatcherServlet">
            <label for="options">Bạn cần hỗ trợ : </label>
            <select name="rtid" id="options">
                <c:forEach items="${rtd.getRequestTypeList()}" var="rt">
                    <option value="${rt.getRtid()}">${rt.getRtname()}</option>
                </c:forEach>
            </select>
            <jsp:include page="./components/inputfield.jsp">
                <jsp:param name="label" value="Description"></jsp:param>
                <jsp:param name="type" value="textarea"></jsp:param>
                <jsp:param name="field" value="description"></jsp:param>
            </jsp:include>

            <input type="hidden" name="rstatus" value="0">
            <button type="submit" name="action" value="support">Confirm</button>
            <button type="submit" name="action" value="">Back</button>
        </form>
        <%@include file="./components/request_history.jsp" %>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
