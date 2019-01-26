package Ecosystem;

/**
 * Class that represents dog-like creatures
 *these creates have the ability walk on land and hunt
 */
public class Canine extends Mammal{

	private int fangSize, claw;

	/**
	 * creates a new mammal that can live on land
	 * @param type refers to the type of animal this is - a mammal
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Canine(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 15;
		furLength = 30;
		intelligence = 35;
		fangSize = 5;
		claw = 5;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	/**creates a copy of another canine
	 * @param a represents the canine that this constructor duplicates
	 */
	public Canine(Canine canine) {
		super(canine);
		this.fangSize = canine.fangSize;
		this.claw = canine.claw;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	
	/**
	 * creates a duplicate of a parent canine with slight chances to mutate
	 * @param a represents the parent canine that the canine duplicates
	 * @param canMate represents the parent canine's ability to mate
	 */
	public Canine(Canine canine, boolean canMate) {
		super(canine, canMate);
		this.fangSize = 5;
		this.claw = 5;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	
	/**
	 * creates a duplicate of a parent mammal with slight chances to mutate
	 * @param a represents the parent mammal that the canine duplicates
	 * @param canMate represents the parent mammal's ability to mate
	 */
	public Canine(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		this.fangSize = 5;
		this.claw = 5;
		type = "canine";
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal)
	 */
	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Canine(this, this.canMate(mate));
		}
		
		return a;
	}
	
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#update()
	 * updates dogs health and allows its claws and fangs to grow
	 */
	public void update() {
		super.update();
		double rand = Math.random();
		
		if (rand > 0.7) {
			fangSize++;
			claw++;
		}
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#calculateDamage()
	 * calculates damage based on fang and claw sizes
	 */
	public void calculateDamage() {
		damage += fangSize * 2 + claw * 2;
	}

}
