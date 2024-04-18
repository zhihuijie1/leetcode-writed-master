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

    // 暴力解
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // ---------------------- for test ----------------------
    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
