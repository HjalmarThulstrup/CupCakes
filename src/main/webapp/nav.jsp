
<form class="nav" style="margin: 0;" action='logOut' method='post'>
    <nav>
        <div>
            <img src="Cupcakes-10.png" alt="" style="margin-right: 10px; margin-bottom: -5px; margin-top: 5px;" />
            <a href="index.jsp">Front Page</a>
            <a href="order.jsp">Order Cupcakes</a>
            <a href="prices.jsp">Prices</a>
            <% if (session.getAttribute("user") != null) {
                    out.println("<a href='user.jsp' >User</a>");
                    out.println("<a href='cart.jsp'>Cart</a>");
                    out.println("<a href='./logOut'>Log Out</a>");
                } else if (session.getAttribute("admin") != null) {
                    out.println("<a href='admin.jsp' >Admin</a>");
                    out.println("<a href='./logOut'>Log Out</a>");
                } else {
                    out.println("<a href='login.jsp' >Login</a>");
                }%>
        </div>
    </nav>
</form>
