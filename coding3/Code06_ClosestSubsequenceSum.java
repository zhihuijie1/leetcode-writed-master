package algorithmbasic.leetcode.coding3;

// 本题测试链接 : https://leetcode.com/problems/closest-subsequence-sum/

/*
 * 给你一个整数数组 nums 和一个整数目标。
 * 您要选择 nums 的一个子序列，使其元素之和尽可能接近目标值。也就是说，如果子序列的元素之和为 sum，那么您要最小化绝对差值 abs(sum - goal)。
 * 返回 abs(sum - goal) 的最小值。
 * 请注意，数组的子序列是删除原数组的部分元素（可能是全部，也可能是全部）后形成的数组。
 *
 * 本题数据量描述:
 * 1 <= nums.length <= 40
 * -10^7 <= nums[i] <= 10^7
 * -10^9 <= goal <= 10^9
 * 通过这个数据量描述可知，需要用到分治，因为数组长度不大
 * 而值很大，用动态规划的话，表会爆
 */

/*
 * 思路：如果直接用动态规划的话goal的规模建表的话直接爆炸了，所以不用dp的方法
 * 会发现数组的长度是很小的，暴力的方法-> 数组当前的数要与不要 --> 复杂度规模是2^40 --> 10^12 超出规模
 * 可以采用分支方法 --> 将数组一分为二，分成左侧小数组20个数，右侧小数组20个数
 * - 左侧小数组当前的数要与不要 --> 复杂度规模是2^20 --> 10^6 未超出规模
 * - 右侧小数组当前的数要与不要 --> 复杂度规模是2^20 --> 10^6 未超出规模
 * - 左右结合 -- 左侧小数组确定一个数之后 去右侧找一个与其最匹配的数进行匹配 --> 2^20 * log (2^20) -->  数据规模 20 * 10^6 未超出数据规模 -- 解释：
 *   左侧有2^20个累加和 右侧也是 但是右侧的数会放进有序表种，有序表种查询时间复杂度是 log (2^20)
 *
 * - 整体思路
 */

import java.util.Arrays;

public class Code06_ClosestSubsequenceSum {
    //创建左右两个数组用来存储左右小数组中的所有累加和结果
    private static int[] left = new int[1 << 20];// 2^20
    private static int[] right = new int[1 << 20];

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int N = nums.length;
        //将左右小数组中存入所有的累加和，返回值是一共有几个累加和。
        int leftN = process(nums, 0, N >> 1, 0, 0, left);
        int rightN = process(nums, N >> 1, N, 0, 0, right);

        Arrays.sort(left, 0, leftN);
        Arrays.sort(right, 0, rightN--);

        int ans = Math.abs(goal);
        for (int i = 0; i < leftN; i++) {
            while (rightN > 0 && Math.abs(goal - left[i] - right[rightN]) >= Math.abs(goal - left[i] - right[rightN - 1])) { // ------ps rightN >0 而不是>= 0
                rightN--;
            }
            ans = Math.min(ans, Math.abs(goal - left[i] - right[rightN]));
        }
        return ans;
    }


    // 解释一下
    // nums[0..index-1]已经选了一些数字，组成了累加和sum
    // 当前来到nums[index....end)这个范围，所有可能的累加和
    // 填写到arr里去
    // fill参数的意思是: 如果出现新的累加和，填写到arr的什么位置
    // 返回所有生成的累加和，现在填到了arr的什么位置
    private static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            fill = process(nums, index + 1, end, sum, fill, arr);
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        return fill;
    }
}