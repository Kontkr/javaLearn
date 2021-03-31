package mplan.lg;

/**
 * 租户
 **/
public class Tenant {

	private String name;
	
	private String require;

	
	public Tenant(String name,String require) {
		this.name = name;
		this.require = require;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

}
