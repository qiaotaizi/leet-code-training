package com.jaiz.study;

import org.junit.Test;

public class Day20200413Test {


    private int i=0;

    private int NextI(){
        return i++;
    }

    @Test
    public void incrTest(){
        System.out.println(NextI());
        System.out.println(NextI());
        System.out.println(i);
    }

}
