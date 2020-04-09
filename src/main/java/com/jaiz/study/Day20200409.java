package com.jaiz.study;

import java.util.ArrayList;
import java.util.List;

/**
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 * 输入的值为n
 * 那么需要输出长度为2n的字符串的集合。
 * 创建一个长度为2n的字符数组
 * 递归访问数组的每一位
 * 在这一位上设置(或者)
 * 每层递归检查有效性：
 * 1.左括号数量是否超过n
 * 2.右括号数量是否超过左括号数量
 * 3.左右括号数量之和大于2n
 * 有效性判定失败则停止向下递归
 * 当数组被填满时，停止递归，并且收集结果
 */
public class Day20200409 {

    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<>();
        char[] buf=new char[2*n];
        fill(buf,0,result,0,0,n);
        return result;
    }

    /**
     * 递归填充buf
     * @param buf 字符数组
     * @param pos 待填充位置
     * @param result 递归完毕的字符串输出集合
     * @param leftCount 左括号数量
     * @param rightCount 右括号数量
     * @param n 输入的n值
     */
    private void fill(char[] buf, int pos, List<String> result, int leftCount, int rightCount, int n) {
        //非有效括号，停止递归
        if (invalid(leftCount,rightCount,n)){
            return;
        }
        //找到一种有效结果，纳入集合，停止递归
        if (pos==2*n){
            result.add(new String(buf));
            return;
        }

        //尝试在pos位置写入左括号
        buf[pos]='(';
        fill(buf,pos+1,result,leftCount+1,rightCount,n);
        //尝试在pos位置写入右括号
        buf[pos]=')';
        fill(buf,pos+1,result,leftCount,rightCount+1,n);
    }

    /**
     * 判定有效性
     * @param leftCount
     * @param rightCount
     * @param n
     * @return
     */
    private boolean invalid(int leftCount, int rightCount, int n) {
        return leftCount>n || rightCount>leftCount || leftCount+rightCount>2*n;
    }

}
