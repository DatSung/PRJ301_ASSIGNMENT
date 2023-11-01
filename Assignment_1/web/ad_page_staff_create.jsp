<%-- 
    Document   : ad_page_staff_create
    Created on : Oct 29, 2023, 11:55:16 PM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Create New Staff</title>
        <script type="text/javascript">
            function validatePassword() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;

                if (password !== confirmPassword) {
                    alert("Passwords do not match. Please try again.");
                    return false;
                }
                return true;
            }
        </script>
        <style>
            .form-container {
                width: 50%;
                margin: 20px auto;
                padding: 20px;
                background-color: #f0f0f0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 0 5px #999;
                text-align: center;
            }

            .form-heading {
                font-size: 24px;
                margin-bottom: 20px;
                text-align: center;
            }

            .form-label {
                display: block;
                margin: 10px 0;
                text-align: left;
            }

            .form-input {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .form-select {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .error-message {
                margin: 10px 0;
            }

            .form-button {
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            .form-link {
                display: block;
                text-align: center;
                text-decoration: none;
                margin-top: 10px;
            }

            .form-link:hover {
                text-decoration: underline;
            }

        </style>
    </head> 
    <body>
        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.staff != null}">
            <form class="form-container" action="AdminDispatcherServlet" onsubmit="return validatePassword();">
                <h2 class="form-heading">Create New Staff</h2>
                <label for="stname" class="form-label">Staff Name:</label>
                <input type="text" required="" name="stname" class="form-input" placeholder="${requestScope.stname}">
                <br>
                <label for="stemail" class="form-label">Service Email:</label>
                <input type="email" required="" name="stemail" class="form-input" placeholder="${requestScope.stemail}">
                <br>
                <label for="stpass" class="form-label">Service Password:</label>
                <input type="text" required="" name="stpass" class="form-input" placeholder="${requestScope.stpass}">
                <br>
                <label for="stpassconfirm" class="form-label">Service Password Confirm:</label>
                <input type="text" required="" name="stpassconfirm" class="form-input" placeholder="${requestScope.stpassconfirm}">
                <br>
                <label for="strole" class="form-label">Service Status:</label>
                <select name="strole" class="form-select">
                    <option value="TE" selected="">Technical Engineer</option>
                    <option value="ST">Supporter</option>
                    <c:choose>
                        <c:when test="${requestScope.strole eq 'TE'}">
                            <option value="TE" selected="">Technical Engineer</option>
                            <option value="ST">Supporter</option>
                        </c:when>
                        <c:when test="${requestScope.svstatus eq 'ST'}">
                            <option value="TE">Technical Engineer</option>
                            <option value="ST" selected="">Supporter</option>
                        </c:when>
                    </c:choose>
                </select>
                <br>
                <p class="error-message">${requestScope.createstaffresult}</p>
                <button name="action" value="actioncreateviewstaff" class="form-button">Create</button>
                <a href="ad_page_staff.jsp" class="form-link">Back</a>

            </form>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
