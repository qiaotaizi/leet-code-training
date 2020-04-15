package com.jaiz.study;

/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * 示例 2:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * <p>
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * <p>
 * 注意:
 * <p>
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/01-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 向四个方向递归查找到0的距离
 * 比较四个方向上的距离，取最小值
 * 使用右数第一位表示元素值
 * 使用右数第二位表示是否已经计算过距离
 * 使用右数第三位表示是否被本轮递归遍历到
 * 使用右数第4位到右数第17位共14位空间表示距离值
 * 最后所有位进行右位移
 *
 * TODO 没有完全通过测试用例，可以求助一下官解
 */
public class Day20200415 {

    /**
     * 第二位表示本单元格是否已经得到了距离值
     */
    private final int CAL_BIT = 1 << 1;

    //使用第三位表示本单元格是否已经在本轮被遍历过
    private final int ITER_BIT = 1 << 2;

    /**
     * 已知矩阵元素个数不超过10000，那么距离最大值应为9999，这里使用10000表示遍历到矩阵边界
     * 按照题目条件，不应该有任何一个单元格达到这个值
     */
    private final int LIMIT = 10000;

    private final int UNITER_BIT=(16383<<3)+3;

    public int[][] updateMatrix(int[][] matrix) {
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            int[] row = matrix[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                dist(rowIndex, columnIndex, matrix);
            }
        }
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            int[] row = matrix[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                matrix[rowIndex][columnIndex] >>>= 3;
            }
        }
        return matrix;
    }

    /**
     * @param rowIndex
     * @param columnIndex
     * @param matrix
     * @return
     */
    private int dist(int rowIndex, int columnIndex, int[][] matrix) {
        //检查递归边界
        if (rowIndex < 0 || rowIndex >= matrix.length || columnIndex < 0 || columnIndex >= matrix[0].length) {
            return LIMIT;
        }
        //如果本单元格已经被计算过值，直接返回值
        if (calculated(matrix[rowIndex][columnIndex])) {
            return calculatedDist(matrix[rowIndex][columnIndex]);
        }
        //如果本单元格的值就是0，直接返回0
        if (valueOf(matrix[rowIndex][columnIndex]) == 0) {
            //标记本单元格已经被计算
            markCalculated(rowIndex, columnIndex, matrix);
            return 0;
        }

        //如果本单元格在本轮已经被遍历过，返回LIMIT
        if (iterated(matrix[rowIndex][columnIndex])) {
            return LIMIT;
        }

        //打上被遍历标记
        markIterated(matrix,rowIndex,columnIndex);
        //向四个方向遍历，获取其中的最小值
        int result = Math.min(LIMIT, dist(rowIndex, columnIndex - 1, matrix) + 1);
        result = Math.min(result, dist(rowIndex - 1, columnIndex, matrix) + 1);
        result = Math.min(result, dist(rowIndex, columnIndex + 1, matrix) + 1);
        result = Math.min(result, dist(rowIndex + 1, columnIndex, matrix) + 1);
        recordDist(matrix, rowIndex, columnIndex, result);
        //int afterRecord=matrix[rowIndex][columnIndex];
        //标记本单元格已经被计算
        markCalculated(rowIndex, columnIndex, matrix);
        //int afterMarkCalculated=matrix[rowIndex][columnIndex];
        //移除本轮被遍历标记
        unmarkIterated(matrix,rowIndex,columnIndex);
        //int afterUnmarkIterated=matrix[rowIndex][columnIndex];
        //System.out.println(rowIndex + ":" + columnIndex + "=" + result+"|"+Integer.toString(afterRecord,2)+"|"+Integer.toString(afterMarkCalculated,2)+"|"+Integer.toString(afterUnmarkIterated,2));
        return result;
    }

    /**
     * 移除被遍历标记
     * @param matrix
     * @param rowIndex
     * @param columnIndex
     */
    private void unmarkIterated(int[][] matrix, int rowIndex, int columnIndex) {
        matrix[rowIndex][columnIndex] &= (UNITER_BIT);
    }

    /**
     * 给单元格值打上被遍历标记
     * @param matrix
     * @param rowIndex
     * @param columnIndex
     */
    private void markIterated(int[][] matrix, int rowIndex, int columnIndex) {
        matrix[rowIndex][columnIndex] |= ITER_BIT;
    }

    /**
     * 检查单元格值是否有被遍历标记
     * @param cellValue
     * @return
     */
    private boolean iterated(int cellValue) {
        return (cellValue & ITER_BIT)==ITER_BIT;
    }

    /**
     * 在单元格上记录距离值
     *
     * @param matrix
     * @param rowIndex
     * @param columnIndex
     * @param result
     */
    private void recordDist(int[][] matrix, int rowIndex, int columnIndex, int result) {

        matrix[rowIndex][columnIndex] |= (result << 3);

    }

    /**
     * 获得单元格已经被计算好的距离值
     *
     * @param cellValue
     * @return
     */
    private int calculatedDist(int cellValue) {
        return cellValue >>> 3;
    }

    /**
     * 检查最右位的值
     *
     * @return
     */
    private int valueOf(int cellValue) {
        return cellValue & 1;
    }

    /**
     * 检查单元格第二位是否为1
     * 即是否已经有被计算过的标志
     *
     * @param cellValue
     * @return
     */
    private boolean calculated(int cellValue) {
        return (cellValue & CAL_BIT) == CAL_BIT;
    }

    /**
     * 给单元格打上被计算过的标记
     *
     * @param rowIndex
     * @param columnIndex
     * @param matrix
     * @return
     */
    private void markCalculated(int rowIndex, int columnIndex, int[][] matrix) {
        matrix[rowIndex][columnIndex] |= CAL_BIT;
    }
}
