package mplan.sort;

import java.util.HashSet;
import java.util.Random;

/**
 * @author 天真无邪
 * @createdate 2020年3月20日下午11:05:11
 * @Desciption 寻月 直接插入排序 n^2
 **/
public class Sort01 {

	public static Integer[] getSortNumber() {
		HashSet<Integer> set = new HashSet<>();
		Random random = new Random();
		while (set.size() < 10) {
			set.add(random.nextInt(20));
		}
		return set.toArray(new Integer[0]);
	}

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<>();
		Random random = new Random();
		while (set.size() < 10) {
			set.add(random.nextInt(20));
		}
		Integer[] arrays = getSortNumber();
		System.out.println("排序前:");
		for (int i : arrays) {
			System.out.print(i + "\t");
		}
		System.out.println();
		int sentry;
		for (int i = 1; i < arrays.length; i++) {
			sentry = arrays[i];// 设置哨兵的值
			int j = i - 1;
			while (j >= 0 && arrays[j] < sentry) {
				arrays[j + 1] = arrays[j];
				j--;
			}
			arrays[j + 1] = sentry;
		}
		System.out.println("排序后:");
		for (int i : arrays) {
			System.out.print(i + "\t");
		}
	}
}
