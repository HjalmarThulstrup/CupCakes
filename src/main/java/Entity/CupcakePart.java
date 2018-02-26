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
public class CupcakePart
{

    private int id;
    private String name;
    private double price;
    
    public CupcakePart(int id, double price, String name)
    {
        this.id = id;
        this.price = price;
        this.name = name;
    }
        
    public int getId()
    {
        return id;
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }
}
