package org.example.javaexamples;

public class AutoAndUnboxingExample {
    public static void main(String[] args) {
        Integer a = 0;
        int b  = 10;
        a = b; // Autoboxing - is assign primitive datatype to wrapper class
        Integer c = 100;
        b = c; // Unboxing - is way of  assigning wrapper class to its primitive datatype
        System.out.println(a);
        System.out.println(c);

        Integer x  = 127;
        Integer y = 127;
        System.out.println(x==y);

        Integer i = 128;
        Integer j = 128;
        System.out.println(i==j);
    }
}
