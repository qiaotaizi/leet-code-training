package com.jaiz.study;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 *     插入一个字符
 *     删除一个字符
 *     替换一个字符
 *
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 直接上大佬思路：
 * 复杂问题可以转换为简单问题。
 * 首先对于A和B两个单词进行编辑，使它们成为相同的单词
 * 可以对A/B插入，对A/B删除，对A/B替换
 * 共六种
 * 对A插入等价于对B删除，例如doge删除e得到dog，而dog插入e也得到doge
 * 同理，对A删除等价于对B插入
 * 对A替换等价如对B替换，例如cat替换c为h得到hat，而hat替换h为c得到cat
 * 所以只有三种有效操作
 * 插入A，插入B，替换A。
 * 拿题目中的例子1来说
 * 要将horse转化为ros（定义为操作O）
 * 可以转化为更加简单的将horse转化为ro（定义为操作P）后再对ro插入s，得到ros
 * 设操作P的编辑距离（也就是操作次数）是a
 * 那么操作O的编辑距离一定不超过a+1
 * 递推下去，可以将操作P转化为更加简单的子问题。
 * 设D[i][j]表示将A[0,i]编辑为B[0,j]所需的编辑距离。
 * 那么对于插入A的情况
 * D[i][j]=D[i-1][j]+1
 * 对于插入B的情况
 * D[i][j]=D[i][j-1]+1
 * 对于替换A/B的情况
 * D[i][j]=D[i-1][j-1]+1，特别的，如果A[i]和B[j]本就相同，则不需要指定替换
 * 此时D[i][j]=D[i-1]D[j-1]
 * 因为要取最小的编辑距离
 * 所以D[i][j]=min(D[i-1][j]+1,D[i][j-1]+1,D[i-1][j-1]+1)
 * 或D[i][j]=min(D[i-1][j]+1,D[i][j-1]+1,D[i-1][j-1])
 * A和B均从空字符串递推过来，可得最小编辑距离
 * E 5 4 4 3
 * S 4 3 3 2
 * R 3 2 2 2
 * O 2 2 1 2
 * H 1 1 2 3
 * # 0 1 2 3
 *   # R O S
 */
public class Day20200406 {

    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();

        //存在空串，直接返回非空串的长度
        if (n*m==0){
            return n+m;
        }


        int[][] dp=new int[n+1][m+1];

        //初始化首行首列
        for (int i=0;i<n+1;i++){
            dp[i][0]=i;
        }
        for (int j=0;j<m+1;j++){
            dp[0][j]=j;
        }

        //计算所有DP
        for (int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                int left=dp[i-1][j]+1;
                int down=dp[i][j-1]+1;
                int left_down=dp[i-1][j-1]+1;
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    left_down--;
                }
                dp[i][j]=Math.min(left,Math.min(down,left_down));
            }
        }

        return dp[n][m];
    }

}
