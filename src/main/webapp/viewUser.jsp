<%-- 
    Document   : viewOrder
    Created on : 26-02-2018, 19:11:04
    Author     : Hjalmar
--%>

<%@page import="Entity.User"%>
<%@page import="Entity.OrderId"%>
<%@page import="Entity.Cupcake"%>
<%@page import="datasource.DataSource1"%>
<%@page import="DAO.DAO"%>
<%@page import="Entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>

    </head>
    <% DAO dao = new DAO(new DataSource1().getDataSource());
        int userId = (Integer) session.getAttribute("userIdForView");
    %>
    <body>
        <%@include file="nav.jsp" %>
        <div class="wrapper">
            <h2>Order</h2>
            <div class="wrapper" style="width: 50%; margin-top: 0; margin-bottom: 20px;">
                <%
                    User user = dao.getUser(userId);
                    if (user.getUsername() != null) {
                        out.println("<b>User id:</b> " + user.getId() + " <b>Username:</b> " + user.getUsername() + " <b>Email:</b> " + user.getEmail());
                    }
                %>
            <form action='DeleteUser' method='post'>
                <input type="submit" value="Delete User">
            </form>

                <form action="./admin.jsp">
                    <input type="submit" value="Go back" />
                </form>
            </div> 
        </div>
    </body>
</html>