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
import java.sql.Connection;
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
    private final Connection conn = dbc.getConnection();

    public DAO(DataSource ds) {
        dbc.setDataSource(ds);
    }
/**
 * Returns a user object if the username and password is valid. it returns null if it's not a valid user
 * @param username users login name
 * @param password users login password
 * @return user object
 */
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

    /**
     * Creates new user in the database and validates afterwards. Returns null if it fails. Else returns the newly created user.
     * @param username users login name
     * @param password users login password
     * @param email users email
     * @return user object
     */
    public User createAndValidateUser(String username, String password, String email) {
        try {
            dbc.open();

            String sql = "INSERT INTO cupcake_factory.users (users.username, users.password, users.email, users.balance, users.role) values ('" + username + "', '" + password + "', '"
                    + email + "', " + 0 + ", " + 1 + ");";

            Statement stmt = dbc.getConnection().createStatement();

            stmt.executeUpdate(sql);

            dbc.close();

            User u = validateUser(username, password);

            return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Returns arraylist with all users. returns null if user table is empty.
     * @return ArrayList with user objects
     */
    public ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<>();
        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.users;";
            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                String username = resultset.getString("username");
                String email = resultset.getString("email");
                String password = resultset.getString("password");
                int role = resultset.getInt("role");
                int id = resultset.getInt("user_Id");

                if (role != 0) {
                    users.add(new User(id, username, password, email, role));
                }
            }
            dbc.close();

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns user object with the same id as the parameter. Returns null if no match
     * @param id users unique id
     * @return user object
     */
    public User getUser(int id)
    {
        User user = null;
        try {
            dbc.open();

            String sql = "SELECT * FROM cupcake_factory.users WHERE user_Id = " + id + ";";
            ResultSet resultset = dbc.query(sql);

            while (resultset.next()) {
                String username = resultset.getString("username");
                String email = resultset.getString("email");
                String password = resultset.getString("password");
                int role = resultset.getInt("role");
                int userId = resultset.getInt("user_Id");

                user = new User(userId, username, password, email, role);
            }
            dbc.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all orders. Returns null if order table is empty.
     * @return Arraylist with order objects
     */
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
    
    /**
     * returns an arraylist of all orders matching the parameter id. Returns null if there's no matches.
     * @param id unique user id
     * @return ArrayList with order objects
     */
    public ArrayList<Order> getOrdersUserId(int id) {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            dbc.open();

            String sql = "select orders.order_Id, users.username from orders "
                    + "inner join users on orders.user = users.user_Id where orders.user = " + id + ";";

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

    /** 
     * returns an arraylist of all orders with all tables inner joined. Returns null if there's no orders.
     * @return ArrayList with order objects
     */
    public ArrayList<Order> getOrdersWithInfo() {
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
                cupcakeBottomId = resultset.getInt("cupcake_bottoms.bottom_Id");

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
    
    public ArrayList<Order> getOrdersWithInfoForUser(int id) {
        ArrayList<Order> orderList = new ArrayList<>();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();
        String username = null, bottomName, topName;
        int amount, orderId = 1, cupcakeTopId, cupcakeBottomId;
        double price = 0.0;

        try {
            dbc.open();

            String sql = "select users.username, cupcake_bottoms.name, cupcake_tops.name, cupcake_tops.top_Id, cupcake_bottoms.bottom_Id, cupcakeOrders.amount, cupcakeOrders.order, orders.price "
                    + "from cupcake_factory.cupcakeOrders "
                    + "inner join orders on cupcakeOrders.order = orders.order_Id "
                    + "inner join users on orders.user = users.user_Id "
                    + "inner join cupcakes on cupcakeOrders.cupcake = cupcakes.cupcake_Id "
                    + "inner join cupcake_bottoms on cupcakes.bottom = cupcake_bottoms.bottom_Id "
                    + "inner join cupcake_tops on cupcakes.top = cupcake_tops.top_Id where orders.user = " + id + " order by cupcakeOrders.order;";
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
                cupcakeBottomId = resultset.getInt("cupcake_bottoms.bottom_Id");

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

    /**
     * returns order matching the parameter id. returns null if there's no matches.
     * @param id unique order id
     * @return ArrayList with order objects
     */
    public ArrayList<Order> getOrder(int id) {
        ArrayList<Order> orderList = new ArrayList<>();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();
        String username = null, bottomName, topName;
        int amount, orderId = 1, cupcakeTopId, cupcakeBottomId;
        double price = 0.0;

        try {
            dbc.open();

            String sql = "select users.username, cupcake_bottoms.name, cupcake_tops.name, cupcake_tops.top_Id, cupcake_bottoms.bottom_Id, cupcakeOrders.amount, cupcakeOrders.order, orders.price "
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
                cupcakeTopId = resultset.getInt("cupcake_tops.top_Id");
                cupcakeBottomId = resultset.getInt("cupcake_bottoms.bottom_Id");

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

//DEPRICATEROO'D
/**
 * Takes an arraylist of orderpieces and an int for the user id and inserts both into the database.
 * if it inserts the parameters successfully into the database it will return true. otherwise returns false.
 * @param orderPieces products from basket
 * @param userId unique user id
 * @return Boolean
 */
    public boolean createOrder(ArrayList<OrderPiece> orderPieces, int userId) {
        double price = 0;
        ArrayList<Cupcake> cupcakes = new ArrayList();

        for (OrderPiece piece : orderPieces) {
            price += piece.getPrice();
            cupcakes.add(piece.getCake());
        }

        try {
            dbc.open();
            Statement stmt = dbc.getConnection().createStatement();

            String sql1 = "INSERT INTO cupcake_factory.orders (orders.user, orders.price) values ('" + userId + "', '" + price + "');";
            stmt.executeUpdate(sql1);

            String sql2 = "select orders.order_Id from orders order by order_Id desc LIMIT 1";
            ResultSet resultset = dbc.query(sql2);

            int orderId = 0;
            while (resultset.next()) {
                orderId = resultset.getInt("order_Id");
            }

            for (Cupcake cupcake : cupcakes) {
                String sql3 = "INSERT INTO cupcake_factory.cupcakes (cupcakes.top, cupcakes.bottom) values ('" + cupcake.getTop().getId() + "', '" + cupcake.getBottom().getId() + "');";
                stmt.executeUpdate(sql3);

                String sql4 = "select cupcakes.cupcake_Id from cupcakes order by cupcake_Id desc LIMIT 1";
                resultset = dbc.query(sql4);

                int cakeId = 0;
                while (resultset.next()) {
                    cakeId = resultset.getInt("cupcakes.cupcake_Id");
                }
                String sql5 = "INSERT INTO cupcake_factory.cupcakeOrders (cupcakeOrders.order, cupcakeOrders.cupcake, cupcakeOrders.amount) values (" + orderId + ", " + cakeId + ", " + cupcake.getAmount() + ");";
                stmt.executeUpdate(sql5);
            }

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * returns arraylist of all cupcake tops.
     * @return ArrayList with cupcakepart objects
     */
    public ArrayList<CupcakePart> getCupcakeTops() {
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

    /**
     * returns arraylist of all cupcake bottoms.
     * @return ArrayList with cupcakePart objects
     */
    public ArrayList<CupcakePart> getCupcakeBottoms() {
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

    /**
     * Returns cupcake bottom matching with the parameter id
     * @param id unique cupcakepart id
     * @return CupcakePart object
     */
    public CupcakePart getCupcakeBottom(int id) {
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
    

    /**
     * returns cupcake top matching with the parameter id.
     * @param id unique cupcakepart id
     * @return CupcakePart object
     */
    public CupcakePart getCupcakeTop(int id) {
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
    
/**
 * Deletes order in database. Returns true if successful else returns false.
 * @param id unique order id
 * @return boolean
 */
    public boolean deleteOrder(int id) {
        try {
            dbc.open();

            String sql = "delete from cupcake_factory.cupcakeOrders where cupcakeOrders.order = " + id + ";";

            Statement stmt = dbc.getConnection().createStatement();

            stmt.executeUpdate(sql);

            String sql2 = "delete from cupcake_factory.orders where orders.order_Id = " + id + ";";

            stmt.executeUpdate(sql2);

            dbc.close();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
    /**
     * Deletes user in database. Returns true if successful else returns false.
     * @param userId unique order id
     * @return boolean
     */
     public boolean deleteUser(int userId)
    {
        try {
            dbc.open();
            Statement stmt = dbc.getConnection().createStatement();

            String userDel = "DELETE FROM cupcake_factory.users WHERE user_Id = " + userId + ";";
            String orderDel = "DELETE FROM cupcake_factory.orders WHERE user = " + userId + ";";
            String cupcakeOrderDel = "DELETE FROM cupcake_factory.cupcakeOrders WHERE cupcakeOrders.order = (SELECT order_Id FROM cupcake_factory.orders WHERE user = " + userId + ");";

            stmt.executeUpdate(cupcakeOrderDel);
            stmt.executeUpdate(orderDel);
            stmt.executeUpdate(userDel);

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
