package mplan.lg;

/**
 * 中介
 **/
public class Intermediary {

	// 寻找房源
	public void searchHose(Tenant tenant) {
		String name = tenant.getName();
		System.out.println("开始为" + name + "寻找房源");
		String require = tenant.getRequire();
		System.out.println(name + "的需求为:" + require);
		System.out.println("查询DB.....");
		System.out.println("们留下信息，等我有房源通知你们");
		System.out.println("结束服务");
	}
}
