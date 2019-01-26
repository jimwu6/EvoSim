package Ecosystem;

/**
 * class for snakes
 *
 */
public class Snake extends Reptile{
	private boolean venomous, hooded;
	
	/**
	 * creates a new snake that can live on land 
	 * @param type refers to the type of animal this is - a snake
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
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
		this.bodyParts.add("snake");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a copy of another snake animal
	 * @param s is the snake being copied
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
		this.bodyParts.add("snake");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a new snake baby from a parent if it can mate
	 * @param s is the parent snake
	 * @param canMate checks if the parent can mate
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
		this.bodyParts.add("snake");
		appearance = makeImage(bodyParts);
	}

	/**creates a snake baby from a parent reptile
	 * @param reptile is the parent 
	 * @param canMate checks if parent can mate
	 */
	public Snake(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.venomous = Math.random() > 0.9;
		this.hooded = Math.random() > 0.7? true : false;
		type = "snake";
		carnivore = true;
		herbivore = false;
		land = true;
		water = false;
		this.bodyParts.clear();
		this.bodyParts.add("snake");
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
	
	public void stalk(){
		
	}
	
	public void hoodDisplay(){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed(Animal prey){
		
	}
}
