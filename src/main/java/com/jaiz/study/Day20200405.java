package com.jaiz.study;

import java.util.*;

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2 );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 *
 * https://leetcode-cn.com/problems/lfu-cache/
 *
 * TODO 按照官解进行编码
 */
public class Day20200405 {

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static class LFUCache {

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();

            int maxLength=10;
            int length=0;

            for (Node next=head;next!=null;next=head.next){
                if (head.prev!=null){
                    sb.append("<-");
                }
                sb.append(next.val);
                if (head.next!=null){
                    sb.append("->");
                }
                length++;
                if (length>=maxLength){
                    break;
                }
            }
            return sb.toString();
        }

        private static class Node {
            private int val;
            private Node prev;
            private Node next;

            Node(int val, Node prev, Node next) {
                this.val = val;
                this.prev = prev;
                this.next = next;
            }

            /**
             * 在当前节点之前插入节点
             * @param n
             */
            void addPrev(Node n){
                Node prev=this.prev;
                if (Objects.nonNull(prev)){
                    prev.next=n;
                    n.prev=prev;
                }
                n.next=this;
                this.prev=n;
            }
        }

        private Map<Integer, Node> map;
        private int capacity;
        private Node head;
        private Node tail;
        private int count;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>(capacity);
            this.count=0;
        }

        public int get(int key) {
            Node n = map.get(key);
            if (Objects.isNull(n)) {
                return -1;
            } else {
                updateNodePos(n);
                return n.val;
            }
        }

        /**
         * 更新节点在队列中的位置
         * 将节点提前至队列头，前驱置为null，后继为原头结点
         * 节点原前驱节点和后继节点连接在一起
         *
         * @param n
         */
        private void updateNodePos(Node n) {
            Node prev = n.prev;
            if (prev == null) {
                //本来就是头结点，不需要做任何操作
                return;
            }
            Node next = n.next;
            n.prev = null;
            n.next = head;
            head = n;

            prev.next = next;
            if (Objects.nonNull(next)){
                next.prev = prev;
            }
            if (tail==n){
                //如果当前节点是尾结点
                //修改tail指针
                tail=prev;
            }
        }

        public void put(int key, int value) {
            //检查map中是否存在
            Node n = map.get(key);
            if (Objects.nonNull(n)){
                return;
            }
            //不存在，插入头部
            n=new Node(value,null,head);
            if (Objects.nonNull(head)){
                //修改头结点前驱
                head.prev=n;
            }else{
                tail=n;
            }
            //头结点设为当前节点。
            head=n;
            //持有节点数量加1
            count++;
            map.put(key,n);
            //判断是否超出容量，超出时删除LFU尾部
            if (count>capacity){
                map.remove(tail.val);
                Node tailPrev=tail.prev;
                tail.prev=null;
                tailPrev.next=null;
                tail=tailPrev;
                count--;
            }

        }
    }

}
