package com.jaiz.study;

import org.junit.Test;

import java.util.Arrays;

/**
 * 示例：
 * <p>
 * 输入：
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * 输出：
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 */
public class Day20200402Test {

    @Test
    public void test() {

        int[][] input = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        int[][] want=new int[][]{
                {0,0,0},
                {1,0,1},
                {0,1,1},
                {0,1,0}
        };

        Day20200402 obj = new Day20200402();
        System.out.println("输入");
        arrayPrint(input);
        obj.gameOfLife(input);
        System.out.println("输出");
        arrayPrint(input);
        System.out.println("期望");
        arrayPrint(want);

    }

    private void arrayPrint(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
