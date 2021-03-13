package Algorithms.week1.percolation;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Fibonacci {
    static ArrayList<Integer> Fn = new ArrayList<Integer>();

    public static long F(int N) {
        if (N == 0) {
            if (Fn.size() == 0) {
                Fn.add(0);
            }
            return Fn.get(0);
        }
        if (N == 1) {
            if (Fn.size() == 1) {
                Fn.add(1);
            }
            return Fn.get(1);
        }
        Fn.add(Fn.get(N - 1) + Fn.get(N - 2));
        return Fn.get(N);
    }

    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N));
    }
}
