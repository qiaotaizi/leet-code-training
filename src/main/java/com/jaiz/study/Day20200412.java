package com.jaiz.study;

/**
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 * <p>
 * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * line1 = {0, 0}, {1, 0}
 * line2 = {1, 1}, {0, -1}
 * 输出： {0.5, 0}
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * line1 = {0, 0}, {3, 3}
 * line2 = {1, 1}, {2, 2}
 * 输出： {1, 1}
 * <p>
 * 示例 3：
 * <p>
 * 输入：
 * line1 = {0, 0}, {1, 1}
 * line2 = {1, 0}, {2, 1}
 * 输出： {}，两条线段没有交点
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 坐标绝对值不会超过 2^7
 * 输入的坐标均是有效的二维坐标
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 两个线段所属直线的表达式分别设为
 * y=a1*x+b1
 * y=a2*x+b2
 * 对于线性函数(y=a*x+b)，已知直线的两点(x1,y1),(x2,y2)
 * 带入函数可得
 * y1=a*x1+b
 * y2=a*x2+b
 * 容易解得
 * a=(y2-y1)/(x2-x1)
 * b=(y2(x2-x1)-x2(y2-y1))/(x2-x1)
 * 注意当x1=x2时，有线性函数y=b (b=x1=x2)
 * <p>
 * 又由两线性函数求得交点的x值为
 * (b2-b1)/(a1-a2)
 * 设属于直线1的两点坐标分别为(x1,y1),(x2,y2)
 * 属于直线2的两点坐标分别为(x3,y3),(x4,y4)
 * 分类讨论：
 * 1.a1=a2且b1=b2，两线段是同一直线的两部分，
 * 如果x属于[x1,x2]且属于[x3,x4]
 * 取min(x1,x3)后求得坐标
 * 2.a1=a2但b1!=b2，两线段所属的直线平行，不存在交点
 * 3.a1!=a2，两线段所属直线一定存在交点，求出交点x值，
 * 若x属于[x1,x2]且属于[x3,x4]，取min(x1,x3)后求得坐标
 */
public class Day20200412 {

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {

        int x1 = start1[0];
        int y1 = start1[1];
        int x2 = end1[0];
        int y2 = end1[1];

        int x3 = start2[0];
        int y3 = start2[1];
        int x4 = end2[0];
        int y4 = end2[1];

        // * a=(y2-y1)/(x2-x1)
        // * b=(y2(x2-x1)-x2(y2-y1))/(x2-x1)
        FS a1=resolveA(x1,y1,x2,y2);
        FS b1=resolveB(x1,y1,x2,y2);

        FS a2=resolveA(x3,y3,x4,y4);
        FS b2=resolveB(x3,y3,x4,y4);

        if (a1.equals(a2)){
            if (b1.equals(b2)){
                //同一直线的两部分
                if (a1.isInfinity()){
                    //斜率为正无穷的情况
                    if (y3>=Math.min(y1,y2) && y3<=Math.max(y1,y2)){
                        return coord(x1,y3);
                    }else if(y1>=Math.min(y3,y4) && y1<=Math.max(y3,y4)){
                        return coord(x1,y1);
                    }else{
                        return emptySet();
                    }
                }
                if (x3>=Math.min(x1,x2) && x3<=Math.max(x1,x2)){
                    return coord(x3,resolveY(a1,b1,x3));
                }else if(x1>=Math.min(x3,x4) && x1<=Math.max(x3,x4)){
                    return coord(x1,resolveY(a1,b1,x1));
                }else{
                    return emptySet();
                }
            }else{
                //平行直线无交点
                return emptySet();
            }

        }else{
            //两直线不平行必有交点
            //对Infinity斜率特殊处理
            if (a1.isInfinity()){
                return coord(x1,resolveY(a2,b2,x1));
            }else if(a2.isInfinity()){
                return coord(x3,resolveY(a1,b1,x3));
            }
            FS x=resolveJDX(a1,b1,a2,b2);
            if (x.between(x1,x2) && x.between(x3,x4)){
                //有交点
                FS y=resolveY(a1,b1,x);
                return coord(x,y);
            }else{
                //没有交点
                return emptySet();
            }
        }
    }

