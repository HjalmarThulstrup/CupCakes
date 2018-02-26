/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Wicktus
 */
public class OrderPiece
{
    private double price;
    private Cupcake cake;

    public OrderPiece(double price, Cupcake cake)
    {
        this.price = price;
        this.cake = cake;
    }

    public double getPrice()
    {
        return price;
    }

    public Cupcake getCake()
    {
        return cake;
    }

    @Override
    public String toString()
    {
        return "price=" + price + ", cake=" + cake.toString();
    }
    
    
}
