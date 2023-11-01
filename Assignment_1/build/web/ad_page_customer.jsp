<%-- 
    Document   : ad_page_customer
    Created on : Oct 23, 2023, 10:44:57 AM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="user" class="dao.UserDAO" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Customer Management</title>

            <style>
                /* Style the container holding the service content */
                .user-container {
                    width: 100%;
                    margin: 0 auto;
                    padding: 20px;
                }

                /* Style the service list */
                .user-list {
                    width: 100%;
                    border: 1px solid #ccc;
                    padding: 10px;
                }

                label{
                    margin-left: 10px;
                }
                /* Style the service items */
                .user-item {
                    width: 100%;
                    margin: 10px 0;
                }
                form {
                    width: 100%;
                    display: flex;
                    align-items: center;
                }

                @media screen and (max-width: 768px) {
                    form {
                        display: block;
                    }
                }

                /* Style the input fields */
                input {
                    width: 100%;
                    padding: 10px;
                    margin: 5px 0;
                    border: 1px solid #ccc;
                    border-radius: 5px;
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
                .user-item a {
                    text-decoration: none; /* Remove underline */
                    color: #007bff; /* Text color */
                    font-weight: bold; /* Make the text bold */
                }

                .user-item h2 {
                    text-align: center;
                }

                /* Hover effect for anchor tags */
                .user-item a:hover {
                    color: #0056b3; /* Change text color on hover */
                }

                /* Style labels inside service-item */
                .user-item label {
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
            <div class="user-container">
                <div class="user-content">
                    <div class="user-list">
                        <div class="user-item">
                            <h2>Customer View</h2>
                            <br>
                            <span>Sort by status:</span>
                            <a href="ad_page_customer.jsp?sort=All">All |</a>
                            <a href="ad_page_customer.jsp?sort=N">None |</a>
                            <a href="ad_page_customer.jsp?sort=G">Gold Class |</a>
                            <a href="ad_page_customer.jsp?sort=P">Premium Class |</a>
                            <a href="ad_page_customer.jsp?sort=D">Diamond Class</a>
                            <c:forEach items="${user.listUser}" var="us">
                                <c:if test="${param.sort eq us.cstatus}">
                                    <form action="AdminDispatcherServlet" method="post">
                                        <input readonly="" type="hidden" value="${us.cid}" name="uscid" />
                                        <label for="uscname">Name:</label>
                                        <input readonly="" name="uscname" type="text" value="${us.cname}" />
                                        <label for="usemail">Email:</label>
                                        <input readonly="" name="usemail" type="email" value="${us.email}" />
                                        <label for="usaddress">Address:</label>
                                        <input readonly="" name="stemail" type="email" value="${us.address}" />
                                        <label for="uscstatus">Status:</label>
                                        <input readonly="" name="uscstatus" type="email" value="${us.cstatus}" />
                                    </form>
                                </c:if>
                                <c:if test="${param.sort eq 'All' or param.sort eq null}">
                                    <form action="AdminDispatcherServlet" method="post">
                                        <input readonly="" type="hidden" value="${us.cid}" name="uscid" />
                                        <label for="uscname">Name:</label>
                                        <input readonly="" name="uscname" type="text" value="${us.cname}" />
                                        <label for="usemail">Email:</label>
                                        <input readonly="" name="usemail" type="email" value="${us.email}" />
                                        <label for="usaddress">Address:</label>
                                        <input readonly="" name="stemail" type="email" value="${us.address}" />
                                        <label for="uscstatus">Status:</label>
                                        <input readonly="" name="uscstatus" type="email" value="${us.cstatus}" />
                                    </form>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>
