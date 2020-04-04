package com.jaiz.study;

import java.util.Arrays;

public class Utils {

    /**
     * 打印一维整型数组
     */
    static void printArr(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 打印二维整型数组
     * @param arr
     */
    static void printArr(int[][] arr){
        for (int[] ints : arr) {
            printArr(ints);
        }
    }

    static String intToString(int i){
        return i+"";
    }

}
