package com.jaiz.study;


/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 从(0,0)向外扩散，递归统计所有K值符合的可达的格子
 * 使用最低位表示可达判定标记
 */
public class Day20200408 {

    private static class Counter {
        int value = 0;

        void incr(){
            value++;
        }
    }

    public int movingCount(int m, int n, int k) {
        int[][] matrix = new int[m][n];

        Counter count = new Counter();

        expand(matrix, m, n, 0, 0, count, k);

        return count.value;
    }

    /**
     * 扩散标记可达位置
     *
     * @param matrix
     * @param startM
     * @param startN
     */
    private void expand(int[][] matrix, int m, int n, int startM, int startN, Counter count, int k) {
        //若当前单元格满足以下任一条件：
        //1 已经有可达标记
        //2 K值不满
        //3 数组越界
        //则递归停止
        if (startM < 0 || startN < 0 ||
                startM >= m || startN >= n ||
                isAccessible(matrix, startM, startN) ||
                !KOK(startM, startN, k)
        ) {
            return;
        }
        //给予当前位置可达标记
        accessible(matrix,startM,startN);
        count.incr();
        //向上下左右四个方向递归调用该方法
        expand(matrix,m,n,startM-1,startN,count,k);
        expand(matrix,m,n,startM,startN-1,count,k);
        expand(matrix,m,n,startM+1,startN,count,k);
        expand(matrix,m,n,startM,startN+1,count,k);
    }

    /**
     * 给予单元格可达标记
     */
    private void accessible(int[][] matrix, int rowIndex, int columnIndex) {
        matrix[rowIndex][columnIndex] = 1;
    }

    /**
     * 是否已有可达标记
     *
     * @param matrix
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private boolean isAccessible(int[][] matrix, int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex] == 1;
    }

    /**
     * 判断K值满足
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    private boolean KOK(int m, int n, int k) {
        return sumCellK(m, n) <= k;
    }

    /**
     * 计算单元格K值
     *
     * @param m
     * @param n
     */
    private int sumCellK(int m, int n) {
        return sumK(m) + sumK(n);
    }

    private int sumK(int num) {
        int result = 0;
        for (; num != 0; num /= 10) {
            result += num % 10;
        }
        return result;
    }

    public int sumK_Test(int num) {
        return sumK(num);
    }
}
