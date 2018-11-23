/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.math.linearalgebra;

/**
 *
 * @author ricolwang
 */
public class Square extends Matrix
{

    public static Square getTheUnitMatrix(int size)
    {
        Square unit = new Square(size);
        for (int i = 0; i < size; i++)
        {
            unit.update(i, i, 1);
        }
        return unit;
    }

    public static Square getSquare(Matrix m)
    {
        if (m.rows != m.columns) return null;
        Square s = new Square(m.rows);
        for (int i = 0; i < m.rows; i++)
        {
            for (int j = 0; j < m.columns; j++)
            {
                s.update(i, j, m.getValue(i, j));
            }
        }
        return s;
    }

    public Square(int size)
    {
        this(size, size, false);
    }

    public Square(int size, boolean random)
    {
        super(size, size, random);
    }

    public Square(int rows, int columns, boolean random)
    {
        super(rows, columns, random);
    }

    public double getTheDigitalRemainingFor(int row, int column)
    {
        if (rows != columns) return 0;

        Matrix d = new Matrix(rows - 1, columns - 1);

        int r = 0, c = 0;
        for (int i = 0; i < rows; i++)
        {
            if (i == row) continue;
            for (int j = 0; j < columns; j++)
            {
                if (j == column) continue;
                d.update(r, c, data[i][j]);
                c += 1;
            }

            r += 1;
            c = 0;
        }

        return d.calculate();
    }

    public Square getTheAdjMatrix()
    {
        if (rows != columns) return null;

        Square m = new Square(rows);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                m.update(i, j, Math.pow(-1, i + j) * this.getTheDigitalRemainingFor(i, j));
            }
        }

        return m;
    }

    public Square getTheInverseMatrix()
    {
        return Square.getSquare(this.getTheAdjMatrix().times(1.0 / this.calculate()));
    }

    public static void main(String[] args)
    {
        System.out.println("Demo Square...");

        int size = 20;
        Square m = new Square(size, true);
        System.out.println("Matrix:");
        m.show();
        Square inverse = m.getTheInverseMatrix();
        System.out.println("Inverse Matrix:");
        inverse.show();
        System.out.print("Validate: ");
        System.out.println(m.multiply(inverse).isSame(inverse.multiply(m)) ? " OK " : "Error!");
        
        Square a = new Square(size, true);
        Square b = new Square(size, true);
        System.out.println("A =");
        a.show();
        System.out.println("B =");
        b.show();
        System.out.println("A * B =");
        a.multiply(b).show();
        System.out.println("B * A =");
        b.multiply(a).show();
        System.out.println("|A * B| = " + a.multiply(b).calculate());
        System.out.println("|B * A| = " + b.multiply(a).calculate());
        System.out.println("|A| * |B| = " + a.calculate() * b.calculate());
    }

}