    private double[] emptySet(){
        return new double[]{};
    }
    private double[] coord(FS x,FS y){
        return rootCoord(x.exact(),y.exact());
    }
    private double[] coord(int x,FS y){
        return rootCoord(x,y.exact());
    }
    private double[] coord(int x,int y){
        return rootCoord(x,y);
    }
    private double[] rootCoord(double x,double y){
        return new double[]{x,y};
    }

    /**
     * 求线性函数的a值
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private FS resolveA(int x1,int y1,int x2,int y2){
        // * a=(y2-y1)/(x2-x1)
        FS a;
        if (x2 == x1) {
            a=FS.INFINITY;
        }else{
            a=new FS(y2 - y1, x2 - x1);
        }
        return a;
    }

    /**
     * 求线性函数的b值
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private FS resolveB(int x1,int y1,int x2,int y2){
        FS b;
        if (x2==x1){
            b = new FS(x1);
        }else{
            b = new FS(y2 * (x2 - x1) - x2 * (y2 - y1), x2 - x1);
        }
        return b;
    }

    /**
     * 求交点X值
     * @return
     */
    public FS resolveJDX(FS a1,FS b1,FS a2,FS b2){
        //(b2-b1)/(a1-a2)
        return b2.minus(b1).divide(a1.minus(a2));
    }

    /**
     * 将x带入线性函数求y值
     * @param a
     * @param b
     * @param x
     * @return
     */
    public FS resolveY(FS a,FS b,FS x){
        //y=ax+b
        return a.multi(x).add(b);
    }

    public FS resolveY(FS a,FS b,int x){
        return resolveY(a,b,FS.fromInt(x));
    }

    /**
     * 分数类，
     * 由于题目中给出的点都是整数，
     * 使用int类型表示分子和分母
     */
    public static class FS {

        public static FS fromInt(int i){
            return new FS(i);
        }

        public FS add(FS adder){
            return new FS(this.fz*adder.fm+adder.fz*this.fm,this.fm*adder.fm);
        }

        public FS minus(FS js){
            return new FS(this.fz*js.fm-js.fz*this.fm,this.fm*js.fm);
        }

        public FS multi(FS cs){
            return new FS(this.fz*cs.fz,this.fm*cs.fm);
        }

        public FS divide(FS cs){
            return new FS(this.fz*cs.fm,this.fm*cs.fz);
        }

        /**
         * 判断是否比一个整数大
         * @param i
         * @return
         */
        public boolean ge(int i){
            return this.fz>=i*this.fm;
        }

        /**
         * 判断是否比一个整数小
         * @param i
         * @return
         */
        public boolean le(int i){
            return this.fz<=i*this.fm;
        }

        /**
         * 判断是否介于一个区间
         * @param left
         * @param right
         * @return
         */
        public boolean between(int left,int right){
            return ge(right) && le(left) || ge(left) && le(right);
        }


        public double exact(){
            return ((double)this.fz)/this.fm;
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof FS)){
                return false;
            }
            FS other=(FS)obj;
            return this.fm==other.fm && this.fz==other.fz;
        }

        public static final FS INFINITY=infinity();

        public boolean isInfinity(){
            return this.fm==0;
        }

        private static FS infinity() {
            FS fs=new FS(0);
            fs.fm=0;
            return fs;
        }


        /**
         * 分子
         */
        private long fz;
        /**
         * 分母
         */
        private long fm;


        public FS(long fz) {
            this.fz = fz;
            this.fm = 1;
        }

        public FS(long fz, long fm) {
            this.fz = fz;
            this.fm = fm;
            if (this.fm<0){
                this.fm*=-1;
                this.fz*=-1;
            }
            //主动约分
            yf();
        }

        public String toString() {
            return fz + "/" + fm;
        }

        /**
         * 约分
         */
        public void yf() {
            if (isInfinity()){
                this.fz=INFINITY.fz;
                return;
            }
            if (this.fz==0){
                this.fm=1;
                return;
            }
            long maxGYS = gcd(Math.abs(this.fz), Math.abs(this.fm));
            this.fz /= maxGYS;
            this.fm /= maxGYS;
        }

        /**
         * 求两自然数的最大公约数
         *
         * @param num1
         * @param num2
         * @return
         */
        public long gcd(long num1, long num2) {
            long max=Math.max(num1,num2);
            long min=Math.min(num1,num2);
            long mod=max%min;
            while (mod!=0){
                max=min;
                min=mod;
                mod=max%min;
            }
            return min;
        }
    }

}
