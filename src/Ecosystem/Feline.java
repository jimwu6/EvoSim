package Ecosystem;

public class Feline extends Mammal{

	public int fangSize, claw;
	
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
	
	public Feline(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "feline";
		carnivore = true;
		herbivore = false;
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Feline(this, this.canMate(mate));
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
		
		if (rand > 0.7) {
			fangSize++;
			claw++;
		}
	}
	
	public void calculateDamage() {
		damage += fangSize + claw;
	}
	
	public void stalk(){
		
	}
}
