package Ecosystem;

/**
 *The Turtle class represents animals that resemble Turtles.
 */
public class Turtle extends Reptile{
	private int shellHardness;
	
	/**
	 * This constructor creates a new Turtle that can live on land or in water.
	 * @param type refers to the type of animal this is - a Turtle.
	 * @param size refers to how large the animal is, which impacts the hunting.
	 * @param speed refers to the speed at which the animal moves.
	 * @param lifespan represents the animal's lifespan.
	 * @param gender is a String depicting if the animal is either male or female.
	 */
	public Turtle(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		shellHardness = 50;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("Turtle");
		appearance = makeImage(bodyParts);
	}
	
	/**This constructor creates a copy of a Turtle.
	 * @param t is a parameter representing the Turtle that is being duplicated.
	 */
	public Turtle(Turtle t) {
		super (t);
		this.shellHardness = t.shellHardness;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("Turtle");
		appearance = makeImage(bodyParts);
	}
	
	/** This constructor creates a new baby Turtle from an adult Turtle that can mate.
	 * @param t is the parent Turtle.
	 * @param canMate checks if mating is allowed.
	 */
	public Turtle(Turtle t, boolean canMate) {
		super(t, canMate);
		this.shellHardness = 20;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("Turtle");
		appearance = makeImage(bodyParts);
	}

	/**creates a new baby Turtle from a reptile that can mate.
	 * @param reptile represents the parent of the animal.
	 * @param canMate is a boolean value that checks if mating is allowed.
	 */
	public Turtle(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.shellHardness = 30;
		type = "Turtle";
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("Turtle");
		appearance = makeImage(bodyParts);
	}

	//mating method extended from animals
	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Turtle(this, this.canMate(mate));
		}
		
		return a;
	}
	
	//mating method for Turtles
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	public void update() {
		super.update();
		//randomly updates the shell's hardness
		shellHardness += (int) (Math.random() * 4 - 1);
	}	
}
