package Ecosystem;

/**
 * @author Justin
 *The Amphibian Class represents animals that are amphibians, such as frogs
 *Considered to be omnivores and have the ability to traverse both land territories and water territories
 */
public class Amphibian extends Animal {
	//fields
	protected int hydration;
	
	/**
	 * Fields that represent animal's ability to do damage when hunting - venomous- or when hunted -poisonous-
	 */
	public boolean poisonous, venomous;
	
	public Amphibian(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		hydration = 100;
		poisonous = Math.random() > 0.93;
		venomous = false;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	public Amphibian(Amphibian a) {
		super (a);
		hydration = a.hydration;
		poisonous = a.poisonous;
		venomous = a.venomous;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	public Amphibian(Amphibian a, boolean canMate) {
		super(a, canMate);
		hydration = a.hydration;
		poisonous = a.poisonous? true : Math.random() > 0.8;
		venomous = a.venomous;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}

	public Amphibian(Fish fish, boolean canMate) {
		super("frog", fish.size(), fish.speed(), fish.lifespan(), fish.gender());
		hydration = 100;
		poisonous = fish.poisonous;
		venomous = Math.random() > 0.8;
		herbivore = true;
		carnivore = true;
		this.water = true;
		this.land = true;
		this.bodyParts.clear();
		this.bodyParts.add("amphibian");
		appearance = makeImage(bodyParts);
	}
	
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			
			if (Math.random() > 0.6 && this.poisonous)
				return new Lizard(this, this.canMate(mate));
			return new Amphibian(this, this.canMate(mate));
		}
		
		return null;	
	}

	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	public void update() {
		super.update();
		hydration--;
	}

}
