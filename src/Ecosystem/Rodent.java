package Ecosystem;

public class Rodent extends Mammal implements Herbivore{

	public int claw;
	public boolean disease;
	
	public Rodent(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 10;
		furLength = 5;
		intelligence = 5;
	}
	
	public Rodent(Rodent rodent) {
		super(rodent);
	}
	
	public Rodent(Rodent rodent, boolean canMate) {
		super(rodent, canMate);
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
			return new Rodent(this, this.canMate(mate));
		return null;
	}
	
	public void spreadDisease(Animal animal){
		
	}
	
	public void feed(String plant){
		
	}

}
