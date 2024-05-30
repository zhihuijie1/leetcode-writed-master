package algorithmbasic.leetcode.top100;

import java.util.HashMap;

public class N11 {
    public int maxArea(int[] height) {
        //双指针法从两边向内收缩
        int N = height.length;
        int left = 0;
        int right = N - 1;
        int maxt = 0;

        while(left < right) {
            int s = Math.min(height[left],height[right]) * (right - left);
            maxt = Math.max(maxt, s);
            //短边移动 因为尽可能的大
            if(height[left] > height[right]) {
                right--;
            }else {
                left++;
            }
        }
        return maxt;
    }
}
