package Ecosystem;

import java.util.*;

public class Plant {

	String name;
	Resource resource;
	
	public Plant(String plantName, String resourceName) {
		this.name = plantName;
		resource = new Resource(resourceName);
	}
}

class Resource {
	
	int heal;
	String name;
	
	public Resource(String n) {
		this.name = n;
	}
	
	public int heal() {
		return this.heal;
	}
}
