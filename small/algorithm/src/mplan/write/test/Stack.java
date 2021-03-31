package mplan.write.test;

import java.util.ArrayDeque;

/**
 * @author 天真无邪
 * @createdate 2020年3月22日下午3:07:54
 * @Desciption 寻月 使用ArrayQueue 实现 Stack
 **/
public class Stack {

	public static void main(String[] args) {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for (int i = 0; i < 5; i++) {
			ad.push(i);
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(ad.pop());
		}
	}
}
