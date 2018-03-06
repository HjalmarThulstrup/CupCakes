<%-- 
    Document   : login
    Created on : 26-02-2018, 09:58:19
    Author     : Hjalmar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="stylesheet.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-color: chocolate; 
                color: #FFFDD0;
                overflow: auto;
            }
            img {
                margin: auto;
            }
        </style>

    </head>
    <body>
        <%@include file="nav.jsp"%>

        <div class="wrapper">
            <h1>Log in here!</h1>

            <form id="formSearch" action="Login" method="post">
                <label id="labelUsername" for="username">Username</label>
                <input type="text" name="username" />
                <label id="labelPassword" for="password">Password</label>
                <input type="password" name="password" />
                <input type="submit" value="SUBMIT" />
            </form>

            <h2>CREATE USER</h2>

            <form id="formCreate" action="UserCreate" method="post">
                <label id="labelUsername" for="username">Username</label>
                <input type="text" name="username" />
                <label id="labelPassword" for="password">Password</label>
                <input type="text" name="password" />
                <label id="labelEmail" for="email">Email</label>
                <input type="text" name="email" />
                <input type="submit" value="CREATE" />
            </form>

            <div style="width: 300px; margin: auto;">
                <img src="Cupcakes-9.png" alt="cupcake" />
            </div>

        </div>

    </body>
</html>
