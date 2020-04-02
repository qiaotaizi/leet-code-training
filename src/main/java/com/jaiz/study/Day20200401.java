package com.jaiz.study;

/**
 * 题干：
 * 有效括号字符串 仅由 "(" 和 ")" 构成，并符合下述几个条件之一：
 *
 *     空字符串
 *     连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 *     嵌套，可以记作 (A)，其中 A 是有效括号字符串
 *
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 *
 *     s 为空时，depth("") = 0
 *     s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 *     s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 *
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 *
 *
 *
 * 给你一个有效括号字符串 seq，将其分成两个不相交的子序列 A 和 B，且 A 和 B 满足有效括号字符串的定义（注意：A.length + B.length = seq.length）。
 *
 * 现在，你需要从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。
 *
 * 返回长度为 seq.length 答案数组 answer ，选择 A 还是 B 的编码规则是：如果 seq[i] 是 A 的一部分，那么 answer[i] = 0。否则，answer[i] = 1。即便有多个满足要求的答案存在，你也只需返回 一个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 *
 * 示例 2：
 *
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 *
 *
 *
 * 提示：
 *
 *     1 <= text.size <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 题干的描述比较绕
 * 其实只要括号多级嵌套时，让A组B组交替出现即可，这样深度总是不超过1。
 * 可以用总体深度的奇偶数来判定当前位置属于A还是B
 * 例如：(((())))
 * 分组情况为：A,B,A,B,B,A,B,A
 * 输出结果：[0,1,0,1,1,0,1,0]
 * 最大深度为1
 */
public class Day20200401
{
    public int[] maxDepthAfterSplit(String seq) {

        int depth=0;
        final char LEFT='(';

        int[] result=new int[seq.length()];

        for (int i=0;i<seq.length();i++){
            char charAtI=seq.charAt(i);
            if (charAtI==LEFT){
                depth++;
                result[i]=(depth+1)&1;
            }else{
                result[i]=(depth+1)&1;
                depth--;
            }
        }

        return result;
    }

}
