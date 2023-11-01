<%-- 
    Document   : ad_page_service
    Created on : Oct 24, 2023, 8:19:36 PM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="service" class="dao.ServiceDAO" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Service Management</title>
            <style>
                /* Style the container holding the service content */
                .service-container {
                    width: 100%;
                    margin: 0 auto;
                    padding: 20px;
                }

                /* Style the service list */
                .service-list {
                    border: 1px solid #ccc;
                    padding: 10px;
                }

                /* Style the service items */
                .service-item {
                    margin: 10px 0;
                }

                /* Style the input fields */
                input {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                form {
                    display: flex;
                    align-items: center;
                }

                @media screen and (max-width: 768px) {
                    form {
                        display: block;
                    }
                }

                label{
                    margin-left: 10px;
                }
                
                select {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                /* Style the buttons */
                button {
                    padding: 10px 20px;
                    margin: 5px;
                    border: none;
                    background-color: #007bff;
                    color: #fff;
                    border-radius: 5px;
                    cursor: pointer;
                }

                .remove-button {
                    background-color: red;
                }

                /* Hover effect for buttons */
                button:hover {
                    background-color: #0056b3;
                }

                /* Center the buttons */
                button[type="submit"] {
                    display: inline-block;
                }

                /* Style the anchor tags inside service-item */
                .service-item a {
                    text-decoration: none; /* Remove underline */
                    color: #007bff; /* Text color */
                    font-weight: bold; /* Make the text bold */
                }

                .service-item h2 {
                    text-align: center;
                }

                /* Hover effect for anchor tags */
                .service-item a:hover {
                    color: #0056b3; /* Change text color on hover */
                }

                /* Style labels inside service-item */
                .service-item label {
                    display: inline-block;
                    width: 150px; /* Adjust the width as needed */
                    font-weight: bold;
                    margin-right: 10px;
                }




            </style>
        </head>
        <body>

        <%@include file="./components/nav.jsp" %>
        <c:if test="${sessionScope.staff != null}">
            <div class="service-container">
                <div class="service-content">
                    <div class="service-list">

                        <div class="service-item">
                            <p>${requestScope.removeserviceresult}</p>
                            <p>${requestScope.updateserviceresult}</p>
                            <h2>Service Management</h2>
                            <a href="AdminDispatcherServlet?action=viewservicecreate"> + Create New Service</a>
                            <c:forEach items="${service.serviceList()}" var="s">


                                <form action="AdminDispatcherServlet" method="post">
                                    <input type="hidden" value="${s.id}" name="svid" />
                                    <label for="svname">Name:</label>
                                    <input name="svname" type="text" value="${s.sname}" />
                                    <label for="svdes">Description:</label>
                                    <input name="svdes" type="text" value="${s.des}" />
                                    <label for="svcost">Cost:</label>
                                    <input min="1" name="svcost" type="number" value="${s.costpermonth}"/>
                                    <label for="svstatus">Status:</label>
                                    <select name="svstatus">
                                        <option value="true" ${s.workingstatus ? "selected" : ""}>Working</option>
                                        <option value="false" ${s.workingstatus ? "" : "selected"}>Not Working</option>
                                    </select>
                                    <button type="submit" name="action" value="viewserviceupdate" >Update</button>
                                    <button  class="remove-button" type="submit" name="action" value="viewserviceremove">Remove</button>
                                </form>


                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
