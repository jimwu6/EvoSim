package Ecosystem;

public class Canine extends Mammal{

	public int fangSize, claw;

	public Canine(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 15;
		furLength = 30;
		intelligence = 35;
		fangSize = 5;
		claw = 5;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	public Canine(Canine canine) {
		super(canine);
		this.fangSize = canine.fangSize;
		this.claw = canine.claw;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	public Canine(Canine canine, boolean canMate) {
		super(canine, canMate);
		this.fangSize = 5;
		this.claw = 5;
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}
	
	public Canine(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		this.fangSize = 5;
		this.claw = 5;
		type = "canine";
		carnivore = true;
		herbivore = false;
		this.bodyParts.clear();
		this.bodyParts.add("canine");
		appearance = makeImage(bodyParts);
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Canine(this, this.canMate(mate));
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
		damage += fangSize * 2 + claw * 2;
	}
	
	public void findPack(Canine pack){
		
	}
}
