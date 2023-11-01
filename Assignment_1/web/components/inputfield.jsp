<%-- 
    Document   : inputfield
    Created on : Oct 20, 2023, 3:12:59 PM
    Author     : 84859
--%>
<%@page import="utils.GetParam"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>


<%
    
    String field = request.getParameter("field");
    String value = (String) GetParam.getClientParams(request, field, "");
    if(value != null)
        value = new String(value.getBytes("iso-8859-1"), "UTF-8");
    if (request.getParameter("field").equals("pass")) {
        value = "";
    }
    if (request.getParameter("defaultValue") != null && value.equals("")) {
        value = request.getParameter("defaultValue");
    }
    String error = (String) GetParam.getClientAttribute(request, request.getParameter("label") + "Error", "");
%>
<div>
    <label for="${param.field}" >${param.label}</label>
    <input type="${param.type}" value="<%=value%>"  id="${param.field}"  name="${param.field}" />
    <p><%=error%></p>
</div>


