package Ecosystem;

/**
 * The Raptor class is used to represent carnivorous birds.
 */
public class Raptor extends Bird {
	private int talonSize, beakStrength;
	
	/** This constructor creates a new Raptor that can live on land or fly over water.
	 * @param type refers to the type of animal this is - a Raptor
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 * @param flight represents if the bird can fly - almost always true
	 */
	public Raptor(String type, int size, int speed, int lifespan, String gender, boolean flight) {
		super(type, size, speed, lifespan, gender, flight);
		talonSize = 30;
		beakStrength = 40;
		carnivore = true;
		herbivore = false;
	}
	
	/**This constructor creates a copy of a pre-existing Raptor.
	 * @param Raptor is the Raptor that is being copied
	 */
	public Raptor(Raptor Raptor) {
		super (Raptor);
		this.talonSize = Raptor.talonSize;
		this.beakStrength = Raptor.beakStrength;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("Raptor");
		appearance = makeImage(bodyParts);
	}
	
	/** This method constructs a Raptor that was formed by a Raptor parent through the mating process.
	 * @param Raptor is the parent Raptor
	 * @param canMate checks if parent can mate
	 */
	public Raptor(Raptor Raptor, boolean canMate) {
		super(Raptor, canMate);

		this.talonSize = Raptor.talonSize;
		this.beakStrength = Raptor.beakStrength;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("Raptor");
		appearance = makeImage(bodyParts);
	}

	/** This constructor creates a Raptor that was formed by the mating of smallBird parents through evolution.
	 * @param smallBird is the parent of the Raptor 
	 * @param canMate checks if the parent can mate
	 */
	public Raptor(smallBird smallBird, boolean canMate) {
		super("Raptor", smallBird.size(), smallBird.speed(), smallBird.lifespan() + 10, Math.random() < 0.5? "Male" : "Female", smallBird.flight);
		this.talonSize = 20;
		this.beakStrength = 30;
		type = "Raptor";
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("Raptor");
		appearance = makeImage(bodyParts);
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Bird#mate(Ecosystem.Animal)
	 * mating method that creates a new animal if the animal can mate
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Raptor(this, this.canMate(mate));
		}
		
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Bird#mate(Ecosystem.Animal)
	 * mating method that creates a new animal if the animal can mate
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	//updates animals health and appetite
	public void update() {
		super.update();
		
		//randomly increases the animals talon size; beak strength increases over time
		talonSize += (int) (Math.random() * 4 - 1);
		beakStrength++;
	}

}
