/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.math.geometry;

import au.com.rmit.math.common.MathConsts;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 *
 * @author Philology
 */
public class Point extends Shape
{

    public double x, y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getDistanceFrom(Point B)
    {
        double delX = B.x - x;
        double delY = B.y - y;
        return sqrt(delX * delX + delY * delY);
    }

    @Override
    public void refresh(double changeX, double changeY, double changeWidth, double changeHeight)
    {
        super.refresh(changeX, changeY, changeWidth, changeHeight);

        x += changeX;
        y += changeY;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Point)
        {
            Point B = (Point) obj;
            return abs(this.x - B.x) <= MathConsts.Minimum && abs(this.y - B.y) <= MathConsts.Minimum;
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    public static Point getZeroPoint()
    {
        Point zero = new Point(0, 0);
        return zero;
    }
}
