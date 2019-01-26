package Ecosystem;

/**
 * class for reptile animals
 */
/**
 * @author Justin
 *
 */
public class Reptile extends Animal {
	protected int scaleHardness, fangSize, biteStrength;

	/**
	 * Creates a new reptile that lives on land.
	 * 
	 * @param type The type of animal this is - a reptile
	 * @param size - how large the animal is, which impacts the hunting
	 * @param speed - the speed at which the animal moves
	 * @param lifespan represents the animal's lifespan
	 * @param gender the animal is either male or female
	 */
	public Reptile(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		scaleHardness = 50;
		fangSize = 30;
		biteStrength = 50;
	}

	/**
	 * Creates a copy of a reptile and its stats.
	 * 
	 * @param r The reptile being copied
	 */ 
	public Reptile(Reptile r) {
		super (r);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}

	/** 
	 * Creates a new reptile through mating.
	 * 
	 * @param r is the parent reptile
	 * @param canMate checks if the parent can reproduce
	 */
	public Reptile(Reptile r, boolean canMate) {
		super(r, canMate);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}

	/**
	 * Creates a new reptile from an amphibian parent as a result of evolution.
	 * 
	 * @param amph is the parent amphibian
	 */
	public Reptile(Amphibian amph) {
		super("lizard", amph.size(), amph.speed(), amph.lifespan(), amph.gender());
		scaleHardness = 50;
		fangSize = 20;
		biteStrength = 30;
	}

	
	/** 
	 * Creates a lizard from a cellular animal.
	 * 
	 * @param cellular is the parental cellular animal
	 */
	public Reptile(Cellular cellular) {
		super("lizard", cellular.size(), cellular.speed(), cellular.lifespan(), cellular.gender());
		scaleHardness = 30;
		fangSize = 10;
		biteStrength = 15;
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal)
	 *mating method for reptiles that creates new animals
	 */
	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			double chance = Math.random();
			if(scaleHardness > 60 && chance > 0.8)
				return new Turtle(this, this.canMate(mate));
			else if (scaleHardness > 40 && chance > 0.6) 
				return new Snake(this, this.canMate(mate));
			else if (chance > 0.4) 
				return new Lizard(this, this.canMate(mate));				
			return null;
		}

		return null;	
	}

	/* (non-Javadoc)
	 * @see Ecosystem.Animal#mate(Ecosystem.Animal, boolean)
	 *creates a new animal through mating
	 */
	public Animal mate(Animal mate, boolean landSafe) {
		if (landSafe)
			return this.mate(mate);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Ecosystem.Animal#update()
	 * updates animals scalehardness randomly and fang size
	 */
	public void update() {
		super.update();
		scaleHardness++;
		fangSize++;
		biteStrength++;
	}
}
