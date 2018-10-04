/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.math.linearalgebra;

import au.com.rmit.math.common.MathConsts;
import java.util.Random;

/**
 *
 * @author ricolwang
 */
public class Matrix
{

    private double[][] data =
    {
    };
    int rows = 0;
    int columns = 0;
    private final Random theRandom = new Random();

    public Matrix(int rows, int columns, boolean random)
    {
        this.rows = rows;
        this.columns = columns;
        data = buildMatrix(rows, columns, random, true);
    }

    public Matrix(int rows, int columns, boolean random, boolean nonzero)
    {
        this.rows = rows;
        this.columns = columns;
        data = buildMatrix(rows, columns, random, nonzero);
    }

    public Matrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        data = buildMatrix(rows, columns, false, true);
    }

    public double getValue(int row, int column)
    {
        return data[row][column];
    }

    public void update(int row, int column, double value)
    {
        data[row][column] = value;
    }

    private double[][] buildMatrix(int rows, int columns, boolean random, boolean nonzero)
    {
        double[][] m = new double[rows][columns];

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                m[r][c] = (random ? (nonzero ? theRandom.nextDouble() : (theRandom.nextDouble() > 0.7 ? 0 : theRandom.nextDouble())) : 0);
            }
        }

        return m;
    }

    public boolean isSquare()
    {
        return rows == columns;
    }

    public void show()
    {
        System.out.printf("----------------%d * %d-------------\n", rows, columns);

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                System.out.printf("%.2f\t", data[r][c]);
            }
            System.out.println();
        }
    }

    public double calculate()
    {
        if (rows != columns) return 0;

        double result = 0;

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                result += Math.pow(data[r][c], Math.pow(-1, r + c));
            }
        }

        return result;
    }

    public Matrix getRevertMatrix()
    {
        Matrix m = new Matrix(columns, rows);

        for (int c = 0; c < columns; c++)
        {
            for (int r = 0; r < rows; r++)
            {
                m.update(c, r, data[r][c]);
            }
        }

        return m;
    }

    public boolean isSameSize(Matrix m)
    {
        return m.rows == rows && m.columns == columns;
    }

    public Matrix plus(Matrix m)
    {
        if (!this.isSameSize(m)) return null;

        Matrix n = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                n.update(r, c, data[r][c] + m.getValue(r, c));
            }
        }

        return n;
    }

    public Matrix minus(Matrix m)
    {
        return this.plus(m.getNegativeMatrix());
    }

    public Matrix getNegativeMatrix()
    {
        Matrix n = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                n.update(r, c, -data[r][c]);
            }
        }

        return n;
    }

    public Matrix multiply(Matrix m)
    {
        if (columns != m.rows) return null;

        Matrix n = new Matrix(rows, m.columns);

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < m.columns; c++)
            {
                double value = 0;
                for (int i = 0; i < columns; i++)
                {
                    value += data[r][i] * m.getValue(i, c);
                }
                n.update(r, c, value);
            }
        }

        return n;
    }

    public Matrix getRemainingMatrix(int row, int column)
    {
        int m_rows = rows - 1;
        int m_columns = columns - 1;
        int m_r = 0;
        int m_c = 0;
        Matrix m = new Matrix(m_rows, m_columns);

        for (int r = 0; r < rows; r++)
        {
            if (r == row) continue;

            for (int c = 0; c < columns; c++)
            {
                if (c == column) continue;

                m.update(m_r, m_c, data[r][c]);
                m_c += 1;
                if (m_c >= m_columns)
                {
                    m_c = 0;
                    m_r += 1;
                }
            }
        }

        return m;
    }

    public boolean isSimplified()
    {
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                if (data[r][c] != 0 && c < r) return false;
                if (data[r][c] != 1) return false;
            }
        }

        return true;
    }

    // basic row operation
    public boolean row_operate_swap(int i, int j)
    {
        if (i >= rows || j >= rows) return false;

        for (int c = 0; c < columns; c++)
        {
            double tmp = data[i][c];
            data[i][c] = data[j][c];
            data[j][c] = tmp;
        }

        return true;
    }

    public boolean row_operate_multiply(int row, double num)
    {
        if (Math.abs(num) < MathConsts.Minimum || row >= rows) return false;

        for (int c = 0; c < columns; c++)
        {
            data[row][c] *= num;
        }

        return true;
    }

    public boolean row_operate_add(int from, int to, double num)
    {
        if (Math.abs(num) < MathConsts.Minimum || from >= rows || to >= rows) return false;

        for (int c = 0; c < columns; c++)
        {
            data[to][c] += data[from][c] * num;
        }

        return true;
    }

    public Matrix getCopy()
    {
        Matrix m = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                m.update(r, c, data[r][c]);
            }
        }

        return m;
    }

    public boolean row_operate_add(int from, int to)
    {
        return this.row_operate_add(from, to, 1);
    }

    public Matrix getNormalizeMatrix()
    {
        Matrix m = this.getCopy();

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                double value = data[r][c];
                if (value == 0) continue;
                if (value == 1) break;

                for (int column = c; column < columns; column++)
                {
                    double oldValue = m.getValue(r, column);
                    m.update(r, column, oldValue / value);
                }

                break;
            }
        }

        m.all_rows_minus_row(0);

        return m;
    }

    public Matrix getFullNormalizationMatrix()
    {
        int r = 0, c = 0;
        Matrix m = this.getCopy();
        System.out.println("getFullNormalizationMatrix...");
        m.show();
        m.reorder();
        System.out.println("reorder:");
        m.show();

        while (r < rows && c < columns)
        {
            Matrix sub = m.getSubMatrix(r, rows - 1, c, columns - 1);
            Matrix normalize = sub.getNormalizeMatrix();
            m.updateSubMatrix(r, c, normalize);
            
            r += 1;
            c += 1;
        }

        return m;
    }

    private void reorder()
    {
        for (int i = 0; i < rows; i++)
        {
            int k = i;

            for (int j = i + 1; j < rows; j++)
            {
                int compare = this.compareRows(j, k);
                if (compare == -2)
                {
                    System.out.println("Error! row: " + j + " row: " + k);
                } else if (compare == 1)
                {
                    k = j;
                }
            }

            if (k != i)
            {
                this.row_operate_swap(i, k);
            }
        }
    }

    private int compareRows(int from, int to)
    {
        if (from < 0 || to < 0 || from >= rows || to >= rows) return -2;

        for (int c = 0; c < columns; c++)
        {
            if (data[from][c] > data[to][c]) return 1;
            else if (Math.abs(data[from][c] - data[to][c]) < MathConsts.Minimum) continue;
            else return -1;
        }

        return 0;
    }

    private int getFirstNonZeroColumnForRow(int row)
    {
        int column = -1;

        for (int c = 0; c < columns; c++)
        {
            if (data[row][c] == 0) column += 1;
            else break;
        }

        return column;
    }

    private void all_rows_minus_row(int row)
    {
        int start_from_column = this.getFirstNonZeroColumnForRow(row);

        for (int r = 0; r < rows; r++)
        {
            if (r == row) continue;
            if (this.getFirstNonZeroColumnForRow(r) < start_from_column) continue;
            this.row_operate_add(row, r, -1);
        }
    }

    public Matrix getSubMatrix(int from_row, int to_row, int from_column, int to_column)
    {
        if (to_row >= rows) to_row = rows - 1;
        if (to_column >= columns) to_column = columns - 1;
        int m_rows = to_row - from_row + 1;
        int m_columns = to_column - from_column + 1;
        if (m_rows < 0 || m_columns < 0) return null;

        Matrix m = new Matrix(m_rows, m_columns);
        int m_r = 0, m_c = 0;

        for (int r = from_row; r <= to_row; r++)
        {
            for (int c = from_column; c <= to_column; c++)
            {
                m.update(m_r, m_c, data[r][c]);
                m_c += 1;
            }
            m_r += 1;
            m_c = 0;
        }

        return m;
    }

    public boolean updateSubMatrix(int from_row, int from_column, Matrix m)
    {
        int to_row = from_row + m.rows - 1;
        int to_column = from_column + m.columns - 1;
        if (to_row >= rows || to_column >= columns) return false;
        int m_r = 0, m_c = 0;

        for (int r = from_row; r <= to_row; r++)
        {
            for (int c = from_column; c <= to_column; c++)
            {
                data[r][c] = m.getValue(m_r, m_c);
                m_c += 1;
            }
            m_r += 1;
            m_c = 0;
        }

        return true;
    }
}

