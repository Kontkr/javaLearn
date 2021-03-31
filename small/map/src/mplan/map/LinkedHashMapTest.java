package mplan.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @describe
 * @author zhujh9
 * @createdate 2020-09-03 下午2:00:45
 * 
 **/
public class LinkedHashMapTest {
	public static void main(String[] args) {
		LinkedHashMap<String, String> linkedMap = new LinkedHashMap<>();
		for (int i = 0; i < 10000; i++)
			linkedMap.put(String.valueOf(i), String.valueOf(i));
		String[] strs = linkedMap.keySet().toArray(new String[linkedMap.size()]);
		Arrays.stream(strs).forEach(System.out::println);
		System.out.println("----------------HashMap-------------");
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < 10000; i++)
			map.put(String.valueOf(i), String.valueOf(i));
		String[] strMap = map.keySet().toArray(new String[map.size()]);
//		Arrays.stream(strMap).forEach(System.out::println);
	}
}
