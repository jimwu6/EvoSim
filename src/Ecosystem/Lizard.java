package Ecosystem;

/**
 * Class that represents lizard organisms. They are land based and can eat other animals.
 */
public class Lizard extends Reptile {
	private boolean tailDecoy;

	/**
	 * Creates a new lizard that can live on land.
	 * 
	 * @param type refers to the type of animal this is - a lizard
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Lizard(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		tailDecoy = true;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;

		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/**
	 * Creates a copy of a lizard.
	 * 
	 * @param i represents the lizard that is being duplicated
	 */
	public Lizard(Lizard i) {
		super(i);
		this.tailDecoy = i.tailDecoy;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		
		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/**
	 * Method to create a lizard through mating.
	 * 
	 * @param i is the parent lizard
	 * @param canMate checks if the parent could properly mate
	 */
	public Lizard(Lizard i, boolean canMate) {
		super(i, canMate);
		this.tailDecoy = i.tailDecoy;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/** 
	 * Method to birth a new lizard from an amphibian.
	 * 
	 * @param amph is the parent amphibian
	 * @param canMate checks if the parent can properly mate
	 */
	public Lizard(Amphibian amph, boolean canMate) {
		super(amph);
		this.tailDecoy = true;
		type = "lizard";
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/** 
	 * Constructs a new lizard from a cellular organism.
	 * 
	 * @param cellular is the parent cellular
	 * @param canMate checks if the parent can properly mate
	 
	 */
	public Lizard(Cellular cellular, boolean canMate) {
		super(cellular);
		this.tailDecoy = true;
		type = "lizard";
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/** 
	 * Births a new lizard from a base reptile by evolution.
	 * 
	 * @param reptile is the parent reptile
	 * @param canMate checks if the parent can properly mate
	 */
	public Lizard(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.tailDecoy = true;
		type = "lizard";
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("lizard");
		appearance = makeImage(bodyParts);
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Reptile#mate(Ecosystem.Animal)
	 * Mating method that allows a lizard to give birth to a small bird or a lizard depending on the animal's traits
	 */
	public Animal mate(Animal mate) {
		Animal a;
		
		if (scaleHardness > 85) {
			return new smallBird(this, this.canMate(mate));
		}
		else {
			a = super.mate(mate);

			if (a == null && this.canMate(mate))
			{
				this.mateTimer = 2;
				return new Lizard(this, this.canMate(mate));
			}
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Reptile#mate(Ecosystem.Animal, boolean)
	 * mating method that takes into account if the land is safe
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Reptile#update()
	 * updates animal's health and appetite
	 */
	public void update() {
		super.update();
	}

	public void visDisplay(){

	}

	public void camouflage(){

	}

	public void decoy(){

	}

}
