package Ecosystem;

public class Bird extends Animal {
	
	protected boolean flight, aquatic;
	
	public Bird(String imageName, int size, int speed, int lifespan, String gender, boolean flight, boolean aquatic) {
		super(imageName, size, speed, lifespan, gender);
		this.flight = flight;
		this.aquatic = aquatic;
	}

	public void fly(){
		
	}
	
	public void featherDisplay(){
		
	}
}
