package algorithmbasic.leetcode.coding3;

import java.util.Arrays;

// 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
// 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
// 返回最多可以同时有多少场比赛

/*
思路：贪心方法 -- 1 3 5 7  假设 K=2 如果中间的两个数进行配对的话，那两边的数是没法进行配对的
            所以由小到大进行配对，小的跟小的配对，中间的跟中间的配对，大的跟大的配对。这样可以满足尽可能多的对数。
            如果先让中间的配对，会导致两边的数不均衡，即小的与大的差值很大。
 */
public class Code04_MaxPairNumber {
    public static int maxPairNum(int[] arr, int k) {
        //先对数组进行由小到大的排序
        Arrays.sort(arr);
        //arr数组有序
        int N = arr.length;
        int ans = 0;// 收集结果
        int L = 0;//a b是滑动窗口
        int R = 0;
        boolean[] used = new boolean[N];

        while (L < N && R < N) {
            if (L == R) {
                R++;
            } else if (used[L]) { /**--------------- great idea 我最需要的简洁的思路就是后面 LR都未用过，且R > L 此时将used放到前面写非常清晰*/
                L++;
            } else { //此时L < R && L没有用过 && R没有用过 (R一定是没有用过的)
                if (arr[R] - arr[L] == k) {
                    ans++;
                    used[R] = true;
                    used[L] = true;//其实这个地方无需处理，因为L以后不会来到这里了
                    L++;
                    R++;
                } else if (arr[R] - arr[L] < k) {
                    //保证先满足小的
                    R++;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }
}
