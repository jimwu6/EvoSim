package Ecosystem;

public class Raptor extends Bird {
	private int talonSize, beakStrength;
	
	public Raptor(String type, int size, int speed, int lifespan, String gender, boolean flight) {
		super(type, size, speed, lifespan, gender, flight);
		talonSize = 30;
		beakStrength = 40;
		carnivore = true;
		herbivore = false;
	}
	
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

	public Animal mate(Animal mate) {
		if (this.canMate(mate))
		{
			this.mateTimer = 3;
			return new Raptor(this, this.canMate(mate));
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
