package algorithmbasic.leetcode.top100;

import java.util.*;

public class N45 {
    public int jump(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            map.put(i, i + nums[i]);
        }


        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.sort(
                (a, b) -> a - b
        );

        return process(n - 1, map, 0, linkedList);
    }

    public int process(int n, HashMap<Integer, Integer> map, int result, LinkedList<Integer> linkedList) {
        if(n == 0) {
            return result;
        }

        for(Map.Entry entry : map.entrySet()) {
            if((int)entry.getValue() >= n && (int)entry.getKey() <= n) {
                int minStep = process((int) entry.getKey(), map, result + 1, linkedList);
                linkedList.add(minStep + 1);
            }
        }
        return linkedList.getFirst();
    }
}

/**
 0：题目描述：
    给定一个 0 索引的整数数组 nums，其长度为 n。你最初位于 nums[0] 位置。
    每个元素 nums[i] 表示从索引 i 开始可以向前跳跃的最大步长。换句话说，如果你在 nums[i] 位置，你可以跳跃到任意 nums[i + j] 位置，其中：
    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 位置的最少跳跃次数。可以保证测试用例是可以到达 nums[n - 1] 位置的。

    输入：nums = [2,3,1,1,4]
    输出：2
    解释：到达最后一个索引的最少跳跃次数是 2。先从索引 0 跳 1 步到索引 1，然后跳 3 步到最后一个


 1：dp数组的定义：
    dp[i]：跳到当前i位置最少的次数是dp[i]

 2：dp数组的递推公式：
    dp[i] =
 */