class JavaMain
{

    public static void main(String[] args)
    {
        System.out.println("Matrix demo...");

        int rows = 20;
        int columns = 23;
        Matrix m = new Matrix(rows, columns, true);
        m.show();
        System.out.println("Calculating..." + m.calculate());
        m.getRevertMatrix().show();

        Matrix m1 = new Matrix(rows, columns, true);
        m1.show();
        Matrix m2 = new Matrix(rows, columns, true);
        m2.show();
        System.out.println("m1 + m2 = ");
        m1.plus(m2).show();
        System.out.println("m1 - m2");
        m1.minus(m2).show();
        Matrix m3 = new Matrix(columns, rows, true);
        m3.show();
        System.out.println("m1 * m3");
        m1.multiply(m3).show();
        m1.getRemainingMatrix(0, 0).show();
        m = new Matrix(rows, columns, true);
        m.show();
        int i = 0, j = rows - 1;
        System.out.println("Swaping " + i + " with " + j);
        m.row_operate_swap(i, j);
        m.show();

        System.out.println("Demo normalization...");
        m = new Matrix(rows, columns, true, false);
        m.show();
        System.out.println("Normalizing...");
        Matrix normalized = m.getFullNormalizationMatrix();
        normalized.show();
        System.out.println("validating..." + (normalized.isSimplified() ? "ok" : "error!"));
    }
}
