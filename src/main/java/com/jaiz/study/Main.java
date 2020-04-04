package com.jaiz.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 贴瓷砖
 * 小红书面试题
 */
public class Main {

    /**
     * 初始graph
     */
    private static int[][] graph = new int[][]{
            {0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0}
    };

    /**
     * 持有的瓷砖数组
     */
    private static int[] tiles = new int[]{5,5,6,2,2,2,3,3,3};

    /**
     * 统计最终方案拼贴瓷砖的位置
     */
    private static Stack<Block> finallyUsedBlocks=new Stack<>();

    /**
     * 记录尚未被贴瓷砖的原值为1的单元格数量
     */
    private static int leftNonZeroCellCount;

    /**
     * 瓷砖拼贴标识位
     * 二进制10
     */
    private static final int TILE_BIT = 1 << 1;

    /**
     * 初值为1且贴了瓷砖标志位
     * 二进制11
     */
    private static final int TILED_BIT = TILE_BIT | 1;

    public static void main(String[] args) {
        //瓷砖数组排序
        Arrays.sort(tiles);
        //统计初始graph中值为1的单元格的数量
        leftNonZeroCellCount = nonZeroCellCount();

        //递归取当前最大的瓷砖开始尝试，是否有任何可行方案
        //有可行方案，输出true，否则输出false
        boolean ok = tryTileFromIndex(tiles.length - 1);

        //输出是否发现了可行方案
        System.out.println(ok);

        //打印方案
        if (ok){
            printUsedTile();
        }
    }

    /**
     * 打印使用的瓷砖
     */
    private static void printUsedTile() {
        System.out.println("瓷砖长度及拼贴位置：");
        while(!finallyUsedBlocks.empty()){
            Block b=finallyUsedBlocks.pop();
            List<Cell> cells=b.cells;
            System.out.print(cells.size()+":");
            for (Cell cell : cells) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    /**
     * 从指定索引位置的瓷砖进行尝试,查找可行方案
     *
     * @param tileIndex
     */
    private static boolean tryTileFromIndex(int tileIndex) {
        if (tileIndex < 0) {
            //已经没有瓷砖可以尝试
            //说明方案不可行
            return false;
        }
        //如果最大瓷砖没有任何可行方案
        //放弃当前最大瓷砖，再从下一块瓷砖开始尝试
        for (; tileIndex >= 0; tileIndex--) {

            //当前瓷砖长度
            int tileLength = tiles[tileIndex];

            //逐单元格遍历，查找所有符合瓷砖长度的Block
            //向该Block拼贴瓷砖
            //遍历方向可以向右或者向下
            for (int rowIndex = 0; rowIndex < graph.length; rowIndex++) {
                int[] row = graph[rowIndex];
                for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                    if (isUntiledCell(rowIndex, columnIndex)) {
                        //发现尚未贴瓷砖的单元格
                        //向右遍历
                        Block b = new Block();
                        b.cells = new ArrayList<>();
                        b.cells.add(new Cell(rowIndex, columnIndex));
                        for (int delta = 1; columnIndex + delta < row.length && delta < tileLength; delta++) {
                            //delta表示增量，向右遍历至graph边界，或者遍历至delta+1==tileLength
                            if (isUntiledCell(rowIndex, columnIndex + delta)) {
                                b.cells.add(new Cell(rowIndex, columnIndex + delta));
                            } else {
                                break;
                            }
                        }
                        if (b.cells.size() == tileLength) {
                            //发现合适的位置
                            //先把瓷砖贴上
                            //检查贴上瓷砖之后，是否已经不存在未贴瓷砖的单元格
                            //然后进入下一瓷砖的尝试
                            b.tileBlock();
                            if (itWorks()) {
                                return true;
                            }
                            if (tryTileFromIndex(tileIndex - 1)) {
                                //发现可行方案
                                return true;
                            } else {
                                //方案不可行
                                //除下瓷砖，继续尝试接下来的方案
                                b.untileBlock();
                            }
                        }

                        //向下遍历
                        //逻辑与向右遍历相似
                        b = new Block();
                        b.cells = new ArrayList<>();
                        b.cells.add(new Cell(rowIndex, columnIndex));
                        for (int delta = 1; rowIndex + delta < graph.length && delta < tileLength; delta++) {
                            //delta表示增量，向下遍历至graph边界，或者遍历至delta+1==tileLength
                            if (isUntiledCell(rowIndex + delta, columnIndex)) {
                                b.cells.add(new Cell(rowIndex + delta, columnIndex));
                            } else {
                                break;
                            }
                        }
                        if (b.cells.size() == tileLength) {
                            //发现合适的位置
                            //先把瓷砖贴上，然后进入下一瓷砖的尝试
                            //检查贴上瓷砖之后，是否已经不存在未贴瓷砖的单元格
                            //然后进入下一瓷砖的尝试
                            b.tileBlock();
                            if (itWorks()) {
                                return true;
                            }
                            if (tryTileFromIndex(tileIndex - 1)) {
                                //发现可行方案
                                return true;
                            } else {
                                //方案不可行
                                //除下瓷砖，继续尝试接下来的方案
                                b.untileBlock();
                            }
                        }
                    }
                }
            }
        }

        //已经尝试了所有的方案，都不可行
        return false;
    }

    /**
     * 判断方案可行
     *
     * @return
     */
    private static boolean itWorks() {
        return leftNonZeroCellCount == 0;
    }

    /**
     * 计算图中值为1的单元格数量
     *
     * @return
     */
    private static int nonZeroCellCount() {
        int result = 0;
        for (int rowIndex = 0; rowIndex < graph.length; rowIndex++) {
            int[] row = graph[rowIndex];
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                if (isNonZeroCell(rowIndex, columnIndex)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 判断graph中指定位置的单元格初值是否为1
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private static boolean isNonZeroCell(int rowIndex, int columnIndex) {
        return (graph[rowIndex][columnIndex] & 1) == 1;
    }

    /**
     * 判断graph中指定单元格初值是1且未贴瓷砖
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private static boolean isUntiledCell(int rowIndex, int columnIndex) {
        return (graph[rowIndex][columnIndex] & TILED_BIT) == 1;
    }

    /**
     * 连续的单元格块
     */
    private static class Block {
        private List<Cell> cells;

        /**
         * 向graph指定位置拼贴瓷砖
         */
        void tileBlock() {
            for (Cell cell : cells) {
                cell.tile();
            }
            finallyUsedBlocks.push(this);
        }

        /**
         * 从graph指定位置除下瓷砖
         */
        void untileBlock() {
            for (Cell cell : cells) {
                cell.untile();
            }
            finallyUsedBlocks.pop();
        }
    }

    /**
     * 单元格
     */
    private static class Cell {
        private int x;
        private int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }

        /**
         * 在指定单元格贴上瓷砖
         * 并且控制剩余的未贴瓷砖数量-1
         */
        void tile() {
            graph[x][y] |= TILE_BIT;
            leftNonZeroCellCount--;
        }

        /**
         * 在指定单元格除下瓷砖
         * 并且控制剩余的未贴瓷砖数量+1
         */
        void untile() {
            graph[x][y] &= 1;
            leftNonZeroCellCount++;
        }
    }

}
