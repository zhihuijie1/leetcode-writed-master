package algorithmbasic.leetcode.top100;

public class N_quickSort {
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if(L >= R) {
            return;
        }
        //选择一个划分点，将其放在最后
        swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
        int[] indexs = flag(arr, L, R);
        process(arr, L, indexs[0]);
        process(arr,indexs[1], R);
        return;
    }

    //R位置是划分值位置
    private static int[] flag(int[] arr, int L , int R) {
        int l = L - 1;
        int r = R;
        int k = arr[R];
        int cur = L;

        while(l < r && cur < r) {
            if(r - 1 >= 0 && arr[cur] > k) {
                swap(arr, cur, r - 1);
                r--;
            }else if(l+1 < arr.length && arr[cur] < k) {
                swap(arr, l + 1, cur);
                l++;
                cur++;
            }else {
                cur++;
            }
        }

        return new int[]{l, r};
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {8,7,6,5,4,3,2,1};
        quickSort(arr);
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }
}
