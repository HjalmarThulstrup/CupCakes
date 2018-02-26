/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hjalmar
 */
public class Cupcake
{

    CupcakePart top, bottom; //Maybe change this to use CupcakePart
    int amount;

    public Cupcake(CupcakePart top, CupcakePart bottom, int amount)
    {
        this.top = top;
        this.bottom = bottom;
        this.amount = amount;
    }

    public CupcakePart getTop()
    {
        return top;
    }

    public void setTop(CupcakePart top)
    {
        this.top = top;
    }

    public CupcakePart getBottom()
    {
        return bottom;
    }

    public void setBottom(CupcakePart bottom)
    {
        this.bottom = bottom;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "Cupcake{" + "top: " + top.getName() + ", bottom: " + bottom.getName() + ", amount: " + amount + '}';
    }

}
