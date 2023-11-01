<%-- 
    Document   : service
    Created on : Oct 8, 2023, 10:40:42 PM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="service" class="dao.ServiceDAO" scope="request"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Service</title>
            <style>
                /* CSS cho tiêu đề "Internet Service" */
                h2 {
                    text-align: center;
                    font-size: 24px;
                    margin-bottom: 20px;
                }

                /* CSS cho lưới và thẻ "col-md-3" */
                .row {
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: space-around;
                }

                .col-md-3 {
                    width: 23%;
                    min-width: 200px;
                    margin: 10px;
                    padding: 10px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    text-align: center;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    background-color: #fff;
                }

                /* CSS cho các phần tử bên trong thẻ "col-md-3" */
                h2 {
                    font-size: 18px;
                }

                p {
                    font-size: 14px;
                }

                h4 {
                    font-size: 16px;
                }

                .register-button {
                    text-decoration: none;
                    background-color: #007bff;
                    color: #fff;
                    padding: 5px 10px;
                    border-radius: 5px;
                    display: inline-block;
                }
            </style>
        </head>
        <body>
        <%@include file="./components/nav.jsp" %>
        <h2> Internet Service </h2>
        <div class="row">


            <c:forEach items="${service.serviceList()}" var="s">
                <c:if test="${s.workingstatus == true}">
                    <div class="col-md-3">
                        <h2>${s.sname}</h2>
                        <p>${s.des}</p>
                        <h4>${s.costpermonth}</h4>
                        <a class="register-button" href="DispatcherServlet?serviceNo=${s.getId()}&action=registerservicepage">Register Now !!!</a><br/>
                    </div>
                </c:if>
            </c:forEach>

            <%@include file="./components/footer.jsp" %>
        </div>  
    </body>
</html>
