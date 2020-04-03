package com.jaiz.study;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时， 则你的函数不需要进行转换，即无法进行有效转换。
 * 
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * 
 * 提示：
 * 
 * 本题中的空白字符只包括空格字符 ' ' 。 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 −
 * 1]。如果数值超过这个范围，请返回 INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: "42" 输出: 42
 * 
 * 示例 2:
 * 
 * 输入: " -42" 输出: -42 解释: 第一个非空白字符为 '-', 它是一个负号。 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到
 * -42 。
 * 
 * 示例 3:
 * 
 * 输入: "4193 with words" 输出: 4193 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 
 * 示例 4:
 * 
 * 输入: "words and 987" 输出: 0 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。 因此无法执行有效的转换。
 * 
 * 示例 5:
 * 
 * 输入: "-91283472332" 输出: -2147483648 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 因此返回
 * INT_MIN (−2^31) 。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * 解题思路： 从头遍历charArr 跳过空格 找到第一个非空格字符 这个字符允许‘+’ ‘-’ ‘0’~‘9’ 出现其他情况直接返回0
 * 如果首个非空字符是正负号，用布尔值记录数值的正负 继续向下找连续的‘0’~‘9’的字符，出现中断则停止遍历。 为正的情况
 * 使用(INT_MAX-b)%10进行比较，判断下次计算是否会越界，越界直接返回最大值 a*10+b进行累加至最有一个连续有效数字字符。 负数情况类似
 */
public class Day20200403 {
    public int myAtoi(String str) {

        // 负数标志
        boolean negFlag = false;
        //是否已经确认了符号
        boolean signDecided = false;
        int result = 0;

        char[] charArr = str.toCharArray();

        //找到第一个非空格字符索引
        int nonBlankCharIndex=findFirstNonBlankCharIndex(charArr);

        // 找到第一个非空格字符
        for (int i = nonBlankCharIndex; i < charArr.length; i++) {
            char c = charArr[i];

            if (!signDecided) {
                // 尚未确定符号
                signDecided = true;
                if (c=='-') {
                    negFlag=true;
                    continue;
                }else if(c=='+'){
                    continue;
                }
            }

            // 一旦遇到任何非数值字符，立刻返回结果
            if (!Character.isDigit(c)) {
                return result;
            }

            int digit = Character.digit(c, 10);
            if (negFlag) {
                // 负数情况
                if (result < (Integer.MIN_VALUE + digit) / 10) {
                    // 越界返回整型最小值
                    return Integer.MIN_VALUE;
                } else {
                    result = result * 10 - digit;
                }
            } else {
                // 正数情况
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return Integer.MAX_VALUE;
                } else {
                    result = result * 10 + digit;
                }
            }
        }
        return result;
    }

    /**
     * 查找第一个非空格字符串的索引
     * 如果全是空格，返回数组的长度
     * @param charArr
     * @return
     */
    public int findFirstNonBlankCharIndex(char[] charArr) {
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i]!=' '){
                return i;
            }
        }
        return charArr.length;
    }
}