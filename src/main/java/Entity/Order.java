/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author Hjalmar
 */
public class Order {
    
    int id;
    double price;
    String username;
    ArrayList<Cupcake> cupcakes;

    public Order(int id, double price, String username, ArrayList<Cupcake> cupcakes) {
        this.id = id;
        this.price = price;
        this.username = username;
        this.cupcakes = cupcakes;
    }
        public Order(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Cupcake> getCupcakes() {
        return cupcakes;
    }

    public void setCupcakes(ArrayList<Cupcake> cupcakes) {
        this.cupcakes = cupcakes;
    }
    
    
    
}
