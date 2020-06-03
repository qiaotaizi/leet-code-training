package com.jaiz.study;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 *
 *
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 * 线代上矩阵旋转90°的一般方法为
 * 先将矩阵上下或左右翻转
 * 再将矩阵沿左上右下（逆时针）或右上左下（顺时针）对角线翻转
 *
 *
 */
public class Day20200407 {

    public void rotate(int[][] matrix) {
        //先将矩阵上下翻转
        int n=matrix.length;//矩阵边长
        for (int x=0;x<n/2;x++){
            for (int y=0;y<n;y++){
                swap(matrix,x,y,n-x-1,y);
            }
        }
        //再将矩阵沿左上右下对角线翻转(x>y时，是对角线上方的元素，x<y时，是对角线下方的元素)
        for (int x=0;x<n;x++){
            for (int y=0;y<n;y++){
                if (x>y){
                    swap(matrix,x,y,y,x);
                }
            }
        }
    }

    /**
     * 交换矩阵中两元素的位置
     * @param matrix
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp=matrix[x1][y1];
        matrix[x1][y1]=matrix[x2][y2];
        matrix[x2][y2]=temp;
    }

}
