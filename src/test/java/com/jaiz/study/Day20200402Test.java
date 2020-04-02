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


    private Day20200402 obj=new Day20200402();

    @Test
    public void test() {

        int[][] input = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        int[][] want = new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
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

    @Test
    public void playTest() {
        int[][] input = new int[][]{
                {1, 1, 1},
                {0, 0, 1},
                {1, 0, 0},
                {1, 1, 1}
        };

        //持续天数
        int durDays = 20;

        playThisGame(input,durDays);
    }

    private void playThisGame(int[][] input ,int maxDays){
        for (int i = 0; i < maxDays; i++) {
            System.out.println("第" + i + "天");
            arrayPrint(input);
            obj.gameOfLife(input);
        }
    }

}
