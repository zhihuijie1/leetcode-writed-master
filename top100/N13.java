package algorithmbasic.leetcode.top100;

import java.util.HashMap;

public class N13 {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        char[] str =  s.toCharArray();
        int N = str.length;
        int res = 0;

        for(int i = N-1; i >= 0; i--) {
            if(i < N-1 && map.get(str[i]) < map.get(str[i+1])) {
                res -= map.get(str[i]);
            }else{
                res += map.get(str[i]);
            }
        }

        return res;
    }
}
