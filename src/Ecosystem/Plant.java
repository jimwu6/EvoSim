package Ecosystem;

public class Plant {

	String name;
	public int size;

	public Plant(String plantName, int s) {
		name = plantName;
		size = s;
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


