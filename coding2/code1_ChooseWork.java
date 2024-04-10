package algorithmbasic.leetcode.coding2;


import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;


//给定数组hard和money，长度都为N
//hard[i]表示i号的难度, money们]表示i号工作的收入
//给定数组ability，长度都为M, ability[j]表示j号人的能力
//每一号工作，都可以提供无数的岗位(一个工作需要无限多的人)
//难度和收入都一样但是人的能力必须>=这份工作的难度，才能上班
//返回一个长度为M的数组ans,ans们]表示j号人能获得的最好收入


//思路: 1 hard <= ability 2: money(MAX) 贪心
//- 对数组进行预处理: 将每一份工作的难度与其对应的薪酬封装在一起(目的是满足后面排序规则)，然后进行排序，排序的规则是：hard由小到大，相同hard时money由大到小。
//- 对有序的封装后的数组进行修剪工作: 相同hard的工作只留一份money最高的。当hard提高而money降低的工作删除。
//                              此时剩下的工作将会满足的条件是: hard对应的一定是当前hard下money做多的。 hard上升money一定会上升
//- 根据ability值找到hard <= abiliity并距离ability最近的就是最佳工作。
public class code1_ChooseWork {
    //封装 hard与其对应的money
    public static class Job {
        int hard;
        int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    //自定义比较器
    //比较规则 - hard由小到大，相同hard时money由大到小。
    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard - o2.hard == 0 ? o2.money - o1.money : o1.hard - o2.hard;
        }
    }

    public static int[] getMoney(Job[] jobs, int[] ability) {
        //对Jobs数组进行自定义规则的排序
        Arrays.sort(jobs, new JobComparator());
        //此时jobs数组已经是自定义的顺序，对jobs数组进行修剪工作
        //相同hard的工作只留一份money最高的。当hard提高而money降低的工作删除。
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //jobs[0]一定是同一个hard下最优的
        map.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                map.put(jobs[i].hard, jobs[i].money);
                pre = jobs[i];
            }
        }
        //修剪完成，hard对应的一定是当前hard下money做多的。 hard上升money一定会上升
        //根据ability值找到hard <= abiliity并距离ability最近的就是最佳工作。
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            //根据ability值找到hard <= abiliity并距离ability最近的key(hard)
            Integer nearHard = map.floorKey(ability[i]);
            ans[i] = nearHard == null ? 0 : map.get(nearHard);
        }
        return ans;
    }
}
