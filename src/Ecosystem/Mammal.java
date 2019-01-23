package Ecosystem;

public class Mammal extends Animal {
	protected int furDensity, furLength, hornSize, intelligence;
	
	public Mammal(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		hornSize = 0;
	}
	
	public Mammal(Mammal m) {
		super (m);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
	}
	
	public Mammal(Mammal m, boolean canMate) {
		super(m, canMate);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			if (this.intelligence > 70) {
				double chance = Math.random();
				if(chance > 0.98)
					return new Primate(this, this.canMate(mate));
				else if (chance > 0.96) 
					return new Feline(this, this.canMate(mate));
				else if (chance > 0.94) 
					return new Canine(this, this.canMate(mate));
				else if (chance > 0.92) 
					return new Rodent(this, this.canMate(mate));
				else if (chance > 0.90)
					return new Hooved(this, this.canMate(mate));
				else return null;
			}
			
			return new Mammal(this, this.canMate(mate));
		}
		
		return null;	
	}
}
