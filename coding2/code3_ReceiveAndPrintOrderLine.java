package algorithmbasic.leetcode.coding2;


//计算机网络中的包等待问题
//1-A
//3-C
//4-D
//5-E
//上面的包都来了但是不能打印，需要等待2-B来之后，统一打印
//设计这样一个数据结构


//思路：设计两个表，头表与尾表，头表存放链表的头节点，尾表存放链表的尾节点。
//headMap: 2B  6F
//tailMap: 4D  7G
//linkList: 2B-3C-4D     6F-7G   //有了Node会自成链表

import java.util.HashMap;

//进来一个数据n-m，先将其放入头表与尾表中
//然后查尾表表中有没有n-1的尾节点，如果有将新节点加到尾节点的后面，更新尾表
//再查头表中有没有n+1的头节点，如果有将头节点连在当前节点的后面，更新头表。
//此时已经连接起来了
//查看当前这个节点是不是缺失节点 如果是 那就打印然后更新缺失节点
public class code3_ReceiveAndPrintOrderLine {
    //封装一个一个节点，因为后面要用到链表
    public static class Node {
        int id;
        String message;
        Node next;

        public Node(int id, String message) {
            this.id = id;
            this.message = message;
        }
    }

    public static class MessageBox {
        static HashMap<Integer, Node> headMap = new HashMap<>();
        static HashMap<Integer, Node> tailMap = new HashMap<>();
        static int lostPoint = 1; //缺失节点

        public static void receive(int id, String message) {
            //进来一个数据n-m，先将其放入头表与尾表中
            Node cur = new Node(id, message);
            headMap.put(id, cur);
            tailMap.put(id, cur);
            //然后查尾表表中有没有n-1的尾节点，如果有将新节点加到尾节点的后面，更新尾表与头表
            if (tailMap.containsKey(id - 1)) {
                Node tailNode = tailMap.get(id - 1);
                tailNode.next = cur;
                //更新尾表与头表
                tailMap.remove(id - 1);
                headMap.remove(id);
            }

            //再查头表中有没有n+1的头节点，如果有将头节点连在当前节点的后面，更新头表与尾表
            if (headMap.containsKey(id + 1)) {
                Node headNode = headMap.get(id + 1);
                cur.next = headNode;
                //更新头表与尾表
                headMap.remove(id + 1);
                tailMap.remove(id);
            }

            //查看当前这个节点是不是缺失节点 如果是 那就打印然后更新缺失节点
            //缺失节点一定是头节点
            //更新头表与尾表
            if (id == lostPoint) {
                Node lost = headMap.get(lostPoint);
                Node p = lost;
                headMap.remove(lostPoint);
                while (p != null) {
                    //那就打印然后更新缺失节点
                    System.out.println(p.id + " : " + p.message);
                    p = p.next;
                    lostPoint++;
                }
                tailMap.remove(lostPoint - 1);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2, "B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1, "A"); // 1 2 -> print, trigger is 1
        box.receive(4, "D"); // - 4
        box.receive(5, "E"); // - 4 5
        box.receive(7, "G"); // - 4 5 - 7
        box.receive(8, "H"); // - 4 5 - 7 8
        box.receive(6, "F"); // - 4 5 6 7 8
        box.receive(3, "C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9, "I"); // 9 -> print, trigger is 9
        box.receive(10, "J"); // 10 -> print, trigger is 10
        box.receive(12, "L"); // - 12
        box.receive(13, "M"); // - 12 13
        box.receive(11, "K"); // 11 12 13 -> print, trigger is 11
    }
}
