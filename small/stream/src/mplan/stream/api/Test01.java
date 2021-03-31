package mplan.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 1.创建Stream
 */
public class Test01 {

	@Test
	public void test01() {
		// 1.通过Collection 提供的stream方法。
		List<String> list = new ArrayList<>();
		Stream<String> stream01 = list.stream();

		// 2.通过Arrays提供的stream方法。
		String[] strs = new String[10];
		Stream<String> stream02 = Arrays.stream(strs);

		// 3.通过Stream的of方法
		Stream<Integer> stream03 = Stream.of(1, 2, 3);

		// 4.创建无限流
		// 迭代，函数型
		Stream<Integer> stream04 = Stream.iterate(0, x -> x + 2);

		// 生成,供给型
		Stream<Double> stream05 = Stream.generate(() -> Math.random());
	}

}
