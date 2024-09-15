package algorithmbasic.leetcode.top100;

public class N_heap {

    static int[] arr;
    static int heapSize = 0;
    static int limit;

    public N_heap (int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    public static void put(int value) {
        if(isFull()) {
            System.out.print("堆是满的");
        }
        arr[heapSize] = value;
        up(arr, heapSize++);
    }

    public static int pop() {
        if(isEmpty()) {
            System.out.println("堆是空的");
        }
        int ans = arr[0];
        swap(arr, 0, --heapSize);
        down(arr, 0, heapSize);
        return ans;
    }

    public static void up(int[] arr, int index) {
        int fatherIndex = (index - 1) / 2;
        while(arr[fatherIndex] > arr[index]) {
            swap(arr, fatherIndex, index);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }

    public static void down(int[] arr, int index, int heapSize) {
        int l = 2 * index + 1;
        while(l < heapSize) {
            int minSon = (l + 1) < heapSize ? arr[l + 1] < arr[l] ? l +1 : l : l ;
            if(arr[minSon] > arr[index]) {
                swap(arr, minSon, index);
                index = minSon;
                l = 2 * index + 1;
            }else {
                break;
            }
        }
    }

    public static boolean isEmpty() {
        return heapSize == 0;
    }

    public static boolean isFull() {
        return heapSize == limit;
    }

    public static void swap(int[] arr, int i, int j) {

    }
}
