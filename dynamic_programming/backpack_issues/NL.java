package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NL {
    public static void main(String[] args) {
        try {
            // 获取localhost对应的InetAddress对象
            InetAddress localhost = InetAddress.getByName("localhost");

            // 打印IP地址
            System.out.println("Localhost IP Address: " + localhost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}


