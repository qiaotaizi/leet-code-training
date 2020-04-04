package com.jaiz.study;

@FunctionalInterface
public interface Stringer<T> {

    String toString(T src);

}
