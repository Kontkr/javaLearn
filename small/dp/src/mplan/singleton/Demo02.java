package mplan.singleton;

/**
 * @author 天真无邪
 * @createdate 2020年3月22日下午3:32:31
 * @Desciption 寻月 饿汉式,类加载时初始化变量,线程安全
 **/
public class Demo02 {

	private static final Demo02 demo = new Demo02();

	private Demo02() {
	}

	public static Demo02 getInstance() {
		return demo;
	}
}
