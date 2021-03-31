package mplan.lg;
/**
测试类
**/
public class Test {
	public static void main(String[] args) {
		Tenant zs = new Tenant("张三","2室一厅的房子，租金不超过2000");
		Tenant ls = new Tenant("李四","租金不超过1000的");
		
		Intermediary intermediary = new Intermediary();
		intermediary.searchHose(zs);
		intermediary.searchHose(ls);
	}
}
