<%-- 
    Document   : admin
    Created on : 26-02-2018, 11:13:37
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
        <title>Admin Page</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <% User user = (User) session.getAttribute("admin");
        DAO dao = new DAO(new DataSource1().getDataSource());%>
    <body>
        <%@include file="nav.jsp" %>
        <h1 style="text-align: center; margin: auto; margin-top: 5%;">Hello <%= user.getUsername()%>!</h1>
        <div class="wrapper" style="margin-top: 0;">
            <h2>Orders</h2>
            <form id='formOrders' style="margin: auto; max-width: 200px;" action='Order' method='post'>
                <select name="orderId">
                    <% for (Order order : dao.getOrders()) {
                            out.println("<option value='" + order.getId() + "'>" + "Order id: " + order.getId() + "</option>");
                        }%>
                </select>
                <input type="submit" value="View Order">
            </form>
            <br>
            <form id='formDeleteOrders' style="margin: auto; max-width: 200px   ;" action='deleteOrder' method='post'>
                <select name="orderId">
                    <% for (Order order : dao.getOrders()) {
                            out.println("<option value='" + order.getId() + "'>" + "Order id: " + order.getId() + "</option>");
                        }%>
                </select>
                 <input type="text" name="is_Admin" value="<%= user.isAdmin()%>" hidden="true">
                <input type="submit" value="Delete Order">
            </form>
        </div>
        <div class="wrapper" style="margin-top: 20px;">
            <h2>Users</h2>
            <form action='ViewUser' method='post'>
                <select name="userId">
                    <% for (User userList : dao.getUsers()) {
                            out.println("<option value='" + userList.getId() + "'>" + "Username: " + userList.getUsername() + "</option>");
                        }%>
                </select>
                <input type="submit" value="View user info">
            </form>
        </div>
    </body>
</html>
