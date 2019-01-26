package Ecosystem;

/**
 *class representing monkey-like animals
 */
public class Primate extends Mammal {
	private int toolStrength;

	/**creates a new primate that can live on land
	 * @param type refers to the type of animal this is - a primate
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
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	/**creates a copy of an existing primate
	 * @param primate is the copied primate
	 */
	public Primate(Primate primate) {
		super(primate);
		this.toolStrength = primate.toolStrength;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	/** birth method that returns a new primate 
	 * @param mammal is the parent mammal
	 * @param canMate checks if mammal can mate
	 */
	public Primate(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "primate";
		this.toolStrength = 10;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	/**
	 * birth method that returns a new primate 
	 * @param primate is the parent primate
	 * @param canMate checks if mammal can mate
	 */
	public Primate(Primate primate, boolean canMate) {
		super(primate, canMate);
		this.toolStrength = primate.toolStrength;
		carnivore = true;
		herbivore = true;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal)
	 * mating method for primate
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
	 * mating method for primates that returns an animal if the land is appropriate
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

	public void buildTool(){

	}

	public void feed(String plant){

	}

	public void chase(Animal prey){

	}

	public void feed(Animal prey){

	}
}
