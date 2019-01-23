package Ecosystem;

public class Hooved extends Mammal {
	public int hornStrength;
	
	public Hooved(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 40;
		furLength = 20;
		intelligence = 20;
		hornStrength = 50;
		carnivore = false;
		herbivore = true;
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
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Hooved(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public void update() {
		super.update();
		double rand = Math.random();
		
		if (hornSize > 0 && rand > 0.5)
			hornStrength++;
	}
	
	public void findHerd(Hooved animal){
		
	}
	
	public void feed(String plant){
		
	}

}


