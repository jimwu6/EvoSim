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
		this.flight = bird.flight;
		this.aquatic = bird.aquatic;
	}
	
	public Bird(Bird bird, boolean canMate) {
		super(bird, canMate);
		this.flight = bird.flight;
		this.aquatic = bird.aquatic;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Bird(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void fly(){
		
	}
	
	public void featherDisplay(){
		
	}
}
