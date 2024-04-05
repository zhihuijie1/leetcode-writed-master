package algorithmbasic.leetcode.coding2;

public class longestValidParentheses {
    public static int longestValidParentheses(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int a = 0;
        int b = 0;
        int count = 0;
        int pre = 0;
        int in = 0;
        while(a < n && b < n) {
            while(a < n && str[a] != '(') {
                a++;
            }
            while(b < n && str[b] != ')') {
                b++;
                in = b - 1;
            }
            if(b < n && a < n && b > a && in <= a) { //完整的
                if(pre + 1 == a || pre == 0 && a + 1 == b) { //连续的
                    count++;
                    pre = b;
                }else {
                    count--;
                }
                a++;
                b++;
            }else {
                b++;
            }
        }
        return count * 2;
    }
    //fucking

    public static void main(String[] args) {
        String s = ")()())())";
        int i = longestValidParentheses(s);
        System.out.println(i);
    }
}
