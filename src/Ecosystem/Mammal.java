package Ecosystem;

/**
 * Mammal class represents all mammalian forms of life such as canine, felines, and so on
 */
public class Mammal extends Animal {
	//fields
	protected int furDensity, furLength, hornSize, intelligence;

	/**
	 * creates a new mammal that can live on land
	 * @param type refers to the type of animal this is - a mammal
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	*/
	public Mammal(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		hornSize = 0;
		land = true;
		water = false;
	}

	/** takes a mammal and makes a copy of it
	 * @param m is the duplicated mammal
	 */
	public Mammal(Mammal m) {
		super (m);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
		land = true;
		water = false;
	}

	/**creates a new child mammal if parent can mate
	 * @param m is the parent mammal
	 * @param canMate checks if the mammal can mate
	 */
	public Mammal(Mammal m, boolean canMate) {
		super(m, canMate);
		furDensity = m.furDensity;
		furLength = m.furLength;
		hornSize = m.hornSize;
		intelligence = m.intelligence;
		land = true;
		water = false;

		//random chance to grow a horn
		if (Math.random() > 0.95)
			hornSize++;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 * mating method for mammals
	 */
	public Animal mate(Animal animal, boolean canMate) {
		if (canMate)
			return this.mate(animal);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 * mating method for mammals that allows them to branch out and evolve into other animals
	 */
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

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * updates animals characteristics and health/appetite
	 */
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
