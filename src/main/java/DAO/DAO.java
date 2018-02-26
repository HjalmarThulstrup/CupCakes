/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Cupcake;
import Entity.CupcakePart;
import Entity.Order;
import Entity.OrderPiece;
import Entity.User;
import dbconnector.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author Hjalmar
 */
public class DAO {

    private DBConnector dbc = new DBConnector();

    public DAO(DataSource ds) {
        dbc.setDataSource(ds);
    }

    public User validateUser(String username, String password) {
        try {
            dbc.open();

            String sql = "select * from cupcake_factory.users where users.username = \"" + username + "\" and users.password = \"" + password + "\";";
            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                int userid = resultset.getInt("user_id");
                String username1 = resultset.getString("username");
                String userpassword = resultset.getString("password");
                String email = resultset.getString("email");
                int admin = resultset.getInt("role");

                return new User(userid, username1, userpassword, email, admin);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean createUser(String username, String password, String email) {
        try {
            dbc.open();

            String sql = "INSERT INTO cupcake_factory.users (users.username, users.password, users.email, users.balance, users.role) values (\"" + username + "\", \"" + password + "\", \""
                    + email + "\", " + 0 + ", " + 1 + ");";

            Statement stmt = dbc.getConnection().createStatement();

            stmt.executeUpdate(sql);

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            dbc.open();

            String sql = "select orders.order_Id, users.username from orders "
                    + "inner join users on orders.user = users.user_Id;";

            ResultSet resultset = dbc.query(sql);
            while (resultset.next()) {
                String username = resultset.getString("users.username");
                int order_id = resultset.getInt("orders.order_Id");

                orders.add(new Order(order_id, username));
            }

            dbc.close();

            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Order> getOrdersWithInfo()
    {
        ArrayList<Order> orderList = new ArrayList<>();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();
        String username = null, bottomName, topName;
        int amount, orderId = 1, cupcakeTopId, cupcakeBottomId;
        double price = 0.0;

        try {
            dbc.open();

            String sql = "select users.username, cupcake_bottoms.name, cupcake_tops.name, cupcakeOrders.amount, cupcakeOrders.order, orders.price "
                    + "from cupcake_factory.cupcakeOrders "
                    + "inner join orders on cupcakeOrders.order = orders.order_Id "
                    + "inner join users on orders.user = users.user_Id "
                    + "inner join cupcakes on cupcakeOrders.cupcake = cupcakes.cupcake_Id "
                    + "inner join cupcake_bottoms on cupcakes.bottom = cupcake_bottoms.bottom_Id "
                    + "inner join cupcake_tops on cupcakes.top = cupcake_tops.top_Id order by cupcakeOrders.order;";
            ResultSet resultset = dbc.query(sql);
            int prevOrderId = orderId;
            while (resultset.next()) {
                orderId = resultset.getInt("cupcakeOrders.order");
                if (orderId != prevOrderId) {
                    orderList.add(new Order(prevOrderId, price, username, cupcakeList));
                    cupcakeList = new ArrayList<>();
                }
                username = resultset.getString("users.username");
                bottomName = resultset.getString("cupcake_bottoms.name");
                topName = resultset.getString("cupcake_tops.name");
                amount = resultset.getInt("amount");
                price = resultset.getDouble("orders.price");
                cupcakeTopId = resultset.getInt("cupcake_tops.top_Id");
                cupcakeBottomId = resultset.getInt("cupcake_tops.top_Id");

                cupcakeList.add(new Cupcake(new CupcakePart(cupcakeTopId, price, topName), new CupcakePart(cupcakeBottomId, price, topName), amount));

                prevOrderId = orderId;
            }
            orderList.add(new Order(orderId, price, username, cupcakeList));

            dbc.close();

            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ArrayList<Order> getOrder(int id) {
        ArrayList<Order> orderList = new ArrayList<>();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();
        String username = null, bottomName, topName;
        int amount, orderId = 1;
        double price = 0.0;

        try {
            dbc.open();

            String sql = "select users.username, cupcake_bottoms.name, cupcake_tops.name, cupcakeOrders.amount, cupcakeOrders.order, orders.price "
                    + "from cupcake_factory.cupcakeOrders "
                    + "inner join orders on cupcakeOrders.order = orders.order_Id "
                    + "inner join users on orders.user = users.user_Id "
                    + "inner join cupcakes on cupcakeOrders.cupcake = cupcakes.cupcake_Id "
                    + "inner join cupcake_bottoms on cupcakes.bottom = cupcake_bottoms.bottom_Id "
                    + "inner join cupcake_tops on cupcakes.top = cupcake_tops.top_Id where cupcakeOrders.order = " + id + " order by cupcakeOrders.order;";
            ResultSet resultset = dbc.query(sql);
            int prevOrderId = orderId;
            while (resultset.next()) {
                orderId = resultset.getInt("cupcakeOrders.order");
                if (orderId != prevOrderId) {
                    orderList.add(new Order(prevOrderId, price, username, cupcakeList));
                    cupcakeList = new ArrayList<>();
                }
                username = resultset.getString("users.username");
                bottomName = resultset.getString("cupcake_bottoms.name");
                topName = resultset.getString("cupcake_tops.name");
                amount = resultset.getInt("amount");
                price = resultset.getDouble("orders.price");

                cupcakeList.add(new Cupcake(topName, bottomName, amount));

                prevOrderId = orderId;
            }
            orderList.add(new Order(orderId, price, username, cupcakeList));

            dbc.close();

            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


//DEPRICATEROO'D
    public boolean createOrder(int userId, int price, int bottomId, int topId, int amount) {
        try {
            dbc.open();

            String sql = "INSERT INTO cupcake_factory.orders (orders.user, orders.price) values ('" + userId + "', '" + price + "');";

            Statement stmt = dbc.getConnection().createStatement();

            stmt.executeUpdate(sql);

            String sql2 = "INSERT INTO cupcake_factory.cupcakes (cupcakes.bottom, cupcakes.top) values ('" + bottomId + "', '" + topId + "');";

            stmt.executeUpdate(sql2);
            
            String sql3 = "";

            stmt.executeUpdate(sql3);

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public ArrayList<CupcakePart> getCupcakeTops()
    {
        ArrayList<CupcakePart> parts = new ArrayList<>();

        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.cupcake_tops;";
            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                int id = resultset.getInt("top_Id");
                double price = resultset.getDouble("price");
                String name = resultset.getString("name");

                parts.add(new CupcakePart(id, price, name));
            }

            dbc.close();
            return parts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<CupcakePart> getCupcakeBottoms()
    {
        ArrayList<CupcakePart> parts = new ArrayList<>();

        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.cupcake_bottoms;";
            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                int id = resultset.getInt("bottom_Id");
                double price = resultset.getDouble("price");
                String name = resultset.getString("name");

                parts.add(new CupcakePart(id, price, name));
            }

            dbc.close();
            return parts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CupcakePart getCupcakeBottom(int id)
    {
        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.cupcake_bottoms WHERE bottom_Id = " + id + ";";

            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                double price = resultset.getDouble("price");
                String name = resultset.getString("name");

                return new CupcakePart(id, price, name);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CupcakePart getCupcakeTop(int id)
    {
        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.cupcake_tops WHERE top_Id = " + id + ";";

            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                double price = resultset.getDouble("price");
                String name = resultset.getString("name");

                return new CupcakePart(id, price, name);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void performTransaction(ArrayList<OrderPiece> orderList)
    {
        try {
            dbc.open();

            for (OrderPiece order : orderList) {
                try {
                    Statement stmt = conn.getConnection().createStatement();
                    String sql = "";

                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}