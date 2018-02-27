<%-- 
    Document   : prices
    Created on : 27-02-2018, 00:28:11
    Author     : Hjalmar
--%>

<%@page import="Entity.CupcakePart"%>
<%@page import="datasource.DataSource1"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prices</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>

    </head>
    <%  DAO dao = new DAO(new DataSource1().getDataSource());%>
    <body>
        <%@include file="nav.jsp" %>
        <h1 style="text-align: center; margin: auto; margin-top: 2%; margin-bottom: 1%;">Prices</h1>
        <div class="wrapper" style="margin-top: 0;">
            <h2>Bottoms</h2>
            <table>
                <tr>
                    <td><b>Name:</b></td>
                    <td><b>Price:</b></td>
                </tr>
                <% for (CupcakePart part : dao.getCupcakeBottoms()) {
                        //out.println("<b>Name:</b> " + part.getName() + " <b>Price:</b> " + part.getPrice());
                        out.println("<tr>");
                        out.println("<td>" + part.getName() + "</td>");
                        out.println("<td>" + part.getPrice() + "</td>");
                        out.println("</tr>");
                }
                %>
            </table>
            <h2>Tops</h2>
            <table>
                <tr>
                    <td><b>Name:</b></td>
                    <td><b>Price:</b></td>
                </tr>
                <% for (CupcakePart part : dao.getCupcakeTops()) {
                        //out.println("<b>Name:</b> " + part.getName() + " <b>Price:</b> " + part.getPrice());
                        out.println("<tr>");
                        out.println("<td>" + part.getName() + "</td>");
                        out.println("<td>" + part.getPrice() + "</td>");
                        out.println("</tr>");
                }
                %>
            </table>
        </div>
    </body>
</html>
