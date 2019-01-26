package Ecosystem;

/**
 *The Primate class represents primates, which are ape- or monkey-like mammals.
 */
public class Primate extends Mammal {
	private int toolStrength;

	/**This method creates a new Primate that can live on land and generally consumes both plants and meat.
	 * @param type refers to the type of animal this is - a Primate
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female	
	 */
	public Primate(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		furDensity = 30;
		furLength = 15;
		intelligence = 50;
		toolStrength = 10;
		carnivore = true;
		herbivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("Primate");
		appearance = makeImage(bodyParts);
	}

	/**This method creates a copy of an existing Primate.
	 * @param Primate is the copied Primate
	 */
	public Primate(Primate Primate) {
		super(Primate);
		this.toolStrength = Primate.toolStrength;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("Primate");
		appearance = makeImage(bodyParts);
	}

	/**This method creates a new Primate that formed through the mating of Mammals. 
	 * @param mammal is the parent mammal
	 * @param canMate checks if mammal can mate
	 */
	public Primate(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "Primate";
		this.toolStrength = 10;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("Primate");
		appearance = makeImage(bodyParts);
	}

	/**
	 * This method creates a Primate that formed during the mating process between two primates. 
	 * 
	 * @param primate is the parent primate
	 * @param canMate checks if mammal can mate
	 */
	public Primate(Primate Primate, boolean canMate) {
		super(Primate, canMate);
		this.toolStrength = Primate.toolStrength;
		carnivore = true;
		herbivore = true;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal)
	 * mating method for Primate
	 */
	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);

		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Primate(this, this.canMate(mate));
		}

		return a;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal, boolean)
	 * mating method for Primates that returns an animal if the land is appropriate
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#update()
	 * updates animal's traits, also a random chance to update it's traits
	 */
	public void update() {
		super.update();
		double rand = Math.random();

		if (rand > 0.8) {
			toolStrength++;
			if (rand > 0.9)
				intelligence++;
		}
	}

}
