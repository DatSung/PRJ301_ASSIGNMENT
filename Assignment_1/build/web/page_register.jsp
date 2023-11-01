<%-- 
    Document   : page_register
    Created on : Oct 19, 2023, 8:02:52 AM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cd" class="dao.UserDAO" scope="request"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Register</title>
            <style>
                .register {
                    text-align: center;
                    background-color: #f2f2f2;
                    padding: 20px;
                    border-radius: 10px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                }

                .register form {
                    display: inline-block;
                    text-align: left;
                }

                .register label {
                    display: block;
                    font-weight: bold;
                }

                .register input[type="text"],
                .register input[type="password"] {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                .register input[type="hidden"] {
                    display: none; /* Hide hidden input elements */
                }

                .register p {
                    color: red;
                }

                .register button {
                    background-color: green;
                    color: #fff;
                    padding: 10px 20px;
                    border: none;
                    border-radius: 5px;
                    margin: 10px;
                    cursor: pointer;
                }

                .register a {
                    text-decoration: none;
                    color: #007bff;
                }

            </style>
        </head>
        <body>
        <%@include file="./components/nav.jsp" %>
        <%--<c:if test="${sessionScope.user == null}">--%>
        
            <div class="register">
                <form action="DispatcherServlet" method="post">
                    <!--value sẽ dc xử lý sau, tăng dần và mã hóa thành String -->
                    <input type="hidden" name="id" value="${cd.getUserByPurpose("email").size() + 1}"/>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Name" />
                        <jsp:param name="field" value="name" />
                        <jsp:param name="type" value="text" />
                    </jsp:include>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Email" />
                        <jsp:param name="field" value="email" />
                        <jsp:param name="type" value="text" />
                    </jsp:include>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Address" />
                        <jsp:param name="field" value="address" />
                        <jsp:param name="type" value="text" />
                    </jsp:include>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Password" />
                        <jsp:param name="field" value="check" />
                        <jsp:param name="type" value="password" />
                    </jsp:include>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Password Confirm" />
                        <jsp:param name="field" value="recheck" />
                        <jsp:param name="type" value="password" />
                    </jsp:include>
                    <input  type="hidden" name="status" value="N"><br/>
                    <p>${requestScope.msg}</p>
                    <button type="submit" name="action" value="register">Register</button>
                    <a href="page_login.jsp">Back<a/>
                </form>
            </div>
            <%--</c:if>--%>
       
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
