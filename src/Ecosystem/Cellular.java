package Ecosystem;

/**
 * Class for simple cellular organisms.
 * <p>
 * Cells have the ability to move on land or in water.
 * These organisms also have a high possibility to evolve into various animals, and are considered to be the basepoint for evolution.
 *
 */
public class Cellular extends Animal {
	private int membrane;
	
	/** 
	 * Constructor that accepts fields and creates a cellular animal.
	 * 
	 * @param type - String for the type of animal this is
	 * @param size - int for the size of the animal
	 * @param speed - integer for the speed of the animal
	 * @param lifespan - integer for the lifespan of the animal
	 * @param gender - gender of the animal
	 */
	public Cellular(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		membrane = 1;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;
		this.bodyParts.clear();
		this.bodyParts.add("cellular");
		appearance = makeImage(bodyParts);
	}

	/**
	 * Copy constructor that accepts a cellular animal and constructs a cellular animal with identical stats.
	 * 
	 * @param c is the cellular animal that is being duplicated
	 */
	public Cellular(Cellular c) {
		super (c);
		membrane = c.membrane;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;

		this.bodyParts.clear();
		this.bodyParts.add("cellular");
		appearance = makeImage(bodyParts);
	}

	/** 
	 * Constructor used in mating that produces a newborn cell based on the parent.
	 * 
	 * @param c represents the parent animal
	 * @param canMate boolean representing the animal's ability to mate
	 */
	public Cellular(Cellular c, boolean canMate) {
		super(c, canMate);
		membrane = 1;
		herbivore = true;
		carnivore = false;
		water = true;
		land = false;

		this.bodyParts.clear();
		this.bodyParts.add("cellular");
		appearance = makeImage(bodyParts);
	}

	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 * returns a null value if called in mating
	 */
	public Animal mate(Animal mate) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 * if an animal can mate and it is on land, a cellular can turn into a fish or a lizard
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (this.canMate(mate))
		{
			if (membrane >= 4 && Math.random() > 0.5) {
				if (landSafe)
					return new Fish(this, this.canMate(mate));
				else
					return new Lizard(this, this.canMate(mate));
			}
			return new Cellular(this, this.canMate(mate));
		}
		
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * method to update the cellular animal's status and membrane
	 */
	public void update() {
		super.update();
		
		if (Math.random() > 0.5)
			membrane++;
	}
}
