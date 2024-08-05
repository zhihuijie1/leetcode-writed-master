package algorithmbasic.leetcode.StringOperations;

public class N541 {
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int last = -1;
        int index = -1;
        for(int i = 0; i < N; i += 2*k) {
            index = i - k;
            if(index > 0) {
                reverse( str, last, index-1);
            }
            last = i;
        }
        if(N - last > k) {
            reverse(str, last, last+k-1);
        }else {
            reverse(str, last, N-1);
        }
        return str.toString();
    }

    private static void reverse(char[] s, int l, int r) {
        int left = l;
        int right = r;

        while(left < right) {
            swap(s,left,right);
            left++;
            right--;
        }
    }

    private static void swap(char[] s, int left, int right) {
        char temp = 'a';
        temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
