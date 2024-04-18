package algorithmbasic.leetcode.coding3;

import java.util.*;

/*
 给定三个参数
 二叉树的头节点head，树上某个节点target,正数K从target开始,可以向上走或者向下走
 返回与target的距离是K的所有节点
*/
/*
 思路: 构建所有节点的头节点map，因为原始的二叉树是不可以向上移动的
       图的宽度优先遍历，一层一层的遍历
*/
public class code8_DistanceKNodes {
    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        if (root == null || target == null || K < 0) {
            return null;
        }
        //构建所有节点的头节点map
        HashMap<Node, Node> map = new HashMap<>();
        map.put(root, null);
        createMap(map, root);
        //对target节点进行图的宽度优先遍历
        return BFS(map, target, K);
    }

    //构建头节点map
    public static void createMap(HashMap<Node, Node> map, Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.put(root.left, root);
            createMap(map, root.left);
        }
        if (root.right != null) {
            map.put(root.right, root);
            createMap(map, root.right);
        }
        return;
    }

    //宽度优先遍历 --------------------------great idea 用size来一次处理一层 用level来记录层数
    public static List<Node> BFS(HashMap<Node, Node> map, Node target, int K) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        List<Node> list = new LinkedList<>();
        Node cur = target;
        queue.add(cur);
        set.add(cur);/** ----------------------attation */
        int curLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();//计算size是一种策略,用来一次性计算同一level下的所有节点
            //一个循环就处理一层
            while (size > 0) {
                cur = queue.poll();
                //来到第K层了，就开始收集结果
                if (curLevel == K) {
                    list.add(cur);
                }
                //没来到第K层就往队列里一层一层的加
                if (cur.left != null && !set.contains(cur.left)) {
                    queue.add(cur.left);
                }
                if (cur.right != null && !set.contains(cur.right)) {
                    queue.add(cur.right);
                }
                Node father = map.get(cur);
                if (father != null && !set.contains(father)) {
                    queue.add(father);
                }
                size--;
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return list;
    }



    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }
    }
}

/*
* 给定一个数组ars，代表每个人的能力值
。再给定一个非负数k
如果两个人能力差值正好为k，那么可以凑在一起比赛
一局比赛只有两个人
返回最多可以同时有多少场比赛

* */