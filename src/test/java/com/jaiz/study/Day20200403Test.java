package com.jaiz.study;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class Day20200403Test {

    Day20200403 obj = new Day20200403();

    @Test
    public void test() {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("42", 42);
        testMap.put(" -42", -42);
        testMap.put("4193 with words", 4193);
        testMap.put("words and 987", 0);
        testMap.put("-91283472332", -2147483648);
        testMap.put("   +0 123", 0);
        testMap.put("   ", 0);
        testMap.forEach((key, value) -> {
            int got = obj.myAtoi(key);
            Assert.assertTrue("str:" + key + " got " + got + ", but want " + value, value == got);
        });
    }

    @Test
    public void digitTest() {
        Assert.assertTrue(9 == Character.digit('9', 10));
    }

    @Test
    public void findFirstNonBlankCharIndexTest(){
        System.out.println(obj.findFirstNonBlankCharIndex("42".toCharArray()));
    }
}