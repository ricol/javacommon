/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.math.geometry;

/**
 * @author ricolwang
 */
public class SpecialRectangleShape extends ConfinedShape
{

    public double left, top, width, height;

    public SpecialRectangleShape(double left, double top, double width, double height)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean collideWith(ConfinedShape theShape)
    {
        if (theShape instanceof CircledShape)
        {
            return Shape.RectangleCollideWithCircle(this, (CircledShape) theShape);
        } else if (theShape instanceof SpecialRectangleShape)
        {
            return Shape.RectangleCollideWithRectangle(this, (SpecialRectangleShape) theShape);
        } else
        {
            return false;
        }
    }

    @Override
    public void print(String text)
    {
        System.out.println(text + " - RectangleShape: " + left + ", " + top + ", " + width + ", " + height);
    }

    @Override
    public String toString()
    {
        return "RectangleShape: " + left + ", " + top + ", " + width + ", " + height;
    }
}
