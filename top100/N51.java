package algorithmbasic.leetcode.top100;

import java.util.LinkedList;
import java.util.List;

public class N51 {
    static List<List<String>> ans = new LinkedList<>();
    static List<String> chessBoard = new LinkedList<>();

    public static List<List<String>> solveNQueens(int n) {
        //初始化棋盘
        for(int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < n; j++) {
                sb.append(".");
            }
            chessBoard.add(sb.toString());
        }
        backTracking(0, n);
        return ans;
    }

    private static void backTracking(int row, int n) {
        //纵向尾处理
        if(row == n) {
            ans.add(new LinkedList<>(chessBoard));
            return;
        }
        //横向单层处理
        for(int i = 0; i < n; i++) {
            //先检查后放
            if(isValid(chessBoard, row, i, n)) {
                //在进行递归之前提前进行筛选
                StringBuffer sb = new StringBuffer(chessBoard.get(row));
                sb.setCharAt(i,'Q');
                chessBoard.remove(row);
                chessBoard.add(row, sb.toString());
                backTracking(row + 1, n);
                sb.setCharAt(i,'.');
                chessBoard.remove(row);
                chessBoard.add(row, sb.toString());
            }
        }
    }

    private static boolean isValid(List<String> chessBoard, int row , int col, int n) {
        //检查列
        for(int j = 0; j < n; j++) {
            if(chessBoard.get(j).charAt(col) == 'Q') {
                return false;
            }
        }
        //检查行
        for(int j = 0; j < n; j++) {
            if(chessBoard.get(row).charAt(j) == 'Q') {
                return false;
            }
        }
        //检查45°对角线
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chessBoard.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        //检查135°对角线
        for(int i = row - 1 , j = col + 1; i >= 0 && j < n; i--, j++) {
            if(chessBoard.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }
}
