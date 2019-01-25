package Ecosystem;

/**
 * @author Justin
 *The Amphibian Class represents animals that are amphibians, such as frogs
 *Considered to be omnivores and have the ability to traverse both land territories and water territories
 */
public class Amphibian extends Animal {
	//fields
	protected int hydration;
	
	/**
	 * Fields that represent animal's ability to do damage when hunting - venomous- or when hunted -poisonous-
	 */
	public boolean poisonous, venomous;
	
	/** creates a new amphibian that can live on land or in water
	 * @param type refers to the type of animal this is - an amphibian
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Amphibian(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		hydration = 100;
		poisonous = Math.random() > 0.93;
		venomous = false;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a copy of another amphibian
	 * @param a represents the amphibian that this constructor duplicates
	 */
	public Amphibian(Amphibian a) {
		super (a);
		hydration = a.hydration;
		poisonous = a.poisonous;
		venomous = a.venomous;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	/** creates a duplicate of a parent amphibian with slight chances to mutate
	 * @param a represents the parent amphibian that the amphibian duplicates
	 * @param canMate represents the parent amphibian's ability to mate
	 */
	public Amphibian(Amphibian a, boolean canMate) {
		super(a, canMate);
		hydration = a.hydration;
		poisonous = a.poisonous? true : Math.random() > 0.8;
		venomous = a.venomous;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}

	/**
	 * @param fish represents the fish that gives birth to an amphibian through mutation
	 * @param canMate represents the parent's ability to mate
	 */
	public Amphibian(Fish fish, boolean canMate) {
		super("frog", fish.size(), fish.speed(), fish.lifespan(), fish.gender());
		hydration = 100;
		poisonous = fish.poisonous();
		venomous = Math.random() > 0.8;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 * Creates a new animal based on amphibian's traits
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			
			if (Math.random() > 0.6 && this.poisonous)
				return new Lizard(this, this.canMate(mate));
			return new Amphibian(this, this.canMate(mate));
		}
		
		return null;	
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 * Method checks if animal can mate and create a land animal
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * updates an amphibian's hydration levels
	 */
	public void update() {
		super.update();
		hydration--;
		
		if (hydration < 33)
		{
			health -=2;
		}
	}

}
