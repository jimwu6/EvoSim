package Ecosystem;

public class Mammal extends Animal {
	protected int furDensity, furLength, hornSize, intelligence;

	public Mammal(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		hornSize = 0;
		land = true;
		water = false;
	}

	public Mammal(Mammal m) {
		super (m);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
		land = true;
		water = false;
	}

	public Mammal(Mammal m, boolean canMate) {
		super(m, canMate);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
		land = true;
		water = false;

		if (Math.random() > 0.95)
			hornSize++;
	}

	public Animal mate(Animal animal, boolean canMate) {
		if (canMate)
			return this.mate(animal);
		return null;
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate)) {

			this.mateTimer = 3;

			double chance = Math.random();

			if(intelligence > 65 && chance > 0.9)
				return new Primate(this, this.canMate(mate));
			else if (intelligence > 55 && chance > 0.8) 
				return new Canine(this, this.canMate(mate));
			else if (intelligence > 40 && chance > 0.7) 
				return new Feline(this, this.canMate(mate));
			else if (intelligence > 30 && chance > 0.75) 
				return new Hooved(this, this.canMate(mate));
			else if (chance > 0.65)
				return new Rodent(this, this.canMate(mate));

			return null;	
		}

		return null;	
	}

	public void update() {
		super.update();
		double rand = Math.random();

		if (hornSize > -1 && rand > 0.94)
			hornSize++;

		if (rand > 0.94) {
			if ((age + 0.0) / (lifespan + 0.0) < 0.75)
				intelligence++;
			else
				intelligence--;
		}

	}
}
