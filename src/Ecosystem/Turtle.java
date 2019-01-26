package Ecosystem;

/**
 * @author Justin
 *class for turtles
 */
public class Turtle extends Reptile{
	private int shellHardness;
	
	/**
	 * creates a new turtle that can live on land or in water
	 * @param type refers to the type of animal this is - a turtle
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 * @param flight represents if the bird can fly - almost always true
	 */
	public Turtle(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		shellHardness = 50;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("turtle");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a copy of a turtle
	 * @param t is the copied turtle
	 */
	public Turtle(Turtle t) {
		super (t);
		this.shellHardness = t.shellHardness;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("turtle");
		appearance = makeImage(bodyParts);
	}
	
	/** creates a new baby turtle from an adult turtle that can mate
	 * @param t is the parent
	 * @param canMate checks if mating is allowed
	 */
	public Turtle(Turtle t, boolean canMate) {
		super(t, canMate);
		this.shellHardness = 20;
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("turtle");
		appearance = makeImage(bodyParts);
	}

	/**creates a new baby turtle from a reptile that can mate
	 * @param reptile is the parent
	 * @param canMate checks if mating is allowed
	 */
	public Turtle(Reptile reptile, boolean canMate) {
		super(reptile, canMate);
		this.shellHardness = 30;
		type = "turtle";
		carnivore = false;
		herbivore = true;
		land = true;
		water = true;

		this.bodyParts.clear();
		this.bodyParts.add("turtle");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Turtle(this, this.canMate(mate));
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
		
		shellHardness += (int) (Math.random() * 4 - 1);
	}
	
	public void withdraw(){
		
	}
	
	public void feed (String plant){
		
	}
}
