package Ecosystem;

public class Fish extends Animal {
	public int scaleHardness;
	public boolean poisonous, luminant;
	
	public Fish(String type, int size, int speed, int lifespan, String gender) {
		super (type, size, speed, lifespan, gender);
		scaleHardness = 20;
		poisonous = false;
		luminant = false;
		herbivore = true;
		carnivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}
	
	public Fish(Fish f) {
		super (f);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous;
		luminant = f.luminant;
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}
	
	public Fish(Fish f, boolean canMate) {
		super(f, canMate);
		scaleHardness = f.scaleHardness;
		poisonous = f.poisonous? f.poisonous : Math.random() > 0.9;
		luminant = Math.random() > 0.9? !f.luminant : f.luminant;
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}

	public Fish(Cellular cellular, boolean canMate) {
		super("fish", 10, 20, 50 + (int) (Math.random() * 21 - 10), cellular.gender());
		herbivore = true;
		carnivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("fish");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			
			if (this.scaleHardness > 50 && Math.random() > 0.97 && this.poisonous)
				return new Amphibian(this, this.canMate(mate));
			return new Fish(this, this.canMate(mate));
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
		double rand = Math.random();
		
		if (rand > 0.7)
			scaleHardness += 4;
		else if (rand > 0.4)
			scaleHardness -= 4;
		
	}

}
