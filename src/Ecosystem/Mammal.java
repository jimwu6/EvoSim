package Ecosystem;

public class Mammal extends Animal {
	protected int furDensity, furLength, hornSize, intelligence;

	public Mammal(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
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
	}

	public Mammal(Mammal m, boolean canMate) {
		super(m, canMate);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;

		if (Math.random() > 0.95)
			hornSize++;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			if (this.intelligence > 70) {
				double chance = Math.random();
				if(chance > 0.97)
					return new Primate(this, this.canMate(mate));
				else if (chance > 0.95) 
					return new Feline(this, this.canMate(mate));
				else if (chance > 0.93) 
					return new Canine(this, this.canMate(mate));
				else if (chance > 0.91) 
					return new Rodent(this, this.canMate(mate));
				else if (chance > 0.89)
					return new Hooved(this, this.canMate(mate));
				else return null;
			}

			return new Mammal(this, this.canMate(mate));
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
