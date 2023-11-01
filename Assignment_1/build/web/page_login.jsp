<%-- 
    Document   : login
    Created on : Oct 14, 2023, 9:30:00 AM
    Author     : 84859
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Login</title>
        <style>
            .login {
                text-align: center;
                background-color: #f2f2f2;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .login form {
                display: inline-block;
                text-align: left;
            }

            .login label {
                display: block;
                font-weight: bold;
            }

            .login input[type="text"],
            .login input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .login span {
                color: red;
            }

            .login .signin {
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                margin: 10px;
                cursor: pointer;
            }
            .login .register {
                background-color: green;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                margin: 10px;
                cursor: pointer;
            }
        </style>
    </head>
    <body> 
        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.user == null and sessionScope.staff == null}">
            <div class="login">
                <form action="DispatcherServlet" method="post">
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Email" />
                        <jsp:param name="field" value="email" />
                        <jsp:param name="type" value="text" />
                    </jsp:include>
                    <jsp:include page="./components/inputfield.jsp">
                        <jsp:param name="label" value="Password" />
                        <jsp:param name="field" value="password" />
                        <jsp:param name="type" value="password" />
                    </jsp:include>
                    <!--                    <label for="email">Email</label>
                                        <input id="email" type="text" name="txtemail"><br/>
                                        <label for="pass">Password</label>
                                        <input id="pass" type="text" name="txtpass"><br/>-->
                    <span>${requestScope.msg}</span><br/>
                    <button class="signin" type="submit" name="action" value="login">Sign In</button>
                    <button class="register" type="submit" name="action" value="registerpage">Register</button>

                </form>
            </div>

            <div class="google-sign-up">

            </div>

        </c:if>

        <%@include file="./components/footer.jsp" %>

    </body>
</html>
