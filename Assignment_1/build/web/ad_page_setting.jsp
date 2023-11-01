<%-- 
    Document   : ad_page_setting
    Created on : Oct 24, 2023, 8:24:41 PM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
        <title>Setting Account</title>
        <style>
            /* Style the container for staff settings */
            .staff-setting-container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                font-family: Arial, sans-serif;
            }

            /* Style the content within the staff settings */
            .staff-setting-content {
                background-color: #f9f9f9;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
            }

            /* Style the success/error messages */
            .staff-setting-content p {
                color: #ff0000; /* Change to your desired color */
                font-weight: bold;
                margin: 10px 0;
            }

            /* Style the heading for Staff Management */
            .staff-setting-content h2 {
                font-size: 24px;
                font-weight: bold;
            }

            /* Style the form */
            .staff-setting-content form {
                margin-top: 20px;
            }

            /* Style labels for form inputs */
            .staff-setting-content label {
                display: block;
                font-weight: bold;
                margin: 10px 0;
            }

            /* Style form input fields */
            .staff-setting-content input[type="text"],
            .staff-setting-content input[type="email"] {
                width: 100%;
                padding: 5px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            /* Style the update button */
            .staff-setting-content .update-button {
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                margin-top: 20px;
            }

            .staff-setting-content .update-button:hover {
                background-color: #0056b3;
            }

        </style>
    </head>
    <body>
        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.staff != null}">
            <div class="staff-setting-container">
                <div class="staff-setting-content">
                    <p>${requestScope.removestaffresult}</p>
                    <p>${requestScope.updatestaffresult}</p>
                    <h2>Setting Account</h2>
                    <form action="AdminDispatcherServlet" method="post">
                        <input type="hidden" value="${sessionScope.staff.sid}" name="stid" />

                        <label for="stname">Name:</label>
                        <input name="stname" type="text" value="${sessionScope.staff.name}" />

                        <label for="stemail">Email:</label>
                        <input name="stemail" type="email" value="${sessionScope.staff.email}" />

                        <label for="stpass">Password:</label>
                        <input name="stpass" type="text" value="${sessionScope.staff.password}" />

                        <input name="strole" type="hidden" value="${sessionScope.staff.srole}" />

                        <button class="update-button" type="submit" name="action" value="viewsettingupdate" >Update</button>
                    </form>
                </div>
            </div>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
