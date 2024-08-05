package algorithmbasic.leetcode.StringOperations;

public class N541 {
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int startIndex = 0;
        int i = 2*k;
        for(i = 2*k; i < N; i += 2*k) {
            int endIndex = i - k;
            reverse(str, startIndex, endIndex - 1);// 左闭右闭
            startIndex = i;
        }
        if(i - k <= N) {
            int endIndex = i - k;
            reverse(str, startIndex, endIndex - 1);
        }else {
            reverse(str, startIndex, N - 1);
        }
        return new String(str);
    }

    private static void reverse(char[] str, int l, int r) {
        int left = l;
        int right = r;
        while(left < right) {
            char temp = 'a';
            temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }
    }
}
