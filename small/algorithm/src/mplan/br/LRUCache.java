package mplan.br;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 天真无邪
 * @createdate 2020年6月29日下午10:49:32
 * @Desciption 寻月
 * 
 **/
public class LRUCache {

	private int size = 0;

	private Map<Object, Object> data = new HashMap<>();

	private Map<Object, Integer> hitCount = new HashMap<>();

	public LRUCache(int size) {
		this.size = size;
	}

	public Object get(Object key) {
		if (!data.containsKey(key))
			return -1;
		Integer count = hitCount.get(key);
		this.hitCount.put(key, ++count);
		return this.data.get(key);
	}

	public void put(Object key, Object value) {
		if (data.size() >= size) {// 缓存容量达到上线
			boolean firstFlag = true;
			Integer minHit = null;
			Object minKey = null;
			Iterator<Object> iterator = hitCount.keySet().iterator();
			while (iterator.hasNext()) {
				Object termKey = iterator.next();
				Integer count = this.hitCount.get(termKey);
				if (firstFlag) {
					minHit = count;
					minKey = termKey;
					firstFlag = false;
					continue;
				}
				if (minHit.compareTo(count) > 0) {
					minHit = count;
					minKey = termKey;
				}
			}
			this.data.remove(minKey);
			this.hitCount.remove(minKey);
			this.data.put(key, value);
		} else {
			this.data.put(key, value);
		}
		this.hitCount.put(key, Integer.valueOf(0));
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

}
