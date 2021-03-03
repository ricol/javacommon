/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.math.geometry;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author ricolwang
 */
public class PolygonShape extends ConfinedShape
{

    protected ArrayList<Line> sides = new ArrayList<>();

    public PolygonShape()
    {

    }

    public void addSide(Line aLine)
    {
        this.sides.add(aLine);
    }

    public void addSides(ArrayList<Line> lines)
    {
        this.sides.addAll(lines);
    }

    public void removeSide(Line aLine)
    {
        this.sides.remove(aLine);
    }

    public void removeSides(Set<Line> lines)
    {
        this.sides.removeAll(lines);
    }

    public int getSidesCount()
    {
        return this.sides.size();
    }

    @Override
    public boolean intersect(ConfinedShape theTarget)
    {
        return false;
    }

    @Override
    public boolean contain(ConfinedShape theTarget)
    {
        return false;
    }

    @Override
    public double getArea()
    {
        return 0;
    }

    @Override
    public double getCircumference()
    {
        double sum = 0;
        for (Line aLine : this.sides)
        {
            sum += aLine.getLength();
        }

        return sum;
    }

    @Override
    public void refresh(double changeX, double changeY, double changeWidth, double changeHeight)
    {
        super.refresh(changeX, changeY, changeWidth, changeHeight);
        for (Line aLine : this.sides)
        {
            aLine.refresh(changeX, changeY, changeWidth, changeHeight);
        }
    }

    @Override
    public void print(String text)
    {
        System.out.println(text + " - PolygonShape: " + this.sides.size() + " Lines.");
    }
}
