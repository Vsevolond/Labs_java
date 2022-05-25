package com.company;

public class Test {

    public static void main(String[] args) {

        int[] ex1 = { 1, 6, 2, 3, -4, 5, 7, -10, 2 }; // исходный массив
	    Sequence seq1 = new Sequence(ex1); // последовательность из массива
        Sequence res1 = new Sequence(seq1.getSubWithMaxSum()); // результат - тоже sequence
        System.out.println(res1);

        int[] ex2 = { 1 };
        Sequence seq2 = new Sequence(ex2);
        Sequence res2 = new Sequence(seq2.getSubWithMaxSum());
        System.out.println(res2);

        int[] ex3 = { 10, -10, 5, 6, -2, 0, 123 };
        Sequence seq3 = new Sequence(ex3);
        Sequence res3 = new Sequence(seq3.getSubWithMaxSum());
        System.out.println(res3);

    }
}
