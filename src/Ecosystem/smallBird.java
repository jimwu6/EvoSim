package Ecosystem;

public class smallBird extends Bird implements Carnivore, Herbivore{

	public int featherVibrance;
	
	public smallBird(String imageName, int size, int speed, int lifespan, String gender, boolean flight, boolean aquatic) {
		super(imageName, size, speed, lifespan, gender, flight, aquatic);
		featherVibrance = 20;
	}
	
	public smallBird(smallBird bird) {
		super (bird);
		this.featherVibrance = bird.featherVibrance;
	}
	
	public smallBird(smallBird bird, boolean canMate) {
		super(bird, canMate);
		this.featherVibrance = bird.featherVibrance;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			return new smallBird(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void sing(){
		
	}
	
	public void feed(String plant){
		
	}
	
	public void chase(Animal prey){
		
	}
	public void feed(Animal prey){
		
	}
}
