package com.jaiz.study;

import org.junit.Test;

public class Day20200409Test {

    Day20200409 obj=new Day20200409();

    @Test
    public void test(){
        obj.generateParenthesis(4).forEach(System.out::println);
    }
}
