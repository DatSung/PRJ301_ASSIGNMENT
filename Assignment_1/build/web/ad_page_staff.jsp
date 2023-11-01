<%-- 
    Document   : ad_page_staff
    Created on : Oct 23, 2023, 10:15:07 AM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="staff" class="dao.StaffDAO" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Staff Management</title>
            <style>
                /* Style the container holding the service content */
                .staff-container {
                    width: 100%;
                    margin: 0 auto;
                    padding: 20px;
                }

                /* Style the service list */
                .staff-list {
                    width: 100%;
                    border: 1px solid #ccc;
                    padding: 10px;
                }
                label{
                    margin-left: 10px;
                }
                /* Style the service items */
                .staff-item {
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
                .staff-item a {
                    text-decoration: none; /* Remove underline */
                    color: #007bff; /* Text color */
                    font-weight: bold; /* Make the text bold */
                }

                .staff-item h2 {
                    text-align: center;
                }

                /* Hover effect for anchor tags */
                .staff-item a:hover {
                    color: #0056b3; /* Change text color on hover */
                }

                /* Style labels inside service-item */
                .staff-item label {
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
            <div class="staff-container">
                <div class="staff-content">
                    <div class="staff-list">

                        <div class="staff-item">
                            <p>${requestScope.removestaffresult}</p>
                            <p>${requestScope.updatestaffresult}</p>
                            <h2>Staff Management</h2>
                            <a href="AdminDispatcherServlet?action=viewstaffcreate"> + Create New Staff</a>
                            <br>
                            <span>Sort by role:</span>
                            <a href="ad_page_staff.jsp?sort=All">All |</a>
                            <a href="ad_page_staff.jsp?sort=TE">Technical Engineer |</a>
                            <a href="ad_page_staff.jsp?sort=ST">Supporter</a>

                            <c:if test="${param.sort eq 'All' or param.sort eq null}">

                                <c:forEach items="${staff.listStaffs}" var="st">
                                    <c:if test="${st.srole != 'AD'}">
                                        <form action="AdminDispatcherServlet" method="post">
                                            <input type="hidden" value="${st.sid}" name="stid" />
                                            <label for="stname">Name:</label>
                                            <input name="stname" type="text" value="${st.name}" />
                                            <label for="stemail">Email:</label>
                                            <input name="stemail" type="text" value="${st.email}" />
                                            <label for="stpass">Password:</label>
                                            <input name="stpass" type="text" value="${st.password}"/>
                                            <label for="strole">Role:</label>
                                            <select name="strole" class="form-staff">
                                                <c:choose>
                                                    <c:when test="${st.srole eq 'TE'}">
                                                        <option value="TE" selected="selected">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:when>
                                                    <c:when test="${st.srole eq 'ST'}">
                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST" selected="selected">Supporter</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <button class="update-button" type="submit" name="action" value="viewstaffupdate" >Update</button>
                                            <button class="remove-button" type="submit" name="action" value="viewstaffremove">Remove</button>
                                        </form>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${param.sort eq 'TE'}">

                                <c:forEach items="${staff.listStaffs}" var="st">
                                    <c:if test="${st.srole != 'AD' and st.srole == 'TE'}">
                                        <form action="AdminDispatcherServlet" method="post">
                                            <input type="hidden" value="${st.sid}" name="stid" />
                                            <label for="stname">Name:</label>
                                            <input name="stname" type="text" value="${st.name}" />
                                            <label for="stemail">Email:</label>
                                            <input name="stemail" type="text" value="${st.email}" />
                                            <label for="stpass">Password:</label>
                                            <input name="stpass" type="text" value="${st.password}"/>
                                            <label for="strole">Role:</label>
                                            <select name="strole">
                                                <c:choose>

                                                    <c:when test="${st.srole eq 'TE'}">

                                                        <option value="TE" selected="selected">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:when>
                                                    <c:when test="${st.srole eq 'ST'}">

                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST" selected="selected">Supporter</option>
                                                    </c:when>
                                                    <c:otherwise>

                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <button type="submit" name="action" value="viewstaffupdate" >Update</button>
                                            <button class="remove-button" type="submit" name="action" value="viewstaffremove">Remove</button>
                                        </form>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${param.sort eq 'ST'}">

                                <c:forEach items="${staff.listStaffs}" var="st">
                                    <c:if test="${st.srole != 'AD' and st.srole == 'ST'}">
                                        <form action="AdminDispatcherServlet" method="post">
                                            <input type="hidden" value="${st.sid}" name="stid" />
                                            <label for="stname">Name:</label>
                                            <input name="stname" type="text" value="${st.name}" />
                                            <label for="stemail">Email:</label>
                                            <input name="stemail" type="text" value="${st.email}" />
                                            <label for="stpass">Password:</label>
                                            <input name="stpass" type="text" value="${st.password}"/>
                                            <label for="strole">Role:</label>
                                            <select name="strole">
                                                <c:choose>
                                                    <c:when test="${st.srole eq 'TE'}">
                                                        <option value="TE" selected="selected">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:when>
                                                    <c:when test="${st.srole eq 'ST'}"> 
                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST" selected="selected">Supporter</option>
                                                    </c:when>
                                                    <c:otherwise>                                 
                                                        <option value="TE">Technical Engineer</option>
                                                        <option value="ST">Supporter</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <button type="submit" name="action" value="viewstaffupdate" >Update</button>
                                            <button class="remove-button" type="submit" name="action" value="viewstaffremove">Remove</button>
                                        </form>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%@include file="./components/footer.jsp" %>
    </body>
</html>