package com.jaiz.study;

import org.junit.Test;

import java.util.Arrays;

public class Day20200404Test {

    private Day20200404 obj=new Day20200404();
    private Day20200404Opt optObj=new Day20200404Opt();

    @Test
    public void test(){
        Cases<int[],Integer> cases=new Cases<>();
        cases.add(new int[]{0,1,0,2,1,0,1,3,2,1,2,1},6);
        cases.add(new int[]{4,2,3},1);
        cases.add(new int[]{5,4,1,2},1);
        cases.add(new int[]{9,6,8,8,5,6,3},3);
        cases.add(new int[]{8,5,4,1,8,9,3,0,0},14);
        cases.add(new int[]{},0);

        cases.run(obj::trap,
                Arrays::toString,
                Utils::intToString);

    }

    @Test
    public void optTest(){
        Cases<int[],Integer> cases=new Cases<>();
        cases.add(new int[]{0,1,0,2,1,0,1,3,2,1,2,1},6);
        cases.add(new int[]{4,2,3},1);
        cases.add(new int[]{5,4,1,2},1);
        cases.add(new int[]{9,6,8,8,5,6,3},3);
        cases.add(new int[]{8,5,4,1,8,9,3,0,0},14);

        cases.run(optObj::trap,
                Arrays::toString,
                Utils::intToString);
    }
}
