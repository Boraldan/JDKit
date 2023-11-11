package org.example;

public class First<T> {
    public static final First FIRST = new First<>();

    public static <T> First<T> getFirst() {
        return (First<T>) FIRST;
    }

    public  First<T> getFirst2() {
        return new First<>();
    }
}
