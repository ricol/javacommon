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
public class ClosureShape extends Shape
{

    public boolean intersect(ClosureShape theTarget)
    {
        return false;
    }

    public boolean contain(ClosureShape theTarget)
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

    public boolean collideWith(ClosureShape theShape)
    {
        return false;
    }
}
