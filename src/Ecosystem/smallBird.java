package Ecosystem;

/**
 * class for small bird animals
 *
 */
public class smallBird extends Bird {

	private int featherVibrance;

	/** creates a new small bird that can live on land or fly over water
	 * @param type refers to the type of animal this is - a small bird
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 * @param flight represents if the bird can fly - almost always true
	 */
	public smallBird(String type, int size, int speed, int lifespan, String gender, boolean flight) {
		super(type, size, speed, lifespan, gender, flight);
		featherVibrance = 20;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("smallBird");
		appearance = makeImage(bodyParts);
	}

	/** creates a copy of a small bird animal
	 * @param bird is the bird being copied
	 */
	public smallBird(smallBird bird) {
		super (bird);
		this.featherVibrance = bird.featherVibrance;
		carnivore = true;
		herbivore = true;
		
		this.bodyParts.clear();
		this.bodyParts.add("smallBird");
		appearance = makeImage(bodyParts);
	}

	/** method to create new baby bird
	 * @param bird is the parent bird
	 * @param canMate checks if parent can mate
	 */
	public smallBird(smallBird bird, boolean canMate) {
		super(bird, canMate);
		this.featherVibrance = 20;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("smallBird");
		appearance = makeImage(bodyParts);
	}

	/** makes a small bird animal from parent lizards
	 * @param lizard is the parent lizard
	 * @param canMate checks if parent could mate
	 */
	public smallBird(Lizard lizard, boolean canMate) {
		super(lizard, canMate);
		type = "smallBird";
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("smallBird");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 2;
			if (featherVibrance > 35) {
				if (size > 35)
					return new Raptor(this, this.canMate(mate));
				else
					return new Rodent(this, this.canMate(mate));
			}
			
			return new smallBird(this, this.canMate(mate));
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

		if (Math.random() > 0.8)
			featherVibrance++;
	}

	public void sing(){

	}

	public void feed(String plant){

	}

	public void chase(Animal prey){

	}
	public void feed(Animal prey){

	}
}
