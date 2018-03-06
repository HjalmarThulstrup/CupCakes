<%@page import="java.util.ArrayList"%>
<%@page import="Entity.OrderId"%>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <%
        User user = (User) session.getAttribute("admin");
        DAO dao = new DAO(new DataSource1().getDataSource());

        OrderId id = (OrderId) session.getAttribute("order");

    %>

    <body>
        <%@include file="nav.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col border">
                    <h2>Orders</h2>
                </div>
                <div class="col border">
                    <h2>Details</h2>
                </div>
            </div>
            <div class="row">
                <div class="col border">
                    <form action='Order' method='post'>
                        <% for (Order order : dao.getOrders()) {%>
                        <a href="./Order?orderId=<%out.print(order.getId());%>"><% out.print("<b>Order id:</b> " + order.getId() + " <b>Username:</b> " + order.getUsername());%></a>
                        <br>
                        <%}%>
                    </form>
                </div>
                <div class="col border">
                    <% if (id != null) {

                            for (Order order : dao.getOrder(id.getId())) {
                                if (order.getUsername() != null) {
                                    out.println("<b>Order id:</b> " + order.getId() + " <b>Username:</b> " + order.getUsername() + " <b>Price:</b> " + order.getPrice());
                                    for (Cupcake cupcake : order.getCupcakes()) {
                                        out.println("<br>-----");
                                        out.println("<b>Bottom:</b> " + cupcake.getBottom().getName() + " <b>Top:</b> " + cupcake.getTop().getName() + " <b>Amount:</b> " + cupcake.getAmount());
                                    }
                                }

                            }
                        }%>
                </div>
            </div>
        </div>

        <h1 style="text-align: center; margin: auto; margin-top: 5%;">Hello <%= user.getUsername()%>!</h1>

        <div class="container"></div>

        <div class="wrapper" style="margin-top: 0;">

            <form id='formDeleteOrders' style="margin: auto; max-width: 200px   ;" action='deleteOrder' method='post'>
                <select name="orderId">
                    <% for (Order order
                                : dao.getOrders()) {
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
                    <% for (User userList
                                : dao.getUsers()) {
                            out.println("<option value='" + userList.getId() + "'>" + "Username: " + userList.getUsername() + "</option>");
                        }%>
                </select>
                <input type="submit" value="View user info">
            </form>
        </div>
    </body>
</html>
