package Ecosystem;

public class Primate extends Mammal {
	public int toolStrength;

	public Primate(String type, int size, int speed, int lifespan, String gender) {
		super(type, size, speed, lifespan, gender);
		furDensity = 30;
		furLength = 15;
		intelligence = 50;
		toolStrength = 10;
		carnivore = true;
		herbivore = true;
		this.bodyParts.clear();
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	public Primate(Primate primate) {
		super(primate);
		this.toolStrength = primate.toolStrength;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	public Primate(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "primate";
		this.toolStrength = 10;
		carnivore = true;
		herbivore = true;

		this.bodyParts.clear();
		this.bodyParts.add("primate");
		appearance = makeImage(bodyParts);
	}

	public Primate(Primate primate, boolean canMate) {
		super(primate, canMate);
		this.toolStrength = primate.toolStrength;
		carnivore = true;
		herbivore = true;
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);

		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Primate(this, this.canMate(mate));
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

		if (rand > 0.8) {
			toolStrength++;
			if (rand > 0.9)
				intelligence++;
		}
	}

	public void buildTool(){

	}

	public void feed(String plant){

	}

	public void chase(Animal prey){

	}

	public void feed(Animal prey){

	}
}
