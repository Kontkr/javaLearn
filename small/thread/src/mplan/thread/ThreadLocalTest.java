package mplan.thread;

/**
 * @author 天真无邪
 * @createdate 2020年3月31日下午11:06:21
 * @Desciption 寻月
 * 
 **/
public class ThreadLocalTest {

	public static ThreadLocal<Object> tl = new ThreadLocal<Object>() {

		@Override
		protected Object initialValue() {
			return Thread.currentThread();
		}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(() -> {
				Object a = tl.get();
				System.out.println(a);
			}).start();
	}

}