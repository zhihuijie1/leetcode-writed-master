package algorithmbasic.leetcode.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;
        List<List<Integer>> lists = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0; i < N; i++) {
            //去重
            if( i > 0 && nums[i] == nums[i-1]) {continue;}
            int target = 0 - nums[i];
            int left = i + 1;
            int right = N - 1; //左闭右闭
            while(left < right) {
                if(nums[left] + nums[right] == target) {
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]){left++;} //去重
                    while(left < right && nums[right] == nums[right-1]){right--;}
                    left++;
                    right--;
                }else if(nums[left] + nums[right] > target) {
                    right--;
                }else{
                    left++;
                }
            }
        }
        return lists;
    }
}
