package com.jaiz.study;

import org.junit.Test;

public class Day20200405Test {

    Day20200405.LFUCache cache=new Day20200405.LFUCache(2);

    @Test
    public void test(){
        cache.put(1,1);
        System.out.println(cache);
//        cache.put(2,2);
//        System.out.println(cache);
//        System.out.println(cache.get(1));
//        System.out.println(cache);
//        cache.put(3,3);
//        System.out.println(cache);
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));
//        cache.put(4,4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
    }

}
