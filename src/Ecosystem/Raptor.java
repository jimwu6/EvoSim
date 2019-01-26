package Ecosystem;

/**
 * @author Justin
 * class to represent carnivorous birds
 */
public class Raptor extends Bird {
	private int talonSize, beakStrength;
	
	/**creates a new raptor that can live on land or fly over water
	 * @param type refers to the type of animal this is - a raptor
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 * @param flight represents if the bird can fly - almost always true
	 */
	public Raptor(String type, int size, int speed, int lifespan, String gender, boolean flight) {
		super(type, size, speed, lifespan, gender, flight);
		talonSize = 30;
		beakStrength = 40;
		carnivore = true;
		herbivore = false;
	}
	
	/** creates a copy of a preexisting raptor
	 * @param raptor is the raptor that is being copied
	 */
	public Raptor(Raptor raptor) {
		super (raptor);
		this.talonSize = raptor.talonSize;
		this.beakStrength = raptor.beakStrength;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("raptor");
		appearance = makeImage(bodyParts);
	}
	
	/** constructs a raptor based on a parent if it can mate
	 * @param raptor is the parent raptor
	 * @param canMate checks if parent can mate
	 */
	public Raptor(Raptor raptor, boolean canMate) {
		super(raptor, canMate);

		this.talonSize = raptor.talonSize;
		this.beakStrength = raptor.beakStrength;
		carnivore = true;
		herbivore = false;

		this.bodyParts.clear();
		this.bodyParts.add("raptor");
		appearance = makeImage(bodyParts);
	}

	/** creates a raptor from a small bird parent through evolution
	 * @param smallBird is the parent of the raptor 
	 * @param canMate checks if the parent can mate
	 */
	public Raptor(smallBird smallBird, boolean canMate) {
		super("raptor", smallBird.size(), smallBird.speed(), smallBird.lifespan() + 10, Math.random() < 0.5? "Male" : "Female", smallBird.flight);
		this.talonSize = 20;
		this.beakStrength = 30;
		type = "raptor";
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("raptor");
		appearance = makeImage(bodyParts);
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Bird#mate(Ecosystem.Animal)
	 * mating method that creates a new animal if the animal can mate
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Raptor(this, this.canMate(mate));
		}
		
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Bird#mate(Ecosystem.Animal)
	 * mating method that creates a new animal if the animal can mate
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	//updates animals health and appetite
	public void update() {
		super.update();
		
		//randomly increases the animals talon size; beak strength increases over time
		talonSize += (int) (Math.random() * 4 - 1);
		beakStrength++;
	}
	
	public void encircle(){
		
	}
	
	public void chase(Animal prey){
		
	}
	
	public void feed(Animal prey){
		
	}
}
