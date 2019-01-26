package Ecosystem;

/**
 * class representing small mammals like mice
 *
 */
public class Rodent extends Mammal{

	//fields
	private int claw;
	public boolean disease;
	
	/** creates a new rodent that can live on land and eat plants
	 * @param type refers to the type of animal this is - a rodent
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
		this.bodyParts.add("rodent");
		appearance = makeImage(bodyParts);
	}
	
	/** creates a copy of another rodent
	 * @param rodent is the rodent being copied
	 */
	public Rodent(Rodent rodent) {
		super(rodent);
		this.claw = rodent.claw;
		this.disease = rodent.disease;
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("rodent");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a new rodent as a child  
	 * @param rodent is the parent rodent
	 * @param canMate checks if the parent could mate
	 */
	public Rodent(Rodent rodent, boolean canMate) {
		super(rodent, canMate);
		this.claw = rodent.claw;
		this.disease = rodent.disease;
		carnivore = false;
		herbivore = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("rodent");
		appearance = makeImage(bodyParts);
	}
	
	/** creates a new rodent from a mammal parent
	 * @param mammal is the parent mammal
	 * @param canMate checks if the animal can mate
	 */
	public Rodent(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "rodent";
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("rodent");
		appearance = makeImage(bodyParts);
	}

	/**creates rodent evolved from small birds
	 * @param smallBird is the parent animal
	 * @param canMate checks if the parent could mate
	 */
	public Rodent(smallBird smallBird, boolean canMate) {
		super("rodent", smallBird.size(), smallBird.speed(), smallBird.lifespan(), Math.random() < 0.5? "Male" : "Female");
		claw = 5;
		disease = false;
		type = "rodent";
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("rodent");
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
	
	public void spreadDisease(Animal animal){
		
	}
	
	public void feed(String plant){
		
	}

}
