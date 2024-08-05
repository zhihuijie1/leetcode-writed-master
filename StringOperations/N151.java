package algorithmbasic.leetcode.StringOperations;

public class N151 {
    public String reverseWords(String s) {
        //先去除首位以及中间多余的空格。
        StringBuffer sb = removeSpace(s);
        //反转整个字符串
        reverseString(sb);
        //反转单词
        reverseEachWord(sb);
        return sb.toString();
    }

    //先去除首位以及中间多余的空格。
    private static StringBuffer removeSpace(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' ') {
            left++;
        }
        while(s.charAt(right) == ' ') {
            right--;
        }
        StringBuffer sb = new StringBuffer();
        while(left <= right) {
            if(s.charAt(left) != ' '|| sb.charAt(sb.length() - 1) != ' ') {
                sb.append(s.charAt(left));
                left++;
            }else {
                left++;
            }
        }
        return sb;
    }

    //反转整个字符串
    private static void reverseString(StringBuffer sb) {
        int left  = 0;
        int right = sb.length() - 1;
        while(left < right) {
            char temp = '1';
            temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    //反转单词
    private static void reverseEachWord(StringBuffer sb){
        int left = 0;
        int right = 0;
        while(right < sb.length()) {
            if(right != sb.length() -1 && sb.charAt(right) != ' ') {
                right++;
                continue;
            }
            int lastIndex = right == sb.length() - 1 ? right : right - 1;
            reverse(sb, left, lastIndex);
            right++;
            left = right;
        }
    }

    private static void reverse(StringBuffer sb, int l, int r) {
        int left = l;
        int right = r;
        while(left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right,temp);
            left++;
            right--;
        }
    }
}


/**
 给定一个字符串，逐个翻转字符串中的每个单词。
 示例 1：
 输入: "the sky is blue"
 输出: "blue is sky the"
 示例 2：
 输入: "  hello world!  "
 输出: "world! hello"
 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 示例 3：
 输入: "a good   example"
 输出: "example good a"
 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

 1：思路：先去除多余的空格，然后将整个字符串反转，然后再对单词反转

 2：- 步骤1：先去除首位以及中间多余的空格。
 - 步骤2：反转整个字符串
 - 步骤3：反转单词
 */
