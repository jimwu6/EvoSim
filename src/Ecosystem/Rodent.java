package Ecosystem;

/**
 * The Rodent class is a class representing small mammals like mice.
 * Rodents are herbivorous and terrestrial.
 */
public class Rodent extends Mammal{

	//fields
	private int claw;
	public boolean disease;
	
	/** Ths constructor creates a new Rodent that can live on land and eat plants.
	 * @param type refers to the type of animal this is - a Rodent
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Rodent(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		furDensity = 10;
		furLength = 5;
		intelligence = 5;
		claw = 5;
		carnivore = false;
		herbivore = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("Rodent");
		appearance = makeImage(bodyParts);
	}
	
	/** This constructor creates a copy of another Rodent.
	 * @param Rodent is the Rodent being copied
	 */
	public Rodent(Rodent Rodent) {
		super(Rodent);
		this.claw = Rodent.claw;
		this.disease = Rodent.disease;
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("Rodent");
		appearance = makeImage(bodyParts);
	}
	
	/**This constructor creates a new Rodent as a child of a Rodent parent. 
	 * @param Rodent is the parent Rodent
	 * @param canMate checks if the parent could mate
	 */
	public Rodent(Rodent Rodent, boolean canMate) {
		super(Rodent, canMate);
		this.claw = Rodent.claw;
		this.disease = Rodent.disease;
		carnivore = false;
		herbivore = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("Rodent");
		appearance = makeImage(bodyParts);
	}
	
	/** This constructor creates a new Rodent that formed as a result of a mammal parent mating.
	 * @param mammal is the parent mammal
	 * @param canMate checks if the animal can mate
	 */
	public Rodent(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "Rodent";
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("Rodent");
		appearance = makeImage(bodyParts);
	}

	/**This class creates Rodent that evolved from small birds.
	 * @param smallBird is the parent animal
	 * @param canMate checks if the parent could mate
	 */
	public Rodent(smallBird smallBird, boolean canMate) {
		super("Rodent", smallBird.size(), smallBird.speed(), smallBird.lifespan(), Math.random() < 0.5? "Male" : "Female");
		claw = 5;
		disease = false;
		type = "Rodent";
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("Rodent");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Rodent(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	public void update() {
		super.update();
		double rand = Math.random();
		
		if (rand > 0.9)
			claw++;
	}
	
}
