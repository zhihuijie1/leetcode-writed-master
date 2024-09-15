package algorithmbasic.leetcode.top100;

import java.util.*;

public class N347 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int each : nums) {
            map.put(each, map.getOrDefault(each, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        pq.addAll(map.entrySet());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }
}
/*
 * 给定一个整数数组 nums 和一个整数 k，请返回其中出现频率前 k 高的元素。你可以按任意顺序返回答案。
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * 输出：[1,2]
 *
 *
 * hashMap<Integer, Integer> || key -> size
 */