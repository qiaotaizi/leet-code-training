package com.jaiz.study;

import com.sun.tools.internal.xjc.AbortException;

import java.util.Arrays;

/**
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * <p>
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * <p>
 * <p>
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
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 克隆数组
 * 对原数组每个格子中的结果依照克隆数组的数值进行判定
 * 更新原数组中的值
 * <p>
 * 这个思路无法实现原地算法。另外活细胞侵占面板边界会造成什么问题？
 * <p>
 * 原地算法实现原理：
 * 因为表示细胞的死活只有0和1，一个bit位就足够了，对于int类型，还有有31个位可以使用
 * 可以使用最低表示细胞当前的死活，次低位表示细胞下个周期的死活，
 * 所有细胞判断完毕后，二维数组中的所有数值做无符号右移一位即可
 */
public class Day20200402 {

    public void gameOfLife(int[][] board) {
        //逐单元格对下个周期的状态进行判断
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            int[] row = board[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                deadOrLife(board, rowIndex, columnIndex, row.length, board.length);
            }
        }

        //判断完毕，对所有元素进行位移操作
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            int[] row = board[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                board[rowIndex][columnIndex] >>>= 1;
            }
        }
    }

    /**
     * 判定单元格细胞的生死
     *
     * @param arr
     * @param rowIndex
     * @param columnIndex
     * @param maxColumnNum 列数
     * @param maxRowNum    行数
     * @return
     */
    private void deadOrLife(int[][] arr, int rowIndex, int columnIndex, int maxColumnNum, int maxRowNum) {
        //统计周边存活细胞数，当统计数量大于4时即可停止
        int livingCellCount = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //单元格本身不考虑
                if (i == 0 && j == 0) {
                    continue;
                }
                //防越界
                int rowIndex_ = rowIndex + i;
                if (rowIndex_ < 0 || rowIndex_ >= maxRowNum) {
                    continue;
                }
                int columnIndex_ = columnIndex + j;
                if (columnIndex_ < 0 || columnIndex_ >= maxColumnNum) {
                    continue;
                }

                //使用最低位记录当前单元格细胞的死活
                livingCellCount += arr[rowIndex_][columnIndex_] & 1;
                if (livingCellCount > 4) {
                    break;
                }
            }
        }

        //判定单元格在下个周期的死活
        //并使用从右数第二位记录这个值
        int cellValue = arr[rowIndex][columnIndex];
        if ((cellValue & 1) == 0) {
            //单元格是死细胞
            //如果死细胞周围正好有三个活细胞，则该位置死细胞复活
            if(livingCellCount == 3){
                arr[rowIndex][columnIndex]=(cellValue | 1<<1);
            }
        } else {
            //单元格是活细胞
            //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活
            //其他情况死亡
            if (livingCellCount == 2 || livingCellCount == 3){
                arr[rowIndex][columnIndex]=(cellValue | 1<<1);
            }
        }
    }

}
