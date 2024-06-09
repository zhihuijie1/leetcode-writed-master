package algorithmbasic.leetcode.top100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class N46 {
    //两个全局变量
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> result = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //下标 --> 值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        backTracking(map);
        return ans;
    }

    private void backTracking(HashMap<Integer, Integer> map) {
        //纵向结尾处理
        if (map.size() == 0) {
            ans.add(new LinkedList<>(result));
            return;
        }
        //横向单层处理
        Object[] array = map.keySet().toArray();
        for (int i = 0; i < array.length; i++) {
            Integer key = (Integer) array[i];
            Integer value = map.remove(key);
            result.add(value);
            backTracking(map);
            map.put(key, value);
            result.remove(result.size() - 1);
        }
    }
}
