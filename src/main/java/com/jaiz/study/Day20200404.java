package com.jaiz.study;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
 * 在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 解题思路一：
 * 将柱子分层，统计每层有边界的空格位置
 * 累加每层的空格位置，得到可以存储的水量。
 * 这样实现的问题是：O(n^2)时间复杂度，当柱状图非常大
 * 每个柱子都有比较大的深度时，该算法的统计效率是非常低下的。
 * <p>
 * 解题思路二：
 * 依次读取柱子，寻找极大值索引
 * 记为当前左边界，
 * 同时记为当前右边界
 * 继续从当前右边界读取柱子，寻找第二个极大值索引
 * 记为当前右边界
 * 从右边界至左边界，使用较小的极大值减去每个柱子的高度，
 * 得到每个索引上的可存储水量，累计之，
 * 将两个峰值之间所有柱子的高度变成较小极大值的高度。
 * 将两个极大值索引中较大者记为当前左边界
 * 继续从当前右边界读取柱子
 * ...
 * 读取至数组尾部，统计结束
 */
public class Day20200404 {

    public int trap(int[] height) {
        int result = 0;
        int leftLimit = 0;
        int rightLimit;
        for (int i = 0; i < height.length; i++) {
            if (isMaximumIndex(i, height)) {
                //发现极大值
                //记为右边界
                rightLimit = i;
                //统计两边界之间的水量，并且以较小的边界值统一边界之间的高度。
                result += countBetweenLimitsAndUnifyHeight(leftLimit, rightLimit, height);
                //将两边界的高位记为左边界
                leftLimit= higherValueIndex(leftLimit,rightLimit,height);
            }
        }
        return result;
    }

    /**
     * 获取两个索引中值较高者的索引
     * 若两个索引的值相等，返回右侧索引
     * @param leftLimit
     * @param rightLimit
     * @param arr
     * @return
     */
    private int higherValueIndex(int leftLimit, int rightLimit, int[] arr) {
        int leftValue = arr[leftLimit];
        int rightValue = arr[rightLimit];
        return leftValue<=rightValue?rightLimit:leftLimit;
    }

    /**
     * 统计两边界中的存水量，
     * 并且按照较低边界将边界之间的柱子填平
     *
     * @param leftLimit
     * @param rightLimit
     * @param arr
     * @return
     */
    private int countBetweenLimitsAndUnifyHeight(int leftLimit, int rightLimit, int[] arr) {
        int result = 0;
        int leftValue = arr[leftLimit];
        int rightValue = arr[rightLimit];
        int lowerValue = Math.min(leftValue, rightValue);

        for (int i = leftLimit + 1; i < rightLimit; i++) {
            int curValue=arr[i];
            if (curValue<lowerValue){
                result += lowerValue - curValue;
                arr[i] = lowerValue;
            }
        }

        return result;
    }

    /**
     * 判断索引位置是否是极大值
     *
     * @param index 索引，设给定索引不越界
     * @param arr   数组
     * @return 是极大值返回true，否则返回false
     */
    private boolean isMaximumIndex(int index, int[] arr) {
        //若当前索引是数组头，认为其左边柱子高度为0
        int leftValue = (index - 1) < 0 ? 0 : arr[index - 1];
        int midValue = arr[index];
        //向右查找邻值时，可能出现持平情况，一直找到右侧与自身不等的首个索引，再进行比较
        //若当前索引是数组尾，认为其右边柱子高度为0
        boolean isArrTail=index+1>=arr.length;
        int rightValue;
        if (isArrTail){
            rightValue=0;
        }else{
            rightValue=arr[index+1];
            int notSameRightIndex=index+1;
            while (rightValue==midValue){
                notSameRightIndex++;
                if (notSameRightIndex>=arr.length){
                    rightValue=0;
                    break;
                }else{
                    rightValue=arr[notSameRightIndex];
                }
            }
        }

        return midValue > leftValue && midValue > rightValue;
    }

}
