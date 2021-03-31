package mplan.singleton;

/**
 * @author 天真无邪
 * @createdate 2020年3月22日下午3:26:46
 * @Desciption 寻月 懒汉模式 线程不安全 需要时加载
 **/
public class Demo01 {

	private static Demo01 demo = null;

	private Demo01() {
	}

	public static /**synchronized*/ Demo01 getInstance() {
		if (demo == null)
			demo = new Demo01();
		return demo;
	}

}
