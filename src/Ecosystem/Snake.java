package Ecosystem;

/**
 * The Snake class represents reptilian animals that are similar to Snakes
 */
public class Snake extends Reptile{
	//fields
	private boolean venomous, hooded;
	
	/**
	 * This constructor creates a new Snake that can live on land.
	 * @param type refers to the type of animal this is - a Snake.
	 * @param size depicts how large the animal is, which impacts the hunting.
	 * @param speed is an integer showing the speed at which the animal moves.
	 * @param lifespan represents the animal's lifespan.
	 * @param gender is a String that shows if the animal is either male or female.
	 */
	public Snake(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		venomous = false;
		hooded = false;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;

		this.bodyParts.clear();
		this.bodyParts.add("Snake");
		appearance = makeImage(bodyParts);
	}
	
	/**This constructor creates a copy of another Snake animal.
	 * @param s is the Snake being copied.
	 */
	public Snake(Snake s) {
		super (s);
		this.venomous = s.venomous;
		this.hooded = s.hooded;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("Snake");
		appearance = makeImage(bodyParts);
	}
	
	/**This constructor creates a new Snake baby from a parent if it can mate.
	 * @param s is the parent Snake that gave birth to a new Snake.
	 * @param canMate is a boolean value that checks if the parent can mate.
	 */
	public Snake(Snake s, boolean canMate) {
		super(s, canMate);
		this.venomous = Math.random() > 0.9;
		this.hooded = Math.random() > 0.7? true : false;
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("Snake");
		appearance = makeImage(bodyParts);
	}

	/**This constructor creates a Snake baby from a parent reptile.
	 * @param reptile is the parent.
	 * @param canMate is a boolean value that checks if parent can mate.
	 */
	public Snake(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.venomous = Math.random() > 0.9;
		this.hooded = Math.random() > 0.7? true : false;
		type = "Snake";
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("Snake");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 2;
			return new Snake(this, this.canMate(mate));
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
		
		
	}
}
