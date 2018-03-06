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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <%  DAO dao = new DAO(new DataSource1().getDataSource());%>
    <body>
        <%@include file="nav.jsp" %>
        <div class="container">
            <div class="container">
                <h2>Tops</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (CupcakePart part : dao.getCupcakeBottoms()) {
                                //out.println("<b>Name:</b> " + part.getName() + " <b>Price:</b> " + part.getPrice());
                                out.println("<tr>");
                                out.println("<td>" + part.getName() + "</td>");
                                out.println("<td>" + part.getPrice() + "</td>");
                                out.println("</tr>");
                            }%>
                    </tbody>
                </table>
            </div><div class="container">
                <h2>Bottoms</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (CupcakePart part : dao.getCupcakeBottoms()) {
                                //out.println("<b>Name:</b> " + part.getName() + " <b>Price:</b> " + part.getPrice());
                                out.println("<tr>");
                                out.println("<td>" + part.getName() + "</td>");
                                out.println("<td>" + part.getPrice() + "</td>");
                                out.println("</tr>");
                            }%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
