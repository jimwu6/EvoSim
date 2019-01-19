package Ecosystem;

public class Plant {

	String name;
	Resource resource = null;
	int size;
	
	public Plant(String plantName) {
		name = plantName;
		size = 10;
	}
		
	public Plant(String plantName, String resourceName) {
		this(plantName);
		resource = new Resource(resourceName);
	}
}

class Resource {
	
	int heal;
	String name;
	
	public Resource(String n) {
		name = n;
	}
	
	public int heal() {
		return heal;
	}
}
