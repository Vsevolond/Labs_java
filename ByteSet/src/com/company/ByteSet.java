package com.company;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.io.ByteArrayInputStream;


public class ByteSet implements Iterable {
    private String s;
    public ByteSet(String s) {this.s = s;}
    public Iterator iterator() {return new ByteSetIterator();}
    private class ByteSetIterator implements Iterator {
        private int pos;
        public ByteSetIterator() {pos = 0;}
        public boolean hasNext() {return pos < s.length();}
        public String next() {
            String substr = s.substring(pos, pos + 3);
            pos += 3;
            byte[] bytes = substr.getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            int result = in.read();
            return String.valueOf(result);
        }
    }
}
