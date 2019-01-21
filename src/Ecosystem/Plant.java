package Ecosystem;

public class Plant {

	String name;
	natResource resource = null;
	public int size;
	
	public Plant(String plantName) {
		name = plantName;
		size = 111;
	}
		
	public Plant(String plantName, String resourceName) {
		this(plantName);
		resource = new natResource(resourceName);
	}
	
	public void grow() {
		if (size < 20)
			size += 5;
		else if (size < 40)
			size += 2;
		else if (size < 80)
			size += 1;
		else
			size += Math.random() * 5 - 2;
	}
}


