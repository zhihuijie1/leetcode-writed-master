package algorithmbasic.leetcode.StringOperations;

public class N344 {
    public void reverseString(char[] s) {
        int N = s.length;
        int left = 0;
        int right = N-1;

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

/**
 0: 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    ["h","e","l","l","o"] -> ["o","l","l","e","h"]

 1: 采用双指针的方法 - 头尾指针
 */


