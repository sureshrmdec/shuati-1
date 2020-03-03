package Interviews.Indeed;

public class Expiring_Map {
    /**
     * 题还是那个老题，就是ExpiringMap, 这个题本身不难，但是follow up 特别多， 有4个。我当时就醉了，感觉要挂，
     * 不过面试官人很nice一直给提示，最后还是把题做出来了，不知道结果咋样。
     *
     * 给一个expiring map， 你可以一直往里面put东西，这些东西都有个过期值，一旦过期就get不到了。
     * //put(null, null, 2000);
     *
     * // 10:00:00 - put(10, 25, 5000)
     * /**
     * * (10, (25, 10:00:05))
     * *
     * // 10:00:04 - get(10) -> 25
     * // 10:00:05 - get(10) -> null
     * // 10:00:06 - get(10) -> null
     *
     * public class ExpiringMap<K, V> {
     *     HashMap<K, <Duration>> map = new HashMap();
     *     private class Duration {
     *         private V value;
     *         private long expirationTime;
     *         public Duration(V val, long durationMillis) {
     *             this.value = val;
     *             this.expirationTime = durationMillis + System.currentTimeMillis();
     *         }
     *     }
     *     void put(final K key, final V value, final long durationMillis) {
     *         map.put(key, new Duration(value, durationMillis);
     *     }
     *
     *     V get(final K key) {
     *         //if map does not have it
     *         if(!map.containsKey(key)) {
     *             return null;
     *         } else {
     *         //map contains key
     *             Duration temp = map.get(key);
     *             //is valid entry
     *             if(temp.expirationTime > System.currentTimeMillis()) {
     *                 return temp.value;
     *             } else {
     *                 map.remove(key);
     *                 return null;
     *             }
     *         }
     *     }
     * }
     * //put(null, null, 2000);
     *
     * // 10:00:00 - put(10, 25, 5000)
     * /**
     * * (10, (25, 10:00:05))
     * *
     * // 10:00:04 - get(10) -> 25
     * /**
     * *
     * // 10:00:05 - get(10) -> null
     * // 10:00:06 - get(10) -> null
     *
     * 第二个 follow up： 如果用heap的话可以解决上面的问题，但是put时间变成了 O(k log n）怎么办？
     * lz是在put的时候remove 已经expired的东西，这样会remove k个已经expired的东西，每次remove时间是log n，
     * 所以put 从原来o（1） 变成了O(k log n）， 怎么解决
     *
     * 第三 follow up： put 从原来o（1） 变成了O(k log n）， 怎么解决？
     * lz说用多线程，put 只是put 到 hashmap 和 heap 里面， 但是不从heap remove。 put 从原来的 O(1）变成了 O(log n），
     * 然后用另外一个线程来做remove 操作。（幸好没叫写多线程代码，不然死定了）
     */
}