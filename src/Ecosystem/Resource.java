package Ecosystem;

public class Resource {
	
	protected int heal;
	protected String name;
	
	public Resource(String n) {
		name = n;
	}
	
	public int heal() {
		return heal;
	}
	
	public String getName() {
		return name;
	}
}