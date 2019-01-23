package Ecosystem;

public class Snake extends Reptile implements Carnivore{
	public boolean venomous, hooded;
	
	public Snake(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		venomous = false;
		hooded = false;
	}
	
	public Snake(Snake s) {
		super (s);
		this.venomous = s.venomous;
		this.hooded = s.hooded;
	}
	
	public Snake(Snake s, boolean canMate) {
		super(s, canMate);
		this.venomous = s.venomous;
		this.hooded = s.hooded;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			return new Snake(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void stalk(){
		
	}
	
	public void hoodDisplay(){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed(Animal prey){
		
	}
}
