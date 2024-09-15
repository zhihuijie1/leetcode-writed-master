package algorithmbasic.leetcode.top100;

import java.util.LinkedHashMap;

public class N_146 {

    //有序的hashmap
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    int cap;

    public N_146(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }
        //将key改为最近使用
        makeRecent(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        //如果当前有这个key就更新结果
        if(cache.containsKey(key)) {
            cache.put(key, value);
            makeRecent(key);
            return;
        }
        //如果当前cache满了，则弹出历史较久的节点
        if(cache.size() >= cap) {
            //链表的头节点是一个较久的key
            //next()：获取当前元素并将迭代器移动到下一个元素。
            Integer old = cache.keySet().iterator().next();
            cache.remove(old);
            //放入链表的尾节点
        }
        cache.put(key, value);
    }

    private void makeRecent(int key) {
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
    }
}


/*
* class LRUCache {
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
*
*
* */
