package mplan.br;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 天真无邪
 * @createdate 2020年6月29日下午11:55:11
 * @Desciption 寻月
 * 
 **/
public class MatchStr {

	public Map<String, Integer> match(String str) {
		if (str == null && "".equals(str))
			return null;
		int start = 0;
		Set<String> words = new HashSet<>();
		Map<String, Integer> countMaps = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 32) {
				words.add(str.substring(start, i));
				start = i + 1;
			}
			if (i == str.length() - 1 && str.charAt(i) != 32)
				words.add(str.substring(start));

		}
		for (String term : words)
			countMaps.putAll(countWord(term, str));
		return countMaps;
	}

	private Map<String, Integer> countWord(String countStr, String str) {
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(countStr)) != -1) {
			count++;
			str = str.substring(index + countStr.length());
		}
		System.out.println(countStr + "出现的次数为:" + count);
		Map<String, Integer> countMap = new HashMap<>();
		countMap.put(countStr, count);
		return countMap;
	}

	public static void main(String[] args) {
		String str = "Hello python Hello world";
		new MatchStr().match(str);
	}

}
