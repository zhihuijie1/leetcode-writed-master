package algorithmbasic.leetcode.coding1;

/*
 * 给定两个数组x和hp，长度都是N。
 * x数组一定是有序的，x[i]表示i号怪兽在x轴上的位置
 * hp数组不要求有序，hp[i]表示i号怪兽的血量
 * 为了方便起见，可以认为x数组和hp数组中没有负数。
 * 再给定一个正数range，表示如果法师释放技能的范围长度(直径！)
 * 被打到的每只怪兽损失1点血量。
 * 返回要把所有怪兽血量清空，至少需要释放多少次aoe技能？
 * 三个参数：int[] x, int[] hp, int range
 * 返回：int 次数
 * */
public class AOE {
    //贪心策略 -- 最左边缘以最优的方式变成0(AOE尽可能往右扩)
    //关键点就是：
    //1) 线段树
    //2) 总是用技能的最左边缘刮死最左侧没死的怪兽
    //3) 重复步骤2
    public static int minAoe(int[] x, int[] hp, int range) {
        int n = x.length;
        int[] cover = new int[n];//i位置是最左侧，cover[i]是range范围影响的最右侧的位置。
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r < n && x[r] - x[i] <= range) {
                r++;
            }
            cover[i] = r - 1;
        }
        // x[i]:当前最左侧的位置
        // cover[i]:以x[i]位置为最左侧影响到的最右侧位置
        SegmentTree segmentTree = new SegmentTree(hp);
        segmentTree.build(1, n, 1);
        for (int i = 1; i <= n; i++) {
            int leftHp = segmentTree.query(i, i, 1, n, 1);
            if (leftHp > 0) {

            }
        }
    }

    //实现区间内的“增” “查” “改”
    //时间复杂度控制在logN 关键点在于懒数组
    public static class SegmentTree {
        private int N;//传入数组的长度
        private int[] arr;//对传入数组的备份，从下标1开始计数
        private int[] lazy;
        private int[] sum;

        public SegmentTree(int[] hp) {
            N = hp.length;
            arr = new int[N + 1];
            lazy = new int[N << 2];
            sum = new int[N << 2];
        }

        //线段树的整个操作都是在操作sum数组(用下标构建的虚拟二叉树)，所以在使用之前一定要进行构建
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, (rt << 1) | 1);
            pushUp(rt);
        }

        //在[L,R]范围内(小范围)进行修改
        //[l,r]是整体的大范围
        //rt 根节点
        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                sum[rt] += (R - L + 1) * C;
                lazy[rt] += C;
                return;
            }
            int mid = (L + R) >> 1;
            pushDown(rt, mid - L + 1, R - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, R, rt << 1 | 1);
            }
            sum[rt] = sum[rt >> 1] + sum[rt << 1 | 1];
        }

        public void pushDown(int rt, int lnumber, int rnumber) {
            int left = rt << 1;
            int right = rt << 1 | 1;
            lazy[left] += lazy[rt];
            lazy[right] += lazy[rt];
            sum[left] += lnumber * lazy[rt];
            sum[right] += rnumber * lazy[rt];
            lazy[rt] = 0;
        }

        public int pushUp(int rt) {
            return sum[rt << 1] + sum[rt << 1 | 1];
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(mid, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }
}
