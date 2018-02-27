<%-- 
    Document   : viewOrder
    Created on : 26-02-2018, 19:11:04
    Author     : Hjalmar
--%>

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
        OrderId id = (OrderId) session.getAttribute("order");
    %>
    <body>
        <%@include file="nav.jsp" %>
        <div class="wrapper">
            <h2>Order</h2>
            <div class="wrapper" style="width: 50%; margin-top: 0; margin-bottom: 20px;">
                <% for (Order order : dao.getOrder(id.getId())) {
                        if (order.getUsername() != null) {
                            out.println("<b>Order id:</b> " + order.getId() + " <b>Username:</b> " + order.getUsername() + " <b>Price:</b> " + order.getPrice());
                            for (Cupcake cupcake : order.getCupcakes()) {
                                out.println("<br>-----");
                                out.println("<b>Bottom:</b> " + cupcake.getBottom().getName() + " <b>Top:</b> " + cupcake.getTop().getName() + " <b>Amount:</b> " + cupcake.getAmount());
                            }
                        }

                    }%>
                <form action="./admin.jsp">
                    <input type="submit" value="Go back" />
                </form>
            </div> 
        </div>
    </body>
</html>
