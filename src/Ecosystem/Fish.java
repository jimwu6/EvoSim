package Ecosystem;

/**
 * Class for fish animals that live in water.
 * Fish can swim around and eat both other animals and plants.
 */
public class Fish extends Animal {
	//fields
	private int scaleHardness;
	private boolean poisonous, luminant;
	
	/**
	 * Creates a new fish that can live in water.
	 * 
	 * @param type refers to the type of animal this is - a fish
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Fish(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		scaleHardness = 20;
		poisonous = false;
		luminant = false;
		herbivore = true;
		carnivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}
	
	/** 
	 * Copy constructor for a fish that creates a duplicate of it.
	 * 
	 * @param f is the fish that is being duplicated
	 */
	public Fish(Fish f) {
		super (f);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous;
		luminant = f.luminant;
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}
	
	/** 
	 * Gives birth to a fish from a fish parent.
	 * 
	 * @param f is the parent fish
	 * @param canMate checks if the fish can mate
	 */
	public Fish(Fish f, boolean canMate) {
		super(f, canMate);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous? f.poisonous : Math.random() > 0.9;
		luminant = Math.random() > 0.9? !f.luminant : f.luminant;
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}

	/** 
	 * Constructs a fish from cellular animals.
	 * 
	 * @param cellular is the cellular parent
	 * @param canMate checks if the parent can mate
	 */
	public Fish(Cellular cellular, boolean canMate) {
		super("fish", 10, 20, 50 + (int) (Math.random() * 21 - 10), cellular.gender());
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 * mating method for the animal that allows it to construct a baby fish or lizard with another mate
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			
			if (this.scaleHardness > 50 && Math.random() > 0.97 && this.poisonous)
				return new Amphibian(this, this.canMate(mate));
			return new Fish(this, this.canMate(mate));
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 * checks if the animal can mate and if it is on appropriate terrain
	 * creates a new animal if true
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * updates fish's health and appetite, traits
	 */
	public void update() {
		super.update();
		double rand = Math.random();
		
		//random chance to mutate scale hardness
		if (rand > 0.7)
			scaleHardness += 4;
		else if (rand > 0.4)
			scaleHardness -= 4;
	}
	
	/**
	 * @return if the fish is poisonous or not
	 */
	public boolean poisonous () {
		return poisonous;
	}

}
