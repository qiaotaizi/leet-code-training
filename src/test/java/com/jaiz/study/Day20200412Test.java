package com.jaiz.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Day20200412Test {

    Day20200412 obj = new Day20200412();

    @Test
    public void test() {


//        double[] coord=obj.intersection(new int[]{0,0},new int[]{1,1},new int[]{1,0},new int[]{0,1});
//        System.out.println(Arrays.toString(coord));
//        System.out.println(Arrays.toString(obj.intersection(new int[]{0,3},new int[]{0,6},new int[]{0,1},new int[]{0,5})));
//        System.out.println(Arrays.toString(obj.intersection(new int[]{-1,0},new int[]{1,1},new int[]{0,0},new int[]{0,1})));

        //[-25,67]
        //[-67,24]
        //[-52,48]
        //[-45,43]
        //System.out.println(Arrays.toString(obj.intersection(new int[]{-25, 67}, new int[]{-67, 24}, new int[]{-52, 48}, new int[]{-45, 43})));
//[0,0]
//[1,-1]
//[0,0]
//[-1,1]
        //System.out.println(Arrays.toString(obj.intersection(new int[]{0, 0}, new int[]{1, -1}, new int[]{0, 0}, new int[]{-1, 1})));


        //[-61,-36]
        //[34,-53]
        //[-2,-6]
        //[-24,-67]
        System.out.println(Arrays.toString(obj.intersection(new int[]{-61, -36}, new int[]{34, -53}, new int[]{-2, -6}, new int[]{-24, -67})));

    }

    @Test
    public void yfTest() {
        System.out.println(-1105576610);
        System.out.println(265012776);
        Day20200412.FS fs = new Day20200412.FS(-1105576610, 265012776);
        System.out.println(fs);
    }

    @Test
    public void gcdTest() {
        Day20200412.FS fs = Day20200412.FS.INFINITY;

        Assert.assertEquals(1, fs.gcd(3, 5));
        Assert.assertEquals(1, fs.gcd(4, 1));
        Assert.assertEquals(2, fs.gcd(4, 2));
        Assert.assertEquals(5, fs.gcd(5, 5));
        Assert.assertEquals(5, fs.gcd(10, 25));
        Assert.assertEquals(2, fs.gcd(10, 12));

        System.out.println(fs.gcd(1105576610, 265012776));
    }
}
