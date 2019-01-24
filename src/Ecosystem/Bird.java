package Ecosystem;

public class Bird extends Animal {
	
	protected boolean flight;
	
	public Bird(String imageName, int size, int speed, int lifespan, String gender, boolean flight) {
		super(imageName, size, speed, lifespan, gender);
		this.flight = flight;
		this.water = true;
		this.land = true;
	}
	
	public Bird(Bird bird) {
		super (bird);
		this.flight = bird.flight;
		this.water = true;
		this.land = true;
	}
	
	public Bird(Bird bird, boolean canMate) {
		super(bird, canMate);
		this.flight = bird.flight;
		this.water = true;
		this.land = true;
	}

	public Bird(Lizard lizard, boolean canMate) {
		super("smallBird", 20, 60, 30, Math.random() < 0.5? "Male" : "Female");
		this.water = true;
		this.land = true;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate)) 
		{
			this.mateTimer = 3;
			return new Bird(this, this.canMate(mate));
		}
		return null;
	}
	
	public void update() {
		super.update();
	}
	
	public void fly(){
		
	}
	
	public void featherDisplay(){
		
	}
}
