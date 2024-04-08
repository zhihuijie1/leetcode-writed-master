package algorithmbasic.leetcode.coding2;

public class Test {
    public static void main(String[] args) {
        int j=0;
        for (int i=1;i<10000000;i++){
            if (BaseConversion(i)){
                j++;
                System.out.println(i);
                if (j==2023){
                    System.out.println(i);//215040
                    break;
                }
            }
        }
    }
    public static boolean BaseConversion(int n){
        //十进制
        int sum=0;
        int x=n;
        while (x!=0){
            sum+=(x%10);
            x/=10;
        }
        if (n%sum!=0)
            return false;
        //二进制
        sum=0;
        x=n;
        while (x!=0){
            sum+=(x%2);
            x/=2;
        }
        if (n%sum!=0)
            return false;
        //八进制
        sum=0;
        x=n;
        while (x!=0){
            sum+=(x%8);
            x/=8;
        }
        if (n%sum!=0)
            return false;
        //十六进制
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        sum=0;
        x=n;
        while (x!=0){
            sum+=(arr[x%16]);
            x/=16;
        }
        if (n%sum!=0)
            return false;
        return true;
    }

}
