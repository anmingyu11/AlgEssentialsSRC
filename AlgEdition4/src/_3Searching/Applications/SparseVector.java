package _3Searching.Applications;

/******************************************************************************
 *  Compilation:  javac SparseVector.java
 *  Execution:    java SparseVector
 *  Dependencies: StdOut.java
 *
 *  A sparse vector, implementing using a symbol table.
 *
 *  [Not clear we need the instance variable N except for error checking.]
 *
 ******************************************************************************/

import _1Fundamentals.DataAbstraction.Vector;
import base.stdlib.StdOut;

/**
 * The {@code SparseVector} class represents a <em>d</em>-dimensional mathematical vector.
 * Vectors are mutable: their values can be changed after they are created.
 * It includes methods for addition, subtraction,
 * dot product, scalar product, unit vector, and Euclidean norm.
 * <p>
 * The implementation is a symbol table of indices and values for which the vector
 * coordinates are nonzero. This makes it efficient when most of the vector coordindates
 * are zero.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * See also {@link Vector} for an immutable (dense) vector data type.
 * <p>
 * SparseVector类表示d维数学向量。
 * 向量是可变的：它们的值可以在创建后更改。
 * 它包括加法，减法，点积，标量积，单位向量和欧几里德范数的方法。
 * <p>
 * 该实现是矢量坐标非零的索引和值的符号表。 这使得当大多数向量坐标为零时它是有效的。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class SparseVector {

    private int d;                   // dimension
    private ST<Integer, Double> st;  // the vector, represented by index-value pairs

    /**
     * Initializes a d-dimensional zero vector.
     * <p>
     * 初始化d维零向量。
     *
     * @param d the dimension of the vector
     */
    public SparseVector(int d) {
        this.d = d;
        this.st = new ST<>();
    }

    /**
     * Sets the ith coordinate of this vector to the specified value.
     * <p>
     * 将此向量的第i个坐标设置为指定值。
     *
     * @param i     the index
     * @param value the new value
     * @throws IllegalArgumentException unless i is between 0 and d-1
     */
    public void put(int i, double value) {
        if (i < 0 || i >= d) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (value == 0.0) {
            st.delete(i);
        } else {
            st.put(i, value);
        }
    }

    /**
     * Returns the ith coordinate of this vector.
     * <p>
     * 返回此向量的第i个坐标。
     *
     * @param i the index
     * @return the value of the ith coordinate of this vector
     * @throws IllegalArgumentException unless i is between 0 and d-1
     */
    public double get(int i) {
        if (i < 0 || i >= d) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (st.contains(i)) {
            return st.get(i);
        } else {
            return 0.0;
        }
    }

    /**
     * Returns the number of nonzero entries in this vector.
     * <p>
     * 返回此向量中的非零条目数。
     *
     * @return the number of nonzero entries in this vector
     */
    public int nnz() {
        return st.size();
    }

    /**
     * Returns the dimension of this vector.
     * <p>
     * 返回此向量的维度。
     *
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }

    /**
     * Returns the inner product of this vector with the specified vector.
     * <p>
     * 返回指定向量与此向量的内积。
     *
     * @param that the other vector
     * @return the dot product between this vector and that vector
     * @throws IllegalArgumentException if the lengths of the two vectors are not equal
     */
    public double dot(SparseVector that) {
        if (d != that.d) {
            throw new IllegalArgumentException("Vector lengths disagree");
        }
        double sum = 0.0;
        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys()) {
                if (that.st.contains(i)) {
                    sum += this.get(i) * that.get(i);
                }
            }
        } else {
            for (int i : that.st.keys()) {
                if (this.st.contains(i)) {
                    sum += this.get(i) * that.get(i);
                }
            }
        }
        return sum;
    }


    /**
     * Returns the inner product of this vector with the specified array.
     * <p>
     * 返回指定数组与此向量的内积。
     *
     * @param that the array
     * @return the dot product between this vector and that array
     * @throws IllegalArgumentException if the dimensions of the vector and the array are not equal
     */
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys()) {
            sum += that[i] * this.get(i);
        }
        return sum;
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     * <p>
     * 返回此向量的大小。 这也称为L2范数或欧几里德范数。
     *
     * @return the magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the Euclidean norm of this vector.
     * <p>
     * 返回此向量的欧几里德范数。
     *
     * @return the Euclidean norm of this vector
     * @deprecated Replaced by {@link #magnitude()}.
     */
    @Deprecated
    public double norm() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the scalar-vector product of this vector with the specified scalar.
     * <p>
     * 返回标量 alpha 与这个向量的乘积向量.
     *
     * @param alpha the scalar
     * @return the scalar-vector product of this vector with the specified scalar
     */
    public SparseVector scale(double alpha) {
        SparseVector c = new SparseVector(d);
        for (int i : this.st.keys()) {
            c.put(i, alpha * this.get(i));
        }
        return c;
    }

    /**
     * Returns the sum of this vector and the specified vector.
     * <p>
     * 返回此向量和指定向量的和。
     *
     * @param that the vector to add to this vector
     * @return the sum of this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public SparseVector plus(SparseVector that) {
        if (d != that.d) {
            throw new IllegalArgumentException("Vector lengths disagree");
        }
        SparseVector c = new SparseVector(d);
        for (int i : this.st.keys()) {// c = this
            c.put(i, this.get(i));
        }
        for (int i : that.st.keys()) {// c = c + that
            c.put(i, that.get(i) + c.get(i));
        }
        return c;
    }

    /**
     * Returns a string representation of this vector.
     *
     * @return a string representation of this vector, which consists of the
     * the vector entries, separates by commas, enclosed in parentheses
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys()) {
            s.append("(" + i + ", " + st.get(i) + ") ");
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code SparseVector} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SparseVector a = new SparseVector(10);
        SparseVector b = new SparseVector(10);
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a dot b = " + a.dot(b));
        StdOut.println("a + b   = " + a.plus(b));
    }

}