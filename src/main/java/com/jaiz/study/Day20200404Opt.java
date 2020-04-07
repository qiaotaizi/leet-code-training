package com.jaiz.study;

/**
 * 接雨水问题大佬算法
 * 使用两个指针分别从数组的两头进行遍历
 * 当左指针高于右指针
 * 右指针向左移动，计算所有比自己低的柱子上的存水量
 * 当右指针高于左指针
 * 左指针移动，计算所有比自己低的柱子上的存水量
 * 知道左右指针相遇，遍历终止
 */
public class Day20200404Opt {

    public int trap(int[] height) {
        if (height==null || height.length==0){
            return 0;
        }
        int leftPointer=0;
        int rightPointer=height.length-1;
        int maxLeftValue=height[leftPointer];
        int maxRightValue=height[rightPointer];
        int result=0;
        while(leftPointer!=rightPointer){
            int leftValue=height[leftPointer];
            int rightValue=height[rightPointer];
            if (leftValue>=rightValue){
                //左高右低
                //右指针左移
                rightPointer--;
                int nextRightValue=height[rightPointer];
                maxRightValue=Math.max(maxRightValue,nextRightValue);
                result+=maxRightValue-nextRightValue;
            }else{
                //右高左低
                //左指针右移
                leftPointer++;
                int nextLeftValue=height[leftPointer];
                maxLeftValue=Math.max(maxLeftValue,nextLeftValue);
                result+=maxLeftValue-nextLeftValue;
            }
        }
        return result;
    }

}
