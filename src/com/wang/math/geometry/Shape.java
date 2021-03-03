/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.math.geometry;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * @author ricolwang
 */
public class Shape
{

    public static enum CircleRectangleCollideDirection
    {

        FROM_TOP, FROM_LEFT, FROM_BOTTOM, FROM_RIGHT, FROM_INSIDE, FROM_OUTSIDE, FROM_TOP_LEFT, FROM_BOTTOM_LEFT, FROM_BOTTOM_RIGHT, FROM_TOP_RIGHT, NO
    }

    ;

    public void print(String text)
    {

    }

    public void refresh(double changeX, double changeY, double changeWidth, double changeHeight)
    {

    }

    public static boolean CircleCollideWithCircle(CircledShape A, CircledShape B)
    {
        double delX = A.centre.x - B.centre.x;
        double delY = A.centre.y - B.centre.y;
        double distance = Math.sqrt(delX * delX + delY * delY);
        double targetRadius = B.radius;
        double thisRadius = A.radius;
        return distance < targetRadius + thisRadius;
    }

    public static ArrayList<Point> getCollidePoints(CircledShape A, CircledShape B)
    {
        ArrayList<Point> points = new ArrayList<>();

        double a = A.centre.x;
        double b = A.centre.y;
        double c = B.centre.x;
        double d = B.centre.y;
        double r1 = A.radius;
        double r2 = B.radius;

        if (abs(a - c) > 0.01)
        {

        } else if (abs(b - d) > 0.01)
        {

        }

        return points;
    }

    public static boolean CircleCollideWithRectangle(CircledShape A, SpecialRectangleShape B)
    {
        CircleRectangleCollideDirection theDirection = Shape.CircleCollideWithRectangleFromDirection(A, B);
        return theDirection != CircleRectangleCollideDirection.NO;
    }

    public static CircleRectangleCollideDirection CircleCollideWithRectangleFromDirection(CircledShape A, SpecialRectangleShape B)
    {
        double a = A.centre.x;
        double b = A.centre.y;
        double r = A.radius;
        double x = B.left;
        double y = B.top;
        double w = B.width;
        double h = B.height;

        if (abs(y - b) <= r && x <= a && a <= x + w)
        {
            return CircleRectangleCollideDirection.FROM_TOP; //A collide from top to the B
        }
        if (abs(x - a) <= r && y <= b && b <= y + h)
        {
            return CircleRectangleCollideDirection.FROM_LEFT; //A collide from left to the B
        }
        if (abs(y + h - b) <= r && x <= a && a <= x + w)
        {
            return CircleRectangleCollideDirection.FROM_BOTTOM; //A collide from bottom to the B
        }
        if (abs(x + w - a) <= r && y <= b && b <= y + h)
        {
            return CircleRectangleCollideDirection.FROM_RIGHT; //A collide from right to the B
        }
        if ((x <= a) && (a <= x + w) && (y <= b) && (b <= y + h))
        {
            return CircleRectangleCollideDirection.FROM_INSIDE; //A is inside of B
        }
        if ((a <= x) && (b <= y) && (r * r >= (a - x) * (a - x) + (b - y) * (b - y))) //A collide from top left
        {
            return CircleRectangleCollideDirection.FROM_TOP_LEFT;
        }
        if ((a <= x) && (b >= y + h) && (r * r >= (a - x) * (a - x) + (b - y - h) * (b - y - h))) //A collide from bottom left
        {
            return CircleRectangleCollideDirection.FROM_BOTTOM_LEFT;
        }
        if ((a >= x + w) && (b <= y) && (r * r >= (a - x - w) * (a - x - w) + (b - y) * (b - y))) //A collide from top right
        {
            return CircleRectangleCollideDirection.FROM_TOP_RIGHT;
        }
        if ((a >= x + w) && (b >= y + h) && (r * r >= (a - x - w) * (a - x - w) + (b - y - h) * (b - y - h))) //A collide from bottom right
        {
            return CircleRectangleCollideDirection.FROM_BOTTOM_RIGHT;
        }
        return CircleRectangleCollideDirection.NO;
    }

    public static boolean RectangleCollideWithCircle(SpecialRectangleShape A, CircledShape B)
    {
        return CircleCollideWithRectangle(B, A);
    }

    public static boolean RectangleCollideWithRectangle(SpecialRectangleShape A, SpecialRectangleShape B)
    {
        return A.left < B.left + B.width && A.left + A.width > B.left && A.top < B.top + B.height && A.top + A.height > B.top;
    }

    public static void printCode(CircleRectangleCollideDirection theDirection)
    {
        if (theDirection == CircleRectangleCollideDirection.FROM_TOP)
        {
            System.out.println("FROM_TOP");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_LEFT)
        {
            System.out.println("FROM_LEFT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_BOTTOM)
        {
            System.out.println("FROM_BOTTOM");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_RIGHT)
        {
            System.out.println("FROM_RIGHT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_TOP_LEFT)
        {
            System.out.println("FROM_TOP_LEFT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_TOP_RIGHT)
        {
            System.out.println("FROM_TOP_RIGHT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_BOTTOM_LEFT)
        {
            System.out.println("FROM_BOTTOM_LEFT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_BOTTOM_RIGHT)
        {
            System.out.println("FROM_BOTTOM_RIGHT");
        } else if (theDirection == CircleRectangleCollideDirection.FROM_INSIDE)
        {
            System.out.println("FROM_INSIDE");
        } else
        {
            System.out.println("NO");
        }
        //FROM_TOP, FROM_LEFT, FROM_BOTTOM, FROM_RIGHT, FROM_INSIDE, FROM_OUTSIDE, FROM_TOP_LEFT, FROM_BOTTOM_LEFT, FROM_BOTTOM_RIGHT, FROM_TOP_RIGHT, NO
    }
}
