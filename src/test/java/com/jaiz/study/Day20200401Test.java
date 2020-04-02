package com.jaiz.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day20200401Test {

    @Test
    public void test(){
        Map<String,int[]> testMap=new HashMap<>();
        testMap.put("()()()()",new int[]{0,0,0,0,0,0,0,0});
        testMap.put("()",new int[]{0,0});
        testMap.put("(())",new int[]{0,1,1,0});
        testMap.put("(()())",new int[]{0,1,1,1,1,0});
        testMap.put("(((())))",new int[]{0,1,0,1,1,0,1,0});
        testMap.put("(()())(()(()))",new int[]{0,1,1,1,1,0,0,1,1,1,0,0,1,0});

        Day20200401 obj=new Day20200401();

        testMap.forEach((key,value)->{
            int[] got=obj.maxDepthAfterSplit(key);
            boolean equals=Arrays.equals(got,value);
            Assert.assertTrue("key="+key+",got "+Arrays.toString(got)+",but want "+Arrays.toString(value),equals);

        });
    }



}
