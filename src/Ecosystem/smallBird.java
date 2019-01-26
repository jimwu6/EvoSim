package Ecosystem;

/**
 * The smallBird class represents small-sized, herbivorous birds
 *
 */
public class smallBird extends Bird {

	private int featherVibrance;

	/** This constructor creates a new small bird that can live on land or fly over water.
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

	/**This constructor creates a copy of a small bird animal that is passed as a parameter.
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

	/**This method creates a new baby bird that was born from parents that are also smallBirds.
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

	/**This method makes a small bird animal from lizard parents through the use of evolution.
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
}
