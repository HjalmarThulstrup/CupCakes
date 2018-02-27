<%-- 
    Document   : user
    Created on : 26-02-2018, 11:08:06
    Author     : Hjalmar
--%>

<%@page import="Entity.Cupcake"%>
<%@page import="Entity.Order"%>
<%@page import="datasource.DataSource1"%>
<%@page import="DAO.DAO"%>
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
        DAO dao = new DAO(new DataSource1().getDataSource());%>
    <body>
        <%@include file="nav.jsp" %>
        <div class="wrapper" style="margin-top: 2%;">
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
        <div class="wrapper" style="margin-top: 2%;">
            <h2>Orders</h2>
            <% for (Order order : dao.getOrdersWithInfo(user.getId())) {
                    if (order.getUsername() != null) {
                        out.println("<b>Order id:</b> " + order.getId() + " <b>Username:</b> " + order.getUsername() + " <b>Price:</b> " + order.getPrice());
                        for (Cupcake cupcake : order.getCupcakes()) {
                            out.println("<br>-----");
                            out.println("<b>Bottom:</b> " + cupcake.getBottom().getName() + " <b>Top:</b> " + cupcake.getTop().getName() + " <b>Amount:</b> " + cupcake.getAmount());
                        }
                    }
                    out.println("<br>");
                    out.println("<br>");
                }%>

        </div>
    </body>
</html>
