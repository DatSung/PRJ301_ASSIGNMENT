<%-- 
    Document   : ad_page_request
    Created on : Oct 24, 2023, 7:38:28 PM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="r" class="dao.RequestDAO" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="icon" type="image/png" href="asset/img/fpt_icon.png">
            <title>Request Management</title>
            <style>
                /* Style the container holding the service content */
                .request-container {
                    width: 100%;
                    margin: 0 auto;
                    padding: 20px;
                }

                /* Style the service list */
                .request-list {
                    width: 100%;
                    border: 1px solid #ccc;
                    padding: 10px;
                }

                label{
                    margin-left: 10px;
                }
                /* Style the service items */
                .request-item {
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
                .request-item a {
                    text-decoration: none; /* Remove underline */
                    color: #007bff; /* Text color */
                    font-weight: bold; /* Make the text bold */
                }

                .request-item h2 {
                    text-align: center;
                }

                /* Hover effect for anchor tags */
                .request-item a:hover {
                    color: #0056b3; /* Change text color on hover */
                }

                /* Style labels inside service-item */
                .request-item label {
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
            <div class="request-container">
                <div class="request-content">
                    <div class="request-list">
                        <div class="request-item">
                            <p>${requestScope.updaterequestresult}</p>
                            <h2>Request Management</h2>
                            <span>Sort by Type:</span>
                            <a href="ad_page_request.jsp?sort=All">All |</a>
                            <a href="ad_page_request.jsp?sort=1">Hỗ trợ kĩ thuật|</a>
                            <a href="ad_page_request.jsp?sort=2">Vấn đề với nhân viên |</a>
                            <a href="ad_page_request.jsp?sort=3">Hỗ trợ các gói dịch vụ</a>
                            <c:forEach items="${r.listRequests}" var="r">
                                <c:if test="${param.sort eq r.rtid.rtid}">
                                    <form action="AdminDispatcherServlet" method="post">
                                        <input readonly="" type="hidden" value="${r.id}" name="rid" />

                                        <label for="rtid">Type:</label>
                                        <input readonly="" name="rtid" type="text" value="${r.rtid.rtname}" />

                                        <label for="rdes">Description:</label>
                                        <input readonly="" name="rdes" type="text" value="${r.des}" />

                                        <label for="rdate">Date:</label>
                                        <input readonly="" name="rdes" type="text" value="${r.rdate}" />

                                        <label for="rcid">From:</label>
                                        <input readonly="" name="rcid" type="text" value="${r.cid.cname}" />

                                        <label for="rsid">Handle:</label>
                                        <input readonly="" name="rsid" type="text" value="${r.sid.name}" />

                                        <label for="rstatus">Status:</label>
                                        <select name="rstatus">
                                            <c:choose>
                                                <c:when test="${r.rstatus eq -1}">
                                                    <option selected="" value="-1" >Cancel</option>
                                                    <option value="0" >Processing</option>
                                                    <option value="1" >Done</option>
                                                </c:when>
                                                <c:when test="${r.rstatus eq 0}">
                                                    <option value="-1" >Cancel</option>
                                                    <option selected="" value="0" >Processing</option>
                                                    <option value="1" >Done</option>
                                                </c:when>
                                                <c:when test="${r.rstatus eq 1}">
                                                    <option value="-1" >Cancel</option>
                                                    <option value="0" >Processing</option>
                                                    <option selected="" value="1" >Done</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                        <button type="submit" name="action" value="viewrequestupdate" >Update</button>

                                    </form>
                                </c:if>

                                <c:if test="${param.sort eq 'All' or param.sort eq null}">
                                    <form action="AdminDispatcherServlet" method="post">
                                        <input readonly="" type="hidden" value="${r.id}" name="rid" />

                                        <label for="rtid">Type:</label>
                                        <input readonly="" name="rtid" type="text" value="${r.rtid.rtname}" />

                                        <label for="rdes">Description:</label>
                                        <input readonly="" name="rdes" type="text" value="${r.des}" />

                                        <label for="rdate">Date:</label>
                                        <input readonly="" name="rdes" type="text" value="${r.rdate}" />

                                        <label for="rcid">From:</label>
                                        <input readonly="" name="rcid" type="text" value="${r.cid.cname}" />

                                        <label for="rsid">Handle:</label>
                                        <input readonly="" name="rsid" type="text" value="${r.sid.name}" />

                                        <label for="rstatus">Status:</label>
                                        <select name="rstatus">
                                            <c:choose>
                                                <c:when test="${r.rstatus eq -1}">
                                                    <option selected="" value="-1" >Cancel</option>
                                                    <option value="0" >Processing</option>
                                                    <option value="1" >Done</option>
                                                </c:when>
                                                <c:when test="${r.rstatus eq 0}">
                                                    <option value="-1" >Cancel</option>
                                                    <option selected="" value="0" >Processing</option>
                                                    <option value="1" >Done</option>
                                                </c:when>
                                                <c:when test="${r.rstatus eq 1}">
                                                    <option value="-1" >Cancel</option>
                                                    <option value="0" >Processing</option>
                                                    <option selected="" value="1" >Done</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                        <button type="submit" name="action" value="viewrequestupdate" >Update</button>

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
