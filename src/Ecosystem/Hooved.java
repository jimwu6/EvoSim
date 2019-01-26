package Ecosystem;

/**
 * Class for herbivorous, hooved animals such as horses
 *
 */
public class Hooved extends Mammal {
	//fields
	private int hornStrength;
	
	/**
	 * creates a new horse that can live on land
	 * @param type refers to the type of animal this is - a horse
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Hooved(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		furDensity = 40;
		furLength = 20;
		intelligence = 20;
		hornStrength = 50;
		carnivore = false;
		herbivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("hooved");
		appearance = makeImage(bodyParts);
	}
	
	/**takes a hooved animal and creates a copy of it
	 * @param hooved represents the hooved animal being copied
	 */
	public Hooved(Hooved hooved) {
		super(hooved);
		this.hornSize = hooved.hornStrength;
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("hooved");
		appearance = makeImage(bodyParts);
	}
	
	/**
	 * @param hooved is the parent hooved animal
	 * @param canMate is a boolean for if the parent can mate or not
	 */
	public Hooved(Hooved hooved, boolean canMate) {
		super(hooved, canMate);
		this.hornSize = hooved.hornStrength;
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("hooved");
		appearance = makeImage(bodyParts);
	}
	
	/**
	 * @param mammal represents the parent mammal
	 * @param canMate 
	 */
	public Hooved(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "hooved";
		carnivore = false;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("hooved");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Hooved(this, this.canMate(mate));
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
		
		if (hornSize > 0 && rand > 0.5)
			hornStrength++;
	}
	
	public void findHerd(Hooved animal){
		
	}
	
	public void feed(String plant){
		
	}

}


