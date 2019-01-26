package Ecosystem;

/**
 * Class that represents avian creatures.
 * These creates have the ability to fly over water or walk on land.
 */
public class Bird extends Animal {
	
	protected boolean flight;
	
	/**
	 * Creates a new bird that can live on land or in water.
	 * 
	 * @param type refers to the type of animal this is - a bird
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 * @param flight represents if the bird can fly - almost always true
	 */
	public Bird(String type, int size, int speed, int lifespan, String gender, boolean flight) {
		super(type, size, speed, lifespan, gender);
		this.flight = flight;
		this.water = true;
		this.land = true;
	}
	
	/** 
	 * Copy constructor for a bird that creates a duplicate instance of another bird.
	 * 
	 * @param bird - variable for the bird that is being copied
	 */
	public Bird(Bird bird) {
		super (bird);
		this.flight = bird.flight;
		this.water = true;
		this.land = true;
	}
	
	/**
	 * Creates a duplicate of a parent bird with slight chances to mutate.
	 * 
	 * @param bird represents the parent bird that the bird duplicates
	 * @param canMate represents the parent bird's ability to mate
	 */
	public Bird(Bird bird, boolean canMate) {
		super(bird, canMate);
		this.flight = bird.flight;
		this.water = true;
		this.land = true;
	}

	/** 
	 * Constructor for a bird created from lizards mating.
	 * 
	 * @param lizard represents a parent lizard that evolved into a bird
	 * @param canMate parameter dictating if the parent could mate
	 */
	public Bird(Lizard lizard, boolean canMate) {
		super("smallBird", 20, 60, 30, Math.random() < 0.5? "Male" : "Female");
		this.water = true;
		this.land = true;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 * mating method for a brid to create a new instance of a bird
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate)) 
		{
			this.mateTimer = 3;
			return new Bird(this, this.canMate(mate));
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 * method checks if an animal can mate with another instance of an animal, and returns a child if true
	 */
	public Animal mate(Animal animal, boolean canMate) {
		if (canMate)
			return this.mate(animal);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * updates animal
	 */
	public void update() {
		super.update();
	}

}
