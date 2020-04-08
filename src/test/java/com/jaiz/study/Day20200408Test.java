package com.jaiz.study;

import org.junit.Assert;
import org.junit.Test;

public class Day20200408Test {

    Day20200408 obj=new Day20200408();

    @Test
    public void sumKTest(){
        Assert.assertEquals(obj.sumK_Test(22),4);
        Assert.assertEquals(obj.sumK_Test(0),0);
        Assert.assertEquals(obj.sumK_Test(1),1);
        Assert.assertEquals(obj.sumK_Test(56),11);
    }

    @Test
    public void mainTest(){
        Assert.assertEquals(obj.movingCount(16,8,4),15);
    }
}
