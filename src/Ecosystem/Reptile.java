package Ecosystem;

public class Reptile extends Animal {
	protected int scaleHardness, fangSize, biteStrength;

	public Reptile(String imageName, int size, int speed, int lifespan, String gender) {
		super (imageName, size, speed, lifespan, gender);
		scaleHardness = 50;
		fangSize = 30;
		biteStrength = 50;
	}

	public Reptile(Reptile r) {
		super (r);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}

	public Reptile(Reptile r, boolean canMate) {
		super(r, canMate);
		this.scaleHardness = r.scaleHardness;
		this.fangSize = r.fangSize;
		this.biteStrength = r.biteStrength;
	}

	public Reptile(Amphibian amph) {
		super("lizard", amph.size(), amph.speed(), amph.lifespan(), amph.gender());
		scaleHardness = 50;
		fangSize = 20;
		biteStrength = 30;
	}

	public Reptile(Cellular cellular) {
		super("lizard", cellular.size(), cellular.speed(), cellular.lifespan(), cellular.gender());
		scaleHardness = 30;
		fangSize = 10;
		biteStrength = 15;
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			double chance = Math.random();
			if(scaleHardness > 60 && chance > 0.9)
				return new Turtle(this, this.canMate(mate));
			else if (scaleHardness > 40 && chance > 0.8) 
				return new Snake(this, this.canMate(mate));
			else if (chance > 0.7) 
				return new Lizard(this, this.canMate(mate));				
			return null;
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
		scaleHardness += (int) (Math.random() * 4 - 1);
		fangSize++;
		biteStrength++;
	}
}
