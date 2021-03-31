package mplan.write.test;

/**
 * @author 天真无邪
 * @createdate 2020年3月21日下午8:59:04
 * @Desciption 寻月
 * 
 **/
public class WriteTest02 {

	private static WriteTest02 test02 = null;
	
	private WriteTest02() {}

	public static synchronized WriteTest02 getInstall() {
		if(test02 == null)
			test02 = new WriteTest02();
		return test02;
	}
}
