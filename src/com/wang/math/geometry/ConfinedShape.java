/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.math.geometry;

/**
 *
 * @author ricolwang
 */
public class ConfinedShape extends Shape
{

    public boolean intersect(ConfinedShape theTarget)
    {
        return false;
    }

    public boolean contain(ConfinedShape theTarget)
    {
        return false;
    }

    public double getArea()
    {
        return 0;
    }

    public double getCircumference()
    {
        return 0;
    }

    public boolean collideWith(ConfinedShape theShape)
    {
        return false;
    }
}
