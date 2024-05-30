package algorithmbasic.leetcode.top100;

import java.util.HashMap;

public class N1 {
    public int[] twoSum(int[] nums, int target) {
        //维护一个hashmap映射 - 数值 -> 索引
        HashMap<Integer,Integer> valToIndex = new HashMap<>();
        int N = nums.length;
        for(int i = 0; i < N; i++) {
            //查表，看看是否有能和 nums[i] 凑出 target 的元素
            if(valToIndex.containsKey(target - nums[i])) {
                return new int[]{valToIndex.get(target - nums[i]), i};
            }
            // 存入 val -> index 的映射
            valToIndex.put(nums[i],i);
        }
        return null;
    }
}
