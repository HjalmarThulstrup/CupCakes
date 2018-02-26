<%-- 
    Document   : user
    Created on : 26-02-2018, 11:08:06
    Author     : Hjalmar
--%>

<%@page import="Entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>

    <% User user = (User) session.getAttribute("user");
       %>
    <body>
        <%@include file="nav.jsp" %>
        <div class="wrapper" style="margin-top: 5%;">
            <h1>Hello <%= user.getUsername()%>!</h1>
            <h3>User info</h3>
            <table>
                <tr>
                    <td>Username</td>
                    <td><p><%= user.getUsername()%></p></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><p><%= user.getEmail()%></p></td>
                </tr>
            </table>
        </div>
    </body>
</html>
