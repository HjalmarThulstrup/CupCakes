<%-- 
    Document   : testjsp
    Created on : 26-02-2018, 08:52:19
    Author     : Hjalmar
--%>

<%@page import="Entity.CupcakePart"%>
<%@page import="datasource.DataSource1"%>
<%@page import="Entity.User"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Cupcakes</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="nav.jsp"%>
        <%DAO dao = new DAO(new DataSource1().getDataSource());%>

        <div class="wrapper">
            <h1>Here you can order CupCakes</h1>
            <div>    
                <form method="GET" action="AddToCart">
                    <table id="contentTable">
                        <tr>
                            <td>Top</td>
                            <td>Bottom</td>
                            <td>Amount</td>
                        </tr>
                        <tr>
                            <td>
                                <select name="top">
                                    <% for (CupcakePart top : dao.getCupcakeTops()) {
                                            out.println("<option value=" + top.getId() + ">" + top.getName() + "</option>");
                                        }%>
                                </select>
                            </td> 
                            <td>
                                <select name="bottom">
                                    <% for (CupcakePart bottom : dao.getCupcakeBottoms()) {
                                            out.println("<option value=" + bottom.getId() + ">" + bottom.getName() + "</option>");
                                        }%>
                                </select>
                            </td>         
                            <td><input type="number" min="1" max="200" value="1" id="heightTextBox" name="amount"/></td>
                            <td><input type="submit" value="Order" style="float:right;"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
