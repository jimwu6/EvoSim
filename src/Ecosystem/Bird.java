package Ecosystem;

public class Bird extends Animal {
	
	protected boolean flight, aquatic;
	
	public Bird(String imageName, int size, int speed, int lifespan, String gender, boolean flight, boolean aquatic) {
		super(imageName, size, speed, lifespan, gender);
		this.flight = flight;
		this.aquatic = aquatic;
	}
	
	public Bird(Bird bird) {
		super (bird);
	}
	
	public Bird(Bird bird, boolean canMate) {
		super(bird, canMate);
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
			return new Bird(this, this.canMate(mate));
		return null;	
	}
	
	public void fly(){
		
	}
	
	public void featherDisplay(){
		
	}
}
