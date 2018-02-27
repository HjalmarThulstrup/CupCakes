<%-- 
    Document   : Cart
    Created on : Feb 26, 2018, 6:44:25 PM
    Author     : Wicktus
--%>

<%@page import="Entity.OrderPiece"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Cupcake"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <div class="wrapper">
            <h1>Your Cart</h1>
            <div>
                <%
                    if (session.getAttribute("orderList") != null) {

                        ArrayList<OrderPiece> orders = (ArrayList<OrderPiece>) session.getAttribute("orderList");
                        for (OrderPiece order : orders) {
                            out.println("<tr>");

                            out.println("<td>");
                            out.println("<p>" + order.toString() + "</p>");
                            out.println("</td>");

                            out.println("<td>");
                            out.println("<form method=\"GET\" action=\"./RemoveFromCart\">");
                            out.println("<input type=\"submit\" id=\"orderDeleteButton\" value=\"Remove from cart\"/>");
                            out.println("<input type=\"hidden\" name=\"order_id\" value=\"" + orders.indexOf(order) + "\"/>");
                            out.println("</form>");
                            out.println("</td>");

                            out.println("</tr>");
                            out.println("<br>");
                        }

                        out.println("<td><form method=\"GET\" action=\"./Checkout\"><a href=\"./Checkout\">Order Cupcakes</a></td>");

                    } else {
                        out.println("is empty");
                    }
                %>
            </div>
        </div>
    </body>
</html>
