package com.jaiz.study;

import org.junit.Test;

import java.util.Arrays;

public class Day20200410Test {


    Day20200410 obj=new Day20200410();

    @Test
    public void test(){
        Cases<String,String> cases=new Cases<>();
        cases.add("the sky is blue","blue is sky the");
        cases.add("  hello world!  ","world! hello");
        cases.add("a good   example","example good a");
        cases.add("","");
        cases.run(obj::reverseWords,
                String::toString,
                String::toString);
    }
}
