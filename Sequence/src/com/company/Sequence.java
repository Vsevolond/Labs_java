package com.company;


public class Sequence {
    private int[] elem; // элементы последовательности
    private int len; // ее длина
    //private int[] subWithMaxSum;
    //private int lenSub;
    public Sequence(int[] a) { // инициализация через переданный массив
        len = a.length;
        elem = new int[len];
        for (int i = 0; i < len; i++) elem[i] = a[i]; // копирование массива в последовательность
    }
    //public void getSubWithMaxSum() {
    public int[] getSubWithMaxSum() { // функция выделения подпоследовательности с максимальной суммой
        int res = elem[0], sub_l = 0, sub_r = 0, sum = 0, minus_pos = -1, n = len;
        for (int r = 0; r < n; r++) {
            sum += elem[r];
            if (sum > res) {
                res = sum;
                sub_l = minus_pos + 1;
                sub_r = r;
            }
            if (sum < 0) {
                sum = 0;
                minus_pos = r;
            }
        } // Алгоритм Кадана
        //lenSub = sub_r - sub_l + 1;
        //subWithMaxSum = new int[lenSub];
        //arraycopy(elem, 0, subWithMaxSum, sub_l, lenSub);
        int[] ans = new int[sub_r - sub_l + 1]; // массив для подпоследовательности
        for (int i = sub_l; i <= sub_r; i++) ans[i - sub_l] = elem[i];
        return ans;
    }
    public String toString() {
        String res = "";
        for (int x : elem) {
            res += x + " ";
        }
        return res;
    }
}
