package Ecosystem;

public class Hooved extends Mammal implements Herbivore{
	public int hornStrength;
	
	public Hooved(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 40;
		furLength = 20;
		intelligence = 20;
		hornStrength = 50;
	}
	
	public Hooved(Hooved hooved) {
		super(hooved);
		this.hornSize = hooved.hornStrength;
	}
	
	public Hooved(Hooved hooved, boolean canMate) {
		super(hooved, canMate);
		this.hornSize = hooved.hornStrength;
	}
	
	public Hooved(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Hooved(this, this.canMate(mate));
		}
		
		return null;
	}
	
	public void findHerd(Hooved animal){
		
	}
	
	public void feed(String plant){
		
	}

}


