package algorithmbasic.leetcode.top100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class N884 {
    public static String[] uncommonFromSentences(String s1, String s2) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] str1 = s1.split(" ");
        String[] str2 = s2.split(" ");
        List<String> list = new LinkedList<>();

        for(int i = 0; i < str1.length; i++) {
            if(!map.containsKey(str1[i])) {
                map.put(str1[i], 1);
            }
        }

        for(int i = 0; i < str2.length; i++) {
            if(map.containsKey(str2[i])) {
                int count = map.get(str2[i]) + 1;
                map.remove(str2[i]);
                map.put(str2[i], count);
            }else {
                map.put(str2[i], 1);
            }
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        String s1 = "this apple is sour";
        String s2 = "this apple is sweet";
        uncommonFromSentences(s1, s2);
    }
}
