package Ecosystem;

public class Rodent extends Mammal{

	public int claw;
	public boolean disease;
	
	public Rodent(String imageName, int size, int speed, int lifespan, String gender) {
		super(imageName, size, speed, lifespan, gender);
		furDensity = 10;
		furLength = 5;
		intelligence = 5;
		claw = 5;
		carnivore = false;
		herbivore = true;
	}
	
	public Rodent(Rodent rodent) {
		super(rodent);
		this.claw = rodent.claw;
		this.disease = rodent.disease;
		carnivore = false;
		herbivore = true;
	}
	
	public Rodent(Rodent rodent, boolean canMate) {
		super(rodent, canMate);
		this.claw = rodent.claw;
		this.disease = rodent.disease;
		carnivore = false;
		herbivore = true;
	}
	
	public Rodent(Mammal mammal, boolean canMate) {
		super(mammal, canMate);
		type = "rodent";
		carnivore = false;
		herbivore = true;
	}

	public Rodent(smallBird smallBird, boolean canMate) {
		super("rodent", smallBird.size(), smallBird.speed(), smallBird.lifespan(), Math.random() < 0.5? "Male" : "Female");
		claw = 5;
		disease = false;
		type = "rodent";
		carnivore = false;
		herbivore = true;
	}

	public Animal mate(Animal mate) {
		Animal a = super.mate(mate);
		
		if (a == null && this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Rodent(this, this.canMate(mate));
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
		
		if (rand > 0.9)
			claw++;
	}
	
	public void spreadDisease(Animal animal){
		
	}
	
	public void feed(String plant){
		
	}

}
