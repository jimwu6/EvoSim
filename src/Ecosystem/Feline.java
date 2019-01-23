package Ecosystem;

public class Feline extends Mammal implements Carnivore{

	public int fangSize, claw;
	
	public Feline(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 20;
		furLength = 30;
		intelligence = 30;
	}
	
	public Feline(Feline feline) {
		super(feline);
	}
	
	public Feline(Feline feline, boolean canMate) {
		super(feline, canMate);
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
			return new Feline(this, this.canMate(mate));
		return null;	
	}
	
	public void stalk(){
		
	}
	public void chase(Animal prey){
		
	}
	public void feed(Animal prey){
		
	}
}
