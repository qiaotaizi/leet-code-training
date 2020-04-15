package com.jaiz.study;

import org.junit.Test;

public class Day20200415Test {

    Day20200415 obj = new Day20200415();

    @Test
    public void test() {
//        int[][] matrix = {{0, 0, 0},
//                {0, 1, 0},
//                {0, 0, 0}};
//        Utils.printArr(obj.updateMatrix(matrix));
        int[][] matrix = new int[][]
                {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}}
        ;
        Utils.printArr(matrix);
        System.out.println("-------");
        Utils.printArr(obj.updateMatrix(matrix));

    }
}
