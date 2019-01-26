package Ecosystem;

/**
 * Represents feline animals that are carnivorous and can traverse land.
 *
 */
public class Feline extends Mammal{

	//fields
	private int fangSize, claw;
	
	/**
	 * Creates a new canine that can live on land.
	 * 
	 * @param type refers to the type of animal this is - a feline
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Feline(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		furDensity = 20;
		furLength = 30;
		intelligence = 30;
		fangSize = 30;
		claw = 30;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("feline");
		appearance = makeImage(bodyParts);
	}
	
	/**
	 * Copy constructor for felines that replicate a passed parameter.
	 * 
	 * @param feline is the animal that is being duplicated
	 */
	public Feline(Feline feline) {
		super(feline);
		this.fangSize = feline.fangSize;
		this.claw = feline.claw;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("feline");
		appearance = makeImage(bodyParts);
	}
	
	/**
	 * Creates a new feline animal from a parent if it can mate.
	 * 
	 * @param feline is the parent feline
	 * @param canMate checks if the animal can mate
	 */
	public Feline(Feline feline, boolean canMate) {
		super(feline, canMate);
		this.fangSize = feline.fangSize;
		this.claw = feline.claw;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("feline");
		appearance = makeImage(bodyParts);
	}
	
	/**
	 * Creates a new feline child from a parent mammal.
	 * 
	 * @param mammal is a parent mammal
	 * @param canMate checks if the parent can mate
	 */
	public Feline(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "feline";
		carnivore = true;
		herbivore = false;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal)
	 * mating method for the feline class that returns a new feline if it can mate
	 */
	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Feline(this, this.canMate(mate));
		}
		
		return a;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#mate(Ecosystem.Animal, boolean)
	 * mating method for feline if they are on land and can mate
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Mammal#update()
	 * updates a feline's health and appetite
	 */
	public void update() {
		super.update();
		double rand = Math.random();
		
		//random chance for the fangs and claws to grow
		if (rand > 0.7) {
			fangSize++;
			claw++;
		}
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#calculateDamage()
	 * adds the size of the fangs and claw to the damage done
	 */
	public void calculateDamage() {
		damage += fangSize + claw;
	}
	
	public void stalk(){
		
	}
}
