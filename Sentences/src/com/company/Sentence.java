package com.company;

public class Sentence implements Comparable<Sentence> {
    private String str;
    public Sentence(String s) {
        this.str = s;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public boolean isSplit (char x) {
        char []splits = new char[] {' ', ',', '.', '?', '!', ':', ';', '(', ')'};
        boolean f = false;
        for (char s : splits) {
            if (s == x) {
                f = true;
                break;
            }
        }
        return f;
    }

    public int maxLenWord() {
        int m = 0, k = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isSplit(str.charAt(i))) {
                m = max(m, k);
                k = 0;
            } else {
                k++;
            }
        }
        return m;
    }

    public String toString() { return str; }

    public int compareTo(Sentence obj) {
        return this.maxLenWord() - obj.maxLenWord();
    }
}